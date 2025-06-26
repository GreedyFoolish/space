package com.example.space.service.impl;

import com.example.space.convert.SpaceNavConvertor;
import com.example.space.dto.PageResponse;
import com.example.space.dto.SpaceNavDTO;
import com.example.space.dto.SpaceNavQueryDTO;
import com.example.space.dto.SpaceNavTreeDTO;
import com.example.space.enums.ResponseCodeEnum;
import com.example.space.exception.BusinessException;
import com.example.space.model.SpaceNav;
import com.example.space.repository.SpaceNavRepository;
import com.example.space.service.SpaceNavService;
import com.example.space.specification.SpaceNavSpecs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SpaceNavServiceImpl implements SpaceNavService {
    // 日志记录器
    private static final Logger logger = LoggerFactory.getLogger(SpaceNavServiceImpl.class);
    // 导航栏数据访问对象
    private final SpaceNavRepository spaceNavRepository;

    public SpaceNavServiceImpl(SpaceNavRepository spaceNavRepository) {
        this.spaceNavRepository = spaceNavRepository;
    }

    @Override
    public List<SpaceNavTreeDTO> buildNavTree(Long roleId) {
        try {
            List<SpaceNavTreeDTO> allNavs = spaceNavRepository.findUserNavsByRoleId(roleId);
            if (allNavs == null) {
                logger.warn("角色ID {} 获取到的导航菜单为空", roleId);
                return Collections.emptyList();
            }
            // 建立父ID到子节点的映射
            Map<Long, List<SpaceNavTreeDTO>> parentToChildren = new HashMap<>();
            for (SpaceNavTreeDTO nav : allNavs) {
                Long parentId = nav.getParentId() == null ? null : nav.getParentId();
                if (parentId != null) {
                    parentToChildren.computeIfAbsent(parentId, k -> new ArrayList<>()).add(nav);
                }
            }
            // 构建导航树
            List<SpaceNavTreeDTO> rootNodes = allNavs.stream()
                .filter(nav -> nav.getParentId() == null || nav.getParentId() == 0)
                .sorted((o1, o2) -> Integer.compare(o1.getNavSort(), o2.getNavSort()))
                .peek(nav -> buildChildren(nav, parentToChildren))
                .collect(Collectors.toList());
            logger.info("获取用户导航树成功");
            return rootNodes;
        } catch (Exception e) {
            logger.error("构建用户导航树失败", e);
            throw e;
        }
    }

    private void buildChildren(SpaceNavTreeDTO parent, Map<Long, List<SpaceNavTreeDTO>> parentToChildren) {
        List<SpaceNavTreeDTO> children = parentToChildren.getOrDefault(parent.getId(), Collections.emptyList());
        children.sort((o1, o2) -> Integer.compare(o1.getNavSort(), o2.getNavSort()));
        parent.setChildren(children);
        children.forEach(child -> buildChildren(child, parentToChildren));
    }

    @Override
    public PageResponse<SpaceNavDTO> getNavsByUserId(Long userId, SpaceNavQueryDTO spaceNavQueryDTO, int page, int size) {
        List<Boolean> status = spaceNavQueryDTO.getStatus();
        // 将布尔值列表转换为整数列表
        List<Integer> intStatus = status != null
            ? status.stream().map(b -> b ? 0 : 1).toList()
            : null;
        if (intStatus != null && intStatus.isEmpty()) {
            intStatus = null;
        }
        spaceNavQueryDTO.setIntStatus(intStatus);
        // 构建查询条件
        Specification<SpaceNav> spec = new SpaceNavSpecs(spaceNavQueryDTO);
        // 执行分页查询
        Page<SpaceNavDTO> resultPage = spaceNavRepository.getAllNavs(spec, PageRequest.of(page, size));
        // 转换为 DTO 并封装成 PageResponse 返回
        return PageResponse.from(resultPage);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String addNavMenu(Long userId, SpaceNavDTO spaceNavDTO) {
        if (spaceNavDTO == null || spaceNavDTO.getNavName().isEmpty()) {
            throw new BusinessException(ResponseCodeEnum.CUSTOM_ERROR_1001.getCode(), "导航名称不能为空");
        }
        SpaceNav spaceNav = SpaceNavConvertor.INSTANCE.convertDtoToEntity(spaceNavDTO);
        // 设置创建人和更新人
        spaceNav.setCreateBy(userId);
        spaceNav.setUpdateBy(userId);
        // 保存实体到数据库
        SpaceNav createNav = spaceNavRepository.save(spaceNav);
        String message = createNav.getId() != null ? "导航添加成功" : "导航添加失败";
        logger.warn("{}：{}", message, spaceNav.getNavName());
        return message;
    }

}

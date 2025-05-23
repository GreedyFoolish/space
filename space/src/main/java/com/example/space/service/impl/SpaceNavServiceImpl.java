package com.example.space.service.impl;

import com.example.space.dto.SpaceNavTreeDTO;
import com.example.space.repository.SpaceNavRepository;
import com.example.space.service.SpaceNavService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
    // 注入SpaceNavRepository，用于查询导航数据
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
        parent.setChildren(children);
        children.forEach(child -> buildChildren(child, parentToChildren));
    }

}

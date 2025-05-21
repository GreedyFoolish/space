package com.example.space.service;

import com.example.space.dto.SpaceNavTreeDTO;
import com.example.space.repository.SpaceNavRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SpaceNavService {
    // 日志记录器
    private static final Logger logger = LoggerFactory.getLogger(SpaceNavService.class);

    private final SpaceNavRepository spaceNavRepository;

    public SpaceNavService(SpaceNavRepository sacenavRepository) {
        this.spaceNavRepository = sacenavRepository;
    }

    public List<SpaceNavTreeDTO> buildNavTree(Long roleId) {
        List<SpaceNavTreeDTO> allNavs = spaceNavRepository.findUserNavsByRoleId(roleId);

        Map<Long, SpaceNavTreeDTO> navMap = new HashMap<>();
        allNavs.forEach(nav -> navMap.put(nav.getId(), nav));

        logger.info("获取用户导航树成功");
        return allNavs.stream()
                .filter(nav -> nav.getParentId() == null || nav.getParentId() == 0)
                .peek(nav -> buildChildren(nav, navMap))
                .toList();
    }

    private void buildChildren(SpaceNavTreeDTO parent, Map<Long, SpaceNavTreeDTO> navMap) {
        List<SpaceNavTreeDTO> children = navMap.values().stream()
                .filter(nav -> parent.getId().equals(nav.getParentId()))
                .toList();
        parent.setChildren(children);
    }

}

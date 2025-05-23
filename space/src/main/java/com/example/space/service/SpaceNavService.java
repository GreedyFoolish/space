package com.example.space.service;

import com.example.space.dto.SpaceNavTreeDTO;

import java.util.List;

public interface SpaceNavService {

    /**
     * 根据角色ID构建导航树
     *
     * @param roleId 角色ID
     * @return 导航树
     */
    List<SpaceNavTreeDTO> buildNavTree(Long roleId);

}

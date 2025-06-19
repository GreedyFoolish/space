package com.example.space.service;

import com.example.space.dto.SpaceNavDTO;
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

    /**
     * 根据用户ID、导航名称和状态筛选导航菜单
     *
     * @param userId  用户ID
     * @param navName 导航名称（可选）
     * @param status  导航状态（可选）
     * @return 符合条件的导航列表
     */
    List<SpaceNavDTO> getNavsByUserId(Long userId, String navName, List<Boolean> status);

}

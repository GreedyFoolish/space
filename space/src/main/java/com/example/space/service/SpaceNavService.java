package com.example.space.service;

import com.example.space.dto.PageResponse;
import com.example.space.dto.SpaceNavDTO;
import com.example.space.dto.SpaceNavQueryDTO;
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
     * 根据用户ID和查询条件获取导航列表
     *
     * @param userId           用户ID
     * @param spaceNavQueryDTO 查询条件
     * @return 符合条件的导航列表
     */
    PageResponse<SpaceNavDTO> getNavsByUserId(Long userId, SpaceNavQueryDTO spaceNavQueryDTO, int page, int size);

    /**
     * 添加导航菜单
     *
     * @param userId      用户ID
     * @param spaceNavDTO 导航信息
     * @return 是否添加成功
     */
    String addNavMenu(Long userId, SpaceNavDTO spaceNavDTO);

}

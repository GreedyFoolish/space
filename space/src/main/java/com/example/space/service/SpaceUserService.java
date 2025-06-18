package com.example.space.service;

import com.example.space.dto.SpaceUserDTO;
import com.example.space.model.SpaceUser;
import jakarta.validation.Valid;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface SpaceUserService {

    /**
     * 注册用户
     *
     * @param user 用户信息
     */
    void registerUser(@Valid SpaceUser user);

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return 用户信息
     */
    UserDetails loadUserByUsername(String username);

    /**
     * 根据用户名查询用户
     *
     * @param userName 用户名
     * @return 用户列表
     */
    List<SpaceUser> findByUserName(String userName);

    /**
     * 查询所有用户
     *
     * @return 用户列表
     */
    List<SpaceUserDTO> getAllUsers();

    /**
     * 根据用户名查询用户
     *
     * @param name 用户名
     * @return 用户列表
     */
    List<SpaceUserDTO> getUsersByName(String name);

}

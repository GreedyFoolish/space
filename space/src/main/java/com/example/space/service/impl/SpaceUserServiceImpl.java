package com.example.space.service.impl;

import com.example.space.dto.SpaceUserDTO;
import com.example.space.enums.ResponseCodeEnum;
import com.example.space.enums.RoleEnum;
import com.example.space.exception.BusinessException;
import com.example.space.model.SpaceRole;
import com.example.space.model.SpaceUser;
import com.example.space.repository.SpaceRoleRepository;
import com.example.space.repository.SpaceUserRepository;
import com.example.space.service.SpaceUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpaceUserServiceImpl implements SpaceUserService, UserDetailsService {
    // 日志记录器
    private static final Logger logger = LoggerFactory.getLogger(SpaceUserServiceImpl.class);
    // 用户数据访问对象
    private final SpaceUserRepository spaceUserRepository;
    // 角色数据访问对象
    private final SpaceRoleRepository spaceRoleRepository;
    // BCrypt密码编码器
    private final PasswordEncoder passwordEncoder;

    public SpaceUserServiceImpl(SpaceUserRepository spaceUserRepository,
                                SpaceRoleRepository spaceRoleRepository,
                                PasswordEncoder passwordEncoder) {
        this.spaceUserRepository = spaceUserRepository;
        this.spaceRoleRepository = spaceRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void registerUser(SpaceUser user) {
        // 检查用户名是否已存在
        List<SpaceUser> users = spaceUserRepository.findByUserName(user.getUserName());
        if (!users.isEmpty()) {
            throw new BusinessException(ResponseCodeEnum.CUSTOM_ERROR_1005.getCode(), "用户已存在");
        }
        // 检查角色是否存在
        SpaceRole userRole = user.getRoleId();
        SpaceRole role;
        if (userRole == null) {
            // 如果 userRole 为空，默认使用普通用户角色
            role = spaceRoleRepository.findById(3L)
                .orElseThrow(() -> new BusinessException(ResponseCodeEnum.CUSTOM_ERROR_1006.getCode(), "默认角色不存在"));
        } else {
            // 否则根据提供的 userRole 查询角色
            role = spaceRoleRepository.findById(userRole.getId())
                .orElseThrow(() -> new BusinessException(ResponseCodeEnum.CUSTOM_ERROR_1006.getCode(), "角色不存在"));
        }
        // 设置用户角色
        user.setRoleId(role);
        // 加密密码
        String encodedPassword = passwordEncoder.encode(user.getUserPassword());
        user.setUserPassword(encodedPassword);
        // 保存用户
        SpaceUser savedUser = spaceUserRepository.save(user);
        if (savedUser == null || savedUser.getId() == null) {
            throw new BusinessException(ResponseCodeEnum.CUSTOM_ERROR_1005.getCode(), "用户注册失败");
        }
        logger.info("用户注册成功，用户ID：{}", savedUser.getId());
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        // 查询用户
        List<SpaceUser> users = spaceUserRepository.findByUserName(username);
        // 如果用户名不存在，抛出异常
        if (users.isEmpty()) {
            throw new BusinessException(ResponseCodeEnum.CUSTOM_ERROR_1005.getCode(), "未查询到该用户");
        }
        // 获取用户角色
        SpaceUser user = users.get(0);
        if (user != null) {
            // 获取用户角色
            SpaceRole userRole = user.getRoleId();
            // 获取角色名
            String roleName = userRole.getRoleName();
            // 获取角色枚举信息
            RoleEnum roleEnum = RoleEnum.fromAuthCode(userRole.getAuthCode());
            // 获取角色名
            String role = roleEnum.getAuthority();
            logger.info("用户名：{}，用户角色：{}，角色权限：{}", username, roleName, role);
            // 创建用户详情对象
            return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getUserPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(role))
            );
        }
        return null;
    }

    @Override
    public List<SpaceUser> findByUserName(String userName) {
        return spaceUserRepository.findByUserName(userName);
    }

    @Override
    public List<SpaceUserDTO> getAllUsers() {
        return spaceUserRepository.findAll()
            .stream()
            .map(SpaceUserDTO::new)
            .collect(Collectors.toList());
    }

    @Override
    public List<SpaceUserDTO> getUsersByName(String name) {
        return spaceUserRepository.findByUserName(name)
            .stream()
            .map(SpaceUserDTO::new)
            .collect(Collectors.toList());
    }

}

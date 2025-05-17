package com.example.space.service;

import com.example.space.enums.ResponseCodeEnum;
import com.example.space.enums.RoleEnum;
import com.example.space.exception.BusinessException;
import com.example.space.model.User;
import com.example.space.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getUsersByName(String name) {
        return userRepository.findByName(name);
    }

    public void registerUser(User user) {
        // 检查用户名是否已存在
        List<User> users = getUsersByName(user.getName());
        if (!users.isEmpty()) {
            throw new BusinessException(ResponseCodeEnum.CUSTOM_ERROR_1005.getCode(), "用户已存在");
        }

        // 对密码进行加密
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encoded = encoder.encode(user.getPassword());
        user.setPassword(encoded);

        // 保存用户
        User savedUser = userRepository.save(user);
        if (savedUser == null || savedUser.getId() == null) {
            // 用户注册失败
            throw new BusinessException(ResponseCodeEnum.CUSTOM_ERROR_1005.getCode(), "用户注册失败");
        }
    }

    public UserDetails loadUserByUsername(String username) {
        List<User> users = getUsersByName(username);

        if (users.isEmpty()) {
            throw new BusinessException(ResponseCodeEnum.CUSTOM_ERROR_1005.getCode(), "未查询到该用户");
        }

        // 获取用户信息
        User user = users.get(0);
        // 获取角色枚举信息
        RoleEnum roleEnum = RoleEnum.fromAuthCode(user.getAuthCode());
        // 获取角色名
        String role = roleEnum.getAuthority();

        return new org.springframework.security.core.userdetails.User(
                user.getName(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(role))
        );
    }

}

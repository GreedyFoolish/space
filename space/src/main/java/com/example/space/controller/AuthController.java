package com.example.space.controller;

import com.example.space.model.ResponseEntity;
import com.example.space.model.User;
import com.example.space.service.UserService;
import com.example.space.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "认证管理", description = "用户登录及 JWT 生成")
@AllArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    @PostMapping("/login")
    @Operation(summary = "用户登录", description = "通过用户名和密码获取 JWT")
    @ApiResponse(responseCode = "200", description = "成功返回 JWT")
    public ResponseEntity<Map<String, String>> login(@Parameter(description = "用户信息") @RequestBody User user) {
        // 进行身份验证
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword())
        );
        // 如果认证成功，设置 SecurityContext
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 获取用户角色
        String role = "USER";
        // 生成 JWT 令牌
        String token = jwtUtil.generateToken(user.getName(), role);
        // 返回包含令牌的响应
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return ResponseEntity.success(response);
    }

}

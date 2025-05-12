package com.example.space.controller;

import com.example.space.enums.ResponseCodeEnum;
import com.example.space.model.ResponseEntity;
import com.example.space.model.User;
import com.example.space.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@Tag(name = "用户管理", description = "用户相关操作接口")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @Operation(summary = "用户注册", description = "")
    @ApiResponse(responseCode = "200", description = "")
    public ResponseEntity<String> register(@Parameter(description = "用户信息") @Valid @RequestBody User user) {
        userService.registerUser(user);
        return ResponseEntity.custom(ResponseCodeEnum.SUCCESS.getCode(), "注册成功", null);
    }

    @GetMapping(value = "/all", produces = "application/json")
    @Operation(summary = "查询所有用户", description = "获取用户列表")
    @ApiResponse(responseCode = "200", description = "返回所有用户列表")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            throw new RuntimeException("No users found");
        }
        return ResponseEntity.success(users);
    }

    @GetMapping(value = "/{name}", produces = "application/json")
    @Operation(summary = "通过用户名查询用户", description = "返回与给定名称匹配的用户列表")
    @ApiResponse(responseCode = "200", description = "返回与给定名称匹配的用户列表")
    public ResponseEntity<List<User>> getUsersByName(
            @Parameter(description = "用户名称", required = true) @PathVariable String name
    ) {
        if (name == null || name.trim().isEmpty()) {
            throw new RuntimeException("Invalid user name");
        }
        List<User> users = userService.getUsersByName(name);
        if (users.isEmpty()) {
            throw new RuntimeException("No users found with name: " + name);
        }
        return ResponseEntity.success(users);
    }

}

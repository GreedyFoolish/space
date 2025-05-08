package com.example.space.controller;

import com.example.space.model.User;
import com.example.space.server.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping(value = "/all", produces = "application/json")
    @Operation(summary = "查询所有用户", description = "获取用户列表")
    @ApiResponse(responseCode = "200", description = "成功获取用户列表",
            content = @Content(schema = @Schema(implementation = User.class)))
    public List<User> getAllUsers() {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            throw new RuntimeException("No users found");
        }
        return users;
    }

    @GetMapping("/{name}")
    @Operation(summary = "通过用户名查询用户", description = "返回与给定名称匹配的用户列表")
    @ApiResponse(responseCode = "200", description = "返回与给定名称匹配的用户列表",
            content = @Content(schema = @Schema(implementation = User.class)))
    public List<User> getUsersByName(
            @Parameter(description = "用户名称", required = true) @PathVariable String name
    ) {
        if (name == null || name.trim().isEmpty()) {
            throw new RuntimeException("Invalid user name");
        }
        List<User> users = userService.getUsersByName(name);
        if (users.isEmpty()) {
            throw new RuntimeException("No users found with name: " + name);
        }
        return users;
    }
}

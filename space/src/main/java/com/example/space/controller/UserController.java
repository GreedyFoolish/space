package com.example.space.controller;

import com.example.space.model.ResponseEntity;
import com.example.space.model.User;
import com.example.space.server.UserService;
import io.swagger.v3.oas.annotations.Operation;
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

    @GetMapping
    @Operation(summary = "查询所有用户", description = "返回所有用户列表")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            return ResponseEntity.failure("No users found");
        }
        return ResponseEntity.success(users);
    }

    @GetMapping("/{name}")
    @Operation(summary = "通过用户名查询用户", description = "返回与给定名称匹配的用户列表")
    public ResponseEntity<List<User>> getUsersByName(@PathVariable String name) {
        if (name == null || name.trim().isEmpty()) {
            return ResponseEntity.failure("No invalid user name");
        }
        List<User> users = userService.getUsersByName(name);
        if (users.isEmpty()) {
            return ResponseEntity.failure("No users found with name: " + name);
        }
        return ResponseEntity.success(users);
    }
}

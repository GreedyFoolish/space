package com.example.space.controller;

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
@Tag(name = "User Management", description = "Operations for managing users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{name}")
    @Operation(summary = "Get users by name", description = "Retrieve a list of users matching the given name")
    public List<User> getUsersByName(@PathVariable String name) {
        System.out.println("name = " + name);
        return userService.getUsersByName(name);
    }
}

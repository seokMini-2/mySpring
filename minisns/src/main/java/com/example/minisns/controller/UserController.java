package com.example.minisns.controller;

import com.example.minisns.domain.User;
import com.example.minisns.dto.CreateUserRequest;
import com.example.minisns.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public User createUser(@RequestBody CreateUserRequest request) {
        return userService.create(request.getUsername());
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.findAll();
    }
}

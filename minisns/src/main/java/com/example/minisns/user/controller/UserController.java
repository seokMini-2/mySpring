package com.example.minisns.user.controller;

import com.example.minisns.user.dto.CreateUserRequest;
import com.example.minisns.user.dto.UserResponse;
import com.example.minisns.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserResponse createUser(@RequestBody CreateUserRequest request) {
        return userService.create(request.username(), request.password());
    }

    @GetMapping
    public List<UserResponse> getUsers() {
        return userService.getUsers();
    }
}

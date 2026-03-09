package com.example.minisns.controller;

import com.example.minisns.dto.CreateUserRequest;
import com.example.minisns.dto.UserResponse;
import com.example.minisns.service.UserService;
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
        return userService.create(request.username());
    }

    @GetMapping
    public List<UserResponse> getUsers() {
        return userService.getUsers();
    }
}

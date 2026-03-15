package com.example.minisns.user.controller;

import com.example.minisns.user.controller.docs.UserApiDocs;
import com.example.minisns.user.dto.CreateUserRequest;
import com.example.minisns.user.dto.UserResponse;
import com.example.minisns.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User", description = "회원 API")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController implements UserApiDocs {

    private final UserService userService;

    @Operation(summary = "회원 생성", description = "새로운 회원을 생성합니다.")
    @PostMapping
    @Override
    public UserResponse createUser(@Valid @RequestBody CreateUserRequest request) {
        return userService.create(request.username());
    }

    @Operation(summary = "회원 조회", description = "모든 회원을 조회합니다.")
    @GetMapping
    public List<UserResponse> getUsers() {
        return userService.getUsers();
    }

    @Operation(summary = "특정 회원 조회", description = "회원 ID로 특정 회원을 조회합니다.")
    @GetMapping("/{id}")
    @Override
    public UserResponse getUser(@PathVariable("id") Long userId) {
        return userService.getUserById(userId);
    }
}

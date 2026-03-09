package com.example.minisns.user.dto;

import com.example.minisns.user.domain.User;

public record UserResponse(Long id, String username) {
    public static UserResponse toUserResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername());
    }
}

package com.example.minisns.dto;

import com.example.minisns.domain.User;

public record UserResponse(Long id, String username) {
    public static UserResponse toUserResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername());
    }

}

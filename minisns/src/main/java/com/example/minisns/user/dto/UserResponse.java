package com.example.minisns.user.dto;

import com.example.minisns.user.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "회원 응답")
public record UserResponse(

        @Schema(description = "회원 ID", example = "1")
        Long id,

        @Schema(description = "회원 이름", example = "seokmin")
        String username) {
    public static UserResponse from(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername());
    }
}

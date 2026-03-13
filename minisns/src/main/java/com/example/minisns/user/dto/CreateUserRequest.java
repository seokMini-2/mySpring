package com.example.minisns.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserRequest(
        @NotBlank(message = "username은 비어 있을 수 없습니다.")
        @Size(max = 20, message = "username은 20자를 초과할 수 없습니다.")
        String username) {
}

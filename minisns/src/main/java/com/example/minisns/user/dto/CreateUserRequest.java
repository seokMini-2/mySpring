package com.example.minisns.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "회원 생성 요청")
public record CreateUserRequest(
        @Schema(description = "회원 이름", example = "seokmin")
        @NotBlank(message = "username은 비어 있을 수 없습니다.")
        @Size(max = 20, message = "username은 20자를 초과할 수 없습니다.")
        String username) {
}

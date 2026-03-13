package com.example.minisns.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateCommentRequest(

        @NotNull(message = "userId는 필수입니다.")
        Long userId,

        @NotBlank(message = "댓글 내용은 비어있을 수 없습니다.")
        @Size(max = 300, message = "댓글은 300자를 초과할 수 없습니다.")
        String content
) {
}
package com.example.minisns.dto;

public record CreateCommentRequest(
        Long postId,
        Long userId,
        String content
) {
}

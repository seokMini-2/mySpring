package com.example.minisns.dto;

public record CreateCommentRequest(
        Long userId,
        String content
) {
}
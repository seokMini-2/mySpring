package com.example.minisns.comment.dto;

public record CreateCommentRequest(
        Long userId,
        String content
) {
}
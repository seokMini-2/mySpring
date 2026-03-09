package com.example.minisns.comment.dto;


import com.example.minisns.comment.domain.Comment;

public record CommentResponse(
        Long id,
        Long postId,
        Long userId,
        String username,
        String content
) {
    public static CommentResponse toResponse(Comment comment) {
        return new CommentResponse(
                comment.getId(),
                comment.getPost().getId(),
                comment.getUser().getId(),
                comment.getUser().getUsername(),
                comment.getContent()
        );
    }
}

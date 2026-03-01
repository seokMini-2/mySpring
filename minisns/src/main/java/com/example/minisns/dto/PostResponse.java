package com.example.minisns.dto;

import com.example.minisns.domain.Post;

public record PostResponse(
        Long id,
        Long userId,
        String username,
        String title,
        String content
) {
    public static PostResponse toResponse(Post post) {
        return new PostResponse(
                post.getId(),
                post.getUser().getId(),
                post.getUser().getUsername(),
                post.getTitle(),
                post.getContent()
        );
    }
}

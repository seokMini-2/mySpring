package com.example.minisns.post.dto;

import com.example.minisns.post.domain.Post;
import com.example.minisns.post.domain.PostType;

public record PostResponse(
        Long id,
        Long userId,
        String username,
        String title,
        String content,
        PostType postType
) {
    public static PostResponse toResponse(Post post) {
        return new PostResponse(
                post.getId(),
                post.getUser().getId(),
                post.getUser().getUsername(),
                post.getTitle(),
                post.getContent(),
                post.getPostType()
        );
    }
}

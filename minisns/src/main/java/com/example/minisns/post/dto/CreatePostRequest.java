package com.example.minisns.post.dto;

import com.example.minisns.post.domain.PostType;

public record CreatePostRequest(
        Long userId,
        String title,
        String content,
        PostType postType) {}


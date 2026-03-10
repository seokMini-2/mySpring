package com.example.minisns.post.dto;

import com.example.minisns.post.domain.PostType;

public record UpdatePostRequest(
        Long userId,//임시로 유저아이디를 넣었음.
        String title,
        String content,
        PostType postType) {
}

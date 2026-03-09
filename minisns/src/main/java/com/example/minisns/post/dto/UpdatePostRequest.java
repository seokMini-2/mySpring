package com.example.minisns.post.dto;

public record UpdatePostRequest(Long userId,//임시로 유저아이디를 넣었음.
                                String title,
                                String content) {
}

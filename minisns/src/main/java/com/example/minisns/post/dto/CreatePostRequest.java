package com.example.minisns.post.dto;

import com.example.minisns.post.domain.PostType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreatePostRequest(

        @NotNull(message = "userId는 필수입니다.")
        Long userId,

        @NotBlank(message = "제목은 비어있을 수 없습니다.")
        @Size(max = 100, message = "제목은 100자를 초과할 수 없습니다.")
        String title,

        @NotBlank(message = "내용은 비어있을 수 없습니다.")
        @Size(max = 1000, message = "내용은 1000자를 초과할 수 없습니다.")
        String content,

        @NotNull(message = "postType은 필수입니다.")
        PostType postType) {}


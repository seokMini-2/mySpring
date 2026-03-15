package com.example.minisns.post.dto;

import com.example.minisns.post.domain.Post;
import com.example.minisns.post.domain.PostType;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "게시글 응답")
public record PostResponse(

        @Schema(description = "게시글 ID", example = "2")
        Long id,

        @Schema(description = "작성자 ID", example = "1")
        Long userId,

        @Schema(description = "작성자 이름", example = "seokmin")
        String username,

        @Schema(description = "게시글 제목", example = "제목입니다.")
        String title,

        @Schema(description = "게시글 내용", example = "내용입니다.")
        String content,

        @Schema(description = "게시글 타입", example = "GENERAL")
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

package com.example.minisns.post.dto;

import com.example.minisns.post.domain.PostType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "게시글 수정 요청")
public record UpdatePostRequest(

        @Schema(description = "수정 요청 회원 ID", example = "1")
        @NotNull(message = "userId는 필수입니다.")
        Long userId,

        @Schema(description = "수정할 제목", example = "수정될 제목")
        @NotBlank(message = "제목은 비어있을 수 없습니다.")
        @Size(max = 100, message = "제목은 100자를 초과할 수 없습니다.")
        String title,

        @Schema(description = "수정할 내용", example = "수정될 내용")
        @NotBlank(message = "내용은 비어있을 수 없습니다.")
        @Size(max = 1000, message = "내용은 1000자를 초과할 수 없습니다.")
        String content,

        @Schema(description = "수정할 게시글 타입", example = "QUESTION")
        @NotNull(message = "postType은 필수입니다.")
        PostType postType) {
}

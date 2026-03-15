package com.example.minisns.comment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "댓글 생성 요청")
public record CreateCommentRequest(

        @Schema(description = "작성자 ID", example = "1")
        @NotNull(message = "userId는 필수입니다.")
        Long userId,

        @Schema(description = "댓글 내용", example = "댓글 내용입니다잉")
        @NotBlank(message = "댓글 내용은 비어있을 수 없습니다.")
        @Size(max = 300, message = "댓글은 300자를 초과할 수 없습니다.")
        String content
) {
}
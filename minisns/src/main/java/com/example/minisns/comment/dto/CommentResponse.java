package com.example.minisns.comment.dto;


import com.example.minisns.comment.domain.Comment;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "댓글 응답")
public record CommentResponse(

        @Schema(description = "댓글 ID", example = "1")
        Long id,

        @Schema(description = "댓글의 게시글 ID", example = "1")
        Long postId,

        @Schema(description = "댓글 작성자 ID", example = "1")
        Long userId,

        @Schema(description = "댓글 작성자 이름", example = "seokmin")
        String username,

        @Schema(description = "댓글 내용", example = "댓글 내용입니다.")
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

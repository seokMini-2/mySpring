package com.example.minisns.comment.controller;

import com.example.minisns.comment.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Comment", description = "댓글 API")
@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "댓글 삭제", description = "조건을 만족하면 댓글을 삭제합니다.")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id,
                       @RequestParam("userId") Long userId) {
        commentService.delete(userId, id);
    }
}

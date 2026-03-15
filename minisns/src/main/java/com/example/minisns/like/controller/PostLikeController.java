package com.example.minisns.like.controller;

import com.example.minisns.like.service.PostLikeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Like", description = "좋아요 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/posts/{postId}/likes")
public class PostLikeController {

    private final PostLikeService postLikeService;

    @Operation(summary = "좋아요 추가", description = "게시글에 좋아요를 추가합니다.")
    @PostMapping
    public void like(@PathVariable("postId") Long postId,
                     @RequestParam("userId") Long userId) {
        postLikeService.like(postId, userId);
    }

    @Operation(summary = "좋아요 개수 보기", description = "해당하는 게시글의 좋아요 개수를 보여줍니다.")
    @GetMapping("/count")
    public long count(@PathVariable("postId") Long postId) {
        return postLikeService.countLikes(postId);
    }

@Operation(summary = "좋아요 삭제", description = "본인이 누른 게시글의 좋아요를 삭제합니다.")
    @DeleteMapping
    public void unlike(@PathVariable("postId") Long postId,
                       @RequestParam("userId") Long userId) {
        postLikeService.unlike(postId, userId);
    }
}

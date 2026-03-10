package com.example.minisns.like.controller;

import com.example.minisns.like.service.PostLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts/{postId}/likes")
public class PostLikeController {

    private final PostLikeService postLikeService;

    @PostMapping
    public void like(@PathVariable("postId") Long postId,
                     @RequestParam("userId") Long userId) {
        postLikeService.like(postId, userId);
    }

    @GetMapping("/count")
    public long count(@PathVariable("postId") Long postId) {
        return postLikeService.countLikes(postId);
    }


    @DeleteMapping
    public void unlike(@PathVariable("postId") Long postId,
                       @RequestParam("userId") Long userId) {
        postLikeService.unlike(postId, userId);
    }
}

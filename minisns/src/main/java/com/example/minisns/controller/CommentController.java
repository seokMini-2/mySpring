package com.example.minisns.controller;

import com.example.minisns.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
/*
    @PostMapping
    public CommentResponse create(@RequestBody CreateCommentRequest request) {
        return commentService.create(request.postId(), request.userId(), request.content());
    }

    @GetMapping("/posts/{postId}")
    public List<CommentResponse> getCommentsById(@PathVariable("postId") Long postId) {
        return commentService.getCommentsByPost(postId);
    }
    게시글에 달려있는 댓글을 조회하는기능이니까 PostController에 존재하면 된다;;
 */

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        commentService.delete(id);
    }
}

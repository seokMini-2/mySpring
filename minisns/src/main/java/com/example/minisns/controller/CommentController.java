package com.example.minisns.controller;

import com.example.minisns.dto.CommentResponse;
import com.example.minisns.dto.CreateCommentRequest;
import com.example.minisns.repository.CommentRepository;
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

    @PostMapping
    public CommentResponse create(@RequestBody CreateCommentRequest request) {
        return commentService.create(request.postId(), request.userId(), request.content());
    }

    @GetMapping("/posts/{postId}")
    public List<CommentResponse> getCommentsById(@PathVariable("postId") Long postId) {
        return commentService.getCommentsByPost(postId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        commentService.delete(id);
    }
}

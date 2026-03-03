package com.example.minisns.controller;

import com.example.minisns.dto.*;
import com.example.minisns.service.CommentService;
import com.example.minisns.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final CommentService commentService;

    public PostController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @PostMapping
    public PostResponse create(@RequestBody CreatePostRequest request) {
        return postService.create(request.userId(), request.title(), request.content());
    }

    @PostMapping("/{postId}/comments")
    public CommentResponse createComment(@PathVariable("postId") Long postId,
                                         @RequestBody CreateCommentRequest request) {
        return commentService.create(postId, request.userId(), request.content());
    }

    @GetMapping
    public Page<PostResponse> getPosts(Pageable pageable) {
        return postService.getPosts(pageable);
    }

    @GetMapping("/{id}")
    public PostResponse getPostById(@PathVariable("id") Long id) {
        return postService.getPost(id);
    }

    @GetMapping("/{postId}/comments")
    public Page<CommentResponse> getComments(@PathVariable("postId") Long postId, Pageable pageable) {
        return commentService.getCommentsByPost(postId, pageable);
    }

    @PutMapping("/{id}")
    public PostResponse updatePost(@PathVariable("id") Long id, @RequestBody UpdatePostRequest request) {
        return postService.update(id, request.title(), request.content());
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable("id") Long id) {
        postService.delete(id);
    }

}

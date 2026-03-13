package com.example.minisns.post.controller;

import com.example.minisns.comment.dto.CommentResponse;
import com.example.minisns.comment.dto.CreateCommentRequest;
import com.example.minisns.comment.service.CommentService;
import com.example.minisns.global.response.PageResponse;
import com.example.minisns.post.dto.CreatePostRequest;
import com.example.minisns.post.dto.PostResponse;
import com.example.minisns.post.dto.UpdatePostRequest;
import com.example.minisns.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final CommentService commentService;

    @PostMapping
    public PostResponse create(@Valid @RequestBody CreatePostRequest request) {
        return postService.create(
                request.userId(),
                request.title(),
                request.content(),
                request.postType());
    }

    @PostMapping("/{postId}/comments")
    public CommentResponse createComment(@PathVariable("postId") Long postId,
                                         @Valid @RequestBody CreateCommentRequest request) {
        return commentService.create(postId, request.userId(), request.content());
    }

    @GetMapping
    public PageResponse<PostResponse> getPosts(Pageable pageable) {
        return PageResponse.from(postService.getPosts(pageable));
    }

    @GetMapping("/{id}")
    public PostResponse getPostById(@PathVariable("id") Long id) {
        return postService.getPost(id);
    }

    @GetMapping("/{postId}/comments")
    public PageResponse<CommentResponse> getComments(@PathVariable("postId") Long postId, Pageable pageable) {
        return PageResponse.from(commentService.getCommentsByPost(postId, pageable));
    }

    //아이디 검증을 위해
    @PutMapping("/{id}")
    public PostResponse updatePost(@PathVariable("id") Long id, @Valid @RequestBody UpdatePostRequest request) {
        return postService.update(
                request.userId(),
                id,
                request.title(),
                request.content(),
                request.postType());
    }

    //아이디 검증을 위해
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable("id") Long id,
                           @RequestParam("userId") Long userId) {
        postService.delete(userId, id);
    }

}

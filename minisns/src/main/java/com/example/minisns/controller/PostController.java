package com.example.minisns.controller;

import com.example.minisns.domain.Post;
import com.example.minisns.dto.CreatePostRequest;
import com.example.minisns.dto.PostResponse;
import com.example.minisns.service.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/posts")
    public PostResponse create(@RequestBody CreatePostRequest request) {
        Post post = postService.create(request.getUserId(), request.getTitle(), request.getContent());
        return toResponse(post);
    }

    @GetMapping("/posts")
    public List<PostResponse> getPosts() {
        return postService.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private PostResponse toResponse(Post post) {
        return new PostResponse(
                post.getId(),
                post.getUser().getId(),
                post.getUser().getUsername(),
                post.getTitle(),
                post.getContent());
    }

}

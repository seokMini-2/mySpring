package com.example.minisns.controller;

import com.example.minisns.domain.Post;
import com.example.minisns.dto.CreatePostRequest;
import com.example.minisns.dto.PostResponse;
import com.example.minisns.dto.UpdatePostRequest;
import com.example.minisns.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/posts")
    public PostResponse create(@RequestBody CreatePostRequest request) {
        Post post = postService.create(request.userId(), request.title(), request.content());
        return toResponse(post);
    }

    @GetMapping("/posts")
    public List<PostResponse> getPosts() {
        return postService.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @GetMapping("/posts/{id}")
    public PostResponse getPostById(@PathVariable("id") Long id) {
        Post post = postService.findById(id);
        return toResponse(post);
    }

    @PutMapping("/posts/{id}")
    public PostResponse updatePost(@PathVariable("id") Long id, @RequestBody UpdatePostRequest request) {
        Post update = postService.update(id, request.title(), request.content());
        return toResponse(update);
    }

    @DeleteMapping("posts/{id}")
    public void deletePost(@PathVariable("id") Long id) {
        postService.delete(id);
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

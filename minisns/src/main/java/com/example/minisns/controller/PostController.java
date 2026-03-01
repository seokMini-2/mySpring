package com.example.minisns.controller;

import com.example.minisns.dto.CreatePostRequest;
import com.example.minisns.dto.PostResponse;
import com.example.minisns.dto.UpdatePostRequest;
import com.example.minisns.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public PostResponse create(@RequestBody CreatePostRequest request) {
        return postService.create(request.userId(), request.title(), request.content());
    }

    @GetMapping
    public List<PostResponse> getPosts() {
        return postService.getPosts();
    }

    @GetMapping("/{id}")
    public PostResponse getPostById(@PathVariable("id") Long id) {
        return postService.getPost(id);
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

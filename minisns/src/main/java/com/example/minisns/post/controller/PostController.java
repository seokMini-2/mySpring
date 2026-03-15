package com.example.minisns.post.controller;

import com.example.minisns.comment.dto.CommentResponse;
import com.example.minisns.comment.dto.CreateCommentRequest;
import com.example.minisns.comment.service.CommentService;
import com.example.minisns.global.response.PageResponse;
import com.example.minisns.post.controller.docs.PostApiDocs;
import com.example.minisns.post.dto.CreatePostRequest;
import com.example.minisns.post.dto.PostResponse;
import com.example.minisns.post.dto.UpdatePostRequest;
import com.example.minisns.post.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Post", description = "게시글 API")
@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController implements PostApiDocs {

    private final PostService postService;
    private final CommentService commentService;

    @Operation(summary = "게시글 생성", description = "새로운 게시글을 생성합니다.")
    @PostMapping
    @Override
    public PostResponse create(@Valid @RequestBody CreatePostRequest request) {
        return postService.create(
                request.userId(),
                request.title(),
                request.content(),
                request.postType());
    }

    @Operation(summary = "댓글 생성", description = "새로운 댓글을 작성합니다.")
    @PostMapping("/{postId}/comments")
    @Override
        public CommentResponse createComment(@PathVariable("postId") Long postId,
                                         @Valid @RequestBody CreateCommentRequest request) {
        return commentService.create(postId, request.userId(), request.content());
    }

    @Operation(summary = "게시글 목록 조회", description = "게시글 목록을 페이징하여 조회합니다.")
    @GetMapping
    @Override
    public PageResponse<PostResponse> getPosts(Pageable pageable) {
        return PageResponse.from(postService.getPosts(pageable));
    }

    @Operation(summary = "게시글 단건 조회", description = "게시글 ID로 게시글을 조회합니다.")
    @GetMapping("/{id}")
    @Override
    public PostResponse getPostById(@PathVariable("id") Long id) {
        return postService.getPost(id);
    }

    @Operation(summary = "댓글 조회", description = "게시글에 달린 댓글을 조회합니다.")
    @GetMapping("/{postId}/comments")
    @Override
    public PageResponse<CommentResponse> getComments(@PathVariable("postId") Long postId, Pageable pageable) {
        return PageResponse.from(commentService.getCommentsByPost(postId, pageable));
    }

    @Operation(summary = "게시글 수정", description = "조건을 만족하면 게시글을 수정합니다.")
    @PutMapping("/{id}")
    @Override
    public PostResponse updatePost(@PathVariable("id") Long id, @Valid @RequestBody UpdatePostRequest request) {
        return postService.update(
                request.userId(),
                id,
                request.title(),
                request.content(),
                request.postType());
    }

    @Operation(summary = "게시글 삭제", description = "조건을 만족하면 게시글을 삭제합니다.")
    @DeleteMapping("/{id}")
    @Override
    public void deletePost(@PathVariable("id") Long id,
                           @RequestParam("userId") Long userId) {
        postService.delete(userId, id);
    }

}

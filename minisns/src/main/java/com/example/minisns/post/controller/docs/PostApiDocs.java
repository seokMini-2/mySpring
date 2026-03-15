package com.example.minisns.post.controller.docs;

import com.example.minisns.comment.dto.CommentResponse;
import com.example.minisns.comment.dto.CreateCommentRequest;
import com.example.minisns.global.response.ErrorResponse;
import com.example.minisns.global.response.PageResponse;
import com.example.minisns.post.dto.CreatePostRequest;
import com.example.minisns.post.dto.PostResponse;
import com.example.minisns.post.dto.UpdatePostRequest;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface PostApiDocs {

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "게시글 생성 성공",
                    content = @Content(schema = @Schema(implementation = PostResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "잘못된 요청 또는 유효성 검증 실패",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "작성자를 찾을 수 없음",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    PostResponse create(@Valid @RequestBody CreatePostRequest request);

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "댓글 생성 성공",
                    content = @Content(schema = @Schema(implementation = CommentResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "잘못된 요청 또는 유효성 검증 실패",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "게시글 또는 사용자를 찾을 수 없음",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    CommentResponse createComment(@PathVariable("postId") Long postId,
                                  @Valid @RequestBody CreateCommentRequest request);

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "게시글 목록 페이징 조회 성공",
                    content = @Content(schema = @Schema(implementation = PageResponse.class)))
    })
    PageResponse<PostResponse> getPosts(Pageable pageable);

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "게시글 조회 성공",
                    content = @Content(schema = @Schema(implementation = PostResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "게시글을 찾을 수 없습니다.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    PostResponse getPostById(@PathVariable("id") Long id);

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "댓글 목록 조회 성공",
                    content = @Content(schema = @Schema(implementation = PageResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "게시글을 찾을 수 없음",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    PageResponse<CommentResponse> getComments(@PathVariable("postId") Long postId, Pageable pageable);

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "게시글 수정 성공",
                    content = @Content(schema = @Schema(implementation = PostResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "잘못된 요청 또는 작성자 불일치",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "게시글 또는 사용자를 찾을 수 없음",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    PostResponse updatePost(@PathVariable("id") Long id, @Valid @RequestBody UpdatePostRequest request);

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "400",
                    description = "잘못된 요청 또는 작성자 불일치",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "게시글을 찾을 수 없음",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    void deletePost(@PathVariable("id") Long id,
               @RequestParam("userId") Long userId);
}

package com.example.minisns.user.controller.docs;

import com.example.minisns.global.response.ErrorResponse;
import com.example.minisns.user.dto.CreateUserRequest;
import com.example.minisns.user.dto.UserResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserApiDocs {

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "회원 생성 성공",
                    content = @Content(schema = @Schema(implementation = UserResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "잘못된 요청",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "이미 사용중인 usename입니다.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    UserResponse createUser(@Valid @RequestBody CreateUserRequest request);

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "회원 조회 성공",
                    content = @Content(schema = @Schema(implementation = UserResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "회원을 찾을 수 없음",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    UserResponse getUser(@PathVariable("id") Long userId);

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "회원 목록 조회 성공",
                    content = @Content(
                            array = @ArraySchema(schema = @Schema(implementation = UserResponse.class))
                    ))
    })
    List<UserResponse> getUsers();

}

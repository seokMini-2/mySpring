package com.example.minisns.global.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "에러 응답")
public record ErrorResponse(

        @Schema(description = "HTTP 상태 코드", example = "404")
        int status,

        @Schema(description = "에러 코드 문구", example = "NOT_FOUND")
        String code,

        @Schema(description = "에러 메세지", example = "찾을 수 없다 이양반아.")
        String message
) {
    public static ErrorResponse of(int status, String code, String message) {
        return new ErrorResponse(status, code, message);
    }
}

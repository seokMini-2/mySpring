package com.example.minisns.global.response;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.domain.Page;

import java.util.List;

@Schema(description = "페이지 응답")
public record PageResponse<T>(

        @Schema(description = "데이터 목록")
        List<T> content,

        @Schema(description = "현재 페이지 번호", example = "0")
        int page,

        @Schema(description = "페이지 크기", example = "10")
        int size,

        @Schema(description = "전체 요소 수", example = "20")
        long totalElements,

        @Schema(description = "전체 페이지 수", example = "2")
        int totalPages,

        @Schema(description = "다음 페이지 여부", example = "true")
        boolean hasNext,

        @Schema(description = "전 페이지 여부", example = "false")
        boolean hasPrevious
) {
    public static <T> PageResponse<T> from(Page<T> page) {
        return new PageResponse<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.hasNext(),
                page.hasPrevious()
        );
    }
}

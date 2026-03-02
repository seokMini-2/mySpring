package com.example.minisns.exception;

public class CommentNotFoundException extends RuntimeException {
    public CommentNotFoundException(Long id) {
        super("댓글 없음: " + id);
    }
}

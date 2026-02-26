package com.example.minisns.dto;

public class PostResponse {

    private Long id;
    private Long userId;
    private String username;
    private String title;
    private String content;

    public PostResponse(Long id, Long userId, String username, String title, String content) {
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.title = title;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}

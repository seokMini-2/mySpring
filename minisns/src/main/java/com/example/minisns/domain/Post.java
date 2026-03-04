package com.example.minisns.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Post {

    @Id//게시글 아이디(기본키)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")//게시글을 작성한 유저의 아이디(외래키)
    private User user;

    protected Post() {}

    public Post(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

}

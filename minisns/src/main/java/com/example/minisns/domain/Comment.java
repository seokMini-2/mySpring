package com.example.minisns.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, length = 500)
    private String content;

    protected Comment() {}

    public Comment(Post post, User user, String content) {
        this.post = post;
        this.user = user;
        this.content = content;
    }
}

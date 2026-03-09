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

    private Comment(Post post, User user, String content) {
        this.post = post;
        this.user = user;
        this.content = content;
    }

    public static Comment create(Post post, User user, String content) {
        return new Comment(post, user, content);
    }

    //이 댓글을 댓글 작성자가 쓴건지 확인하기 위해
    public boolean isOwner(Long userId) {
        return this.user.getId().equals(userId);
    }
}

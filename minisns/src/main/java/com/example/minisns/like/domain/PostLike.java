package com.example.minisns.like.domain;

import com.example.minisns.post.domain.Post;
import com.example.minisns.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "post_like",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_post_like_post_user", columnNames = {"post_id", "user_id"})})
public class PostLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "post_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    protected PostLike() {
    }

    private PostLike(Post post, User user) {
        this.post = post;
        this.user = user;
    }

    public static PostLike create(Post post, User user) {
        return new PostLike(post, user);
    }
}

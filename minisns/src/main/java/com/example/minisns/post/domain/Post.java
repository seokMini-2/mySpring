package com.example.minisns.post.domain;

import com.example.minisns.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 1000)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    protected Post() {}

    private Post(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }

    //Post 생성 Post에서 직접 관리
    public static Post create(String title, String content, User user) {
        return new Post(title, content, user);
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    //추후 진짜 작성자인지 확인하기 위해서
    public boolean isOwner(Long userId) {
        return this.user.getId().equals(userId);
    }

}

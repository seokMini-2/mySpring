package com.example.minisns.user.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "users") // 테이블 이름 설정하기
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true) // 컬럼 규칙 설정
    private String username;

    protected User() {
    }

    private User(String username) {
        this.username = username;
    }

    public static User create(String username) {
        return new User(username);
    }
}

package com.example.minisns.service;

import com.example.minisns.domain.Post;
import com.example.minisns.domain.User;
import com.example.minisns.repository.PostRepository;
import com.example.minisns.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    // 서비스에서는 레퍼지토리 핸들링.
    public Post create(Long userId, String title, String content) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저 없음"));
        Post post = new Post(title, content, user);
        return postRepository.save(post);
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }
}

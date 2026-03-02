package com.example.minisns.service;

import com.example.minisns.domain.Post;
import com.example.minisns.domain.User;
import com.example.minisns.dto.PostResponse;
import com.example.minisns.exception.PostNotFoundException;
import com.example.minisns.exception.UserNotFoundException;
import com.example.minisns.repository.PostRepository;
import com.example.minisns.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    // 서비스에서는 레퍼지토리 핸들링.
    public PostResponse create(Long userId, String title, String content) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        Post post = new Post(title, content, user);
        Post newPost = postRepository.save(post);
        return PostResponse.toResponse(newPost);
    }

    @Transactional(readOnly = true)
    public PostResponse getPost(Long id) {
        return PostResponse.toResponse(findById(id));
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPosts() {
        return findAll().stream()
                .map(PostResponse::toResponse)
                .toList()
                ;
    }

    public PostResponse update(Long id, String title, String content) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id));
        post.update(title, content);
        return PostResponse.toResponse(post);
    }

    public void delete(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id));
        postRepository.delete(post);
    }

    // =============== PRIVATE METHOD =================

    private List<Post> findAll() {
        return postRepository.findAll();
    }

    private Post findById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id));
    }

}

package com.example.minisns.service;

import com.example.minisns.domain.Post;
import com.example.minisns.domain.User;
import com.example.minisns.dto.PostResponse;
import com.example.minisns.exception.PostNotFoundException;
import com.example.minisns.exception.UserNotFoundException;
import com.example.minisns.repository.PostRepository;
import com.example.minisns.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostResponse create(Long userId, String title, String content) {
        User user = findUserById(userId);
        Post post = Post.create(title, content, user);
        Post savedPost = postRepository.save(post);
        return PostResponse.toResponse(savedPost);
    }

    @Transactional(readOnly = true)
    public PostResponse getPost(Long id) {
        return PostResponse.toResponse(findPostById(id));
    }

    @Transactional(readOnly = true)
    public Page<PostResponse> getPosts(Pageable pageable) {
        return postRepository.findPostResponses(pageable);
    }

    public PostResponse update(Long loginUserId, Long postId, String title, String content) {
        Post post = findPostById(postId);
        validatePostOwner(loginUserId, post);
        post.update(title, content); // Dirty Checking(save 하지않아도 반영됨)
        return PostResponse.toResponse(post);
    }

    public void delete(Long loginUserId, Long postId) {
        Post post = findPostById(postId);
        validatePostOwner(loginUserId, post);
        postRepository.delete(post);
    }

    // =============== PRIVATE METHOD =================

    private User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    private Post findPostById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId));
    }

    private void validatePostOwner(Long loginUserId, Post post) {
        if (!post.isOwner(loginUserId)) {
            throw new IllegalArgumentException("게시글 작성자만 수정/삭제할 수 있습니다.");
        }
    }
}

package com.example.minisns.like.service;

import com.example.minisns.global.exception.PostNotFoundException;
import com.example.minisns.global.exception.UserNotFoundException;
import com.example.minisns.like.domain.PostLike;
import com.example.minisns.like.repository.PostLikeRepository;
import com.example.minisns.post.domain.Post;
import com.example.minisns.post.repository.PostRepository;
import com.example.minisns.user.domain.User;
import com.example.minisns.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostLikeService {

    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public void like(Long postId, Long userId) {
        Post post = findPostById(postId);
        User user = findUserById(userId);

        existsByPostIdAndUserId(postId, userId);

        PostLike postLike = PostLike.create(post, user);
        postLikeRepository.save(postLike);
    }

    @Transactional(readOnly = true)
    public long countLikes(Long postId) {
        existsById(postId);
        return postLikeRepository.countByPostId(postId);
    }

    public void unlike(Long postId, Long userId) {
        PostLike postLike = findByPostIdAndUserId(postId, userId);
        postLikeRepository.delete(postLike);
    }

    // =============== PRIVATE METHOD =================

    private Post findPostById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId));
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    private PostLike findByPostIdAndUserId(Long postId, Long userId) {
        return postLikeRepository.findByPostIdAndUserId(postId, userId)
                .orElseThrow(() -> new IllegalArgumentException("좋아요를 누르지 않은 게시물입니다."));
    }

    private void existsByPostIdAndUserId(Long postId, Long userId) {
        if (postLikeRepository.existsByPostIdAndUserId(postId, userId)) {
            throw new IllegalArgumentException("이미 좋아요를 누른 상태입니다.");
        }
    }

    private void existsById(Long postId) {
        if (!postRepository.existsById(postId)) {
            throw new PostNotFoundException(postId);
        }
    }

}

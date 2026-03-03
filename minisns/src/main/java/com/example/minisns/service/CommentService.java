package com.example.minisns.service;

import com.example.minisns.domain.Comment;
import com.example.minisns.domain.Post;
import com.example.minisns.domain.User;
import com.example.minisns.dto.CommentResponse;
import com.example.minisns.exception.CommentNotFoundException;
import com.example.minisns.exception.PostNotFoundException;
import com.example.minisns.exception.UserNotFoundException;
import com.example.minisns.repository.CommentRepository;
import com.example.minisns.repository.PostRepository;
import com.example.minisns.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public CommentResponse create(Long postId, Long userId, String content) {
        Post post = findByPostId(postId);
        User user = findByUserId(userId);
        Comment comment = new Comment(post, user, content);
        return CommentResponse.toResponse(commentRepository.save(comment));
    }

    @Transactional(readOnly = true)
    public Page<CommentResponse> getCommentsByPost(Long postId, Pageable pageable) {
        if (!postRepository.existsById(postId)) {
            throw new PostNotFoundException(postId);
        }
        return commentRepository.findByPostId(postId, pageable)
                .map(CommentResponse::toResponse);
    }

    public void delete(Long id) {
        Comment comment = findByCommentId(id);
        commentRepository.delete(comment);
    }

    private Post findByPostId(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId));
    }

    private User findByUserId(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    private Comment findByCommentId(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException(id));
    }

}

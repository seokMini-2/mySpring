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
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        Comment comment = new Comment(post, user, content);
        return CommentResponse.toResponse(commentRepository.save(comment));
    }

    @Transactional(readOnly = true)
    public List<CommentResponse> getCommentsByPost(Long postId) {
        postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId));
        return commentRepository.findByPostId(postId)
                .stream()
                .map(CommentResponse::toResponse)
                .toList();
    }

    public void delete(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException(id));
        commentRepository.delete(comment);
    }

}

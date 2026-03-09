package com.example.minisns.comment.service;

import com.example.minisns.comment.domain.Comment;
import com.example.minisns.comment.dto.CommentResponse;
import com.example.minisns.comment.repository.CommentRepository;
import com.example.minisns.global.exception.CommentNotFoundException;
import com.example.minisns.global.exception.PostNotFoundException;
import com.example.minisns.global.exception.UserNotFoundException;
import com.example.minisns.post.domain.Post;
import com.example.minisns.post.repository.PostRepository;
import com.example.minisns.user.domain.User;
import com.example.minisns.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public CommentResponse create(Long postId, Long userId, String content) {
        Post post = findPostById(postId);
        User user = findUserById(userId);
        Comment comment = Comment.create(post, user, content);
        return CommentResponse.toResponse(commentRepository.save(comment));
    }

    @Transactional(readOnly = true)
    public Page<CommentResponse> getCommentsByPost(Long postId, Pageable pageable) {
        if (!postRepository.existsById(postId)) {
            throw new PostNotFoundException(postId);
        }
        return commentRepository.findResponsesByPostId(postId, pageable); // Page<Comment>반환
    }

    public void delete(Long loginUserId, Long commentId) {
        Comment comment = findCommentById(commentId);
        validateCommentOwner(loginUserId, comment);
        commentRepository.delete(comment);
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

    private Comment findCommentById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException(id));
    }

    private void validateCommentOwner(Long loginUserId, Comment comment) {
        if (!comment.isOwner(loginUserId)) {
            throw new IllegalArgumentException("댓글 작성자만 삭제할 수 있습니다.");
        }
    }
}

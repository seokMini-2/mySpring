package com.example.minisns.comment.repository;

import com.example.minisns.comment.domain.Comment;
import com.example.minisns.comment.dto.CommentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("""
            select new com.example.minisns.comment.dto.CommentResponse(
            c.id,
            c.post.id,
            c.user.id,
            c.user.username,
            c.content
            )from Comment c
            where c.post.id= :postId""")
    Page<CommentResponse> findResponsesByPostId(@Param("postId") Long postId, Pageable pageable);

}

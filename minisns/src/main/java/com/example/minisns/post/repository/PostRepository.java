package com.example.minisns.post.repository;

import com.example.minisns.post.domain.Post;
import com.example.minisns.post.dto.PostResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("""
            select new com.example.minisns.post.dto.PostResponse(
            p.id,
            u.id,
            u.username,
            p.title,
            p.content
            )
            from Post p
            join p.user u""")
    Page<PostResponse> findPostResponses(Pageable pageable);
}

package com.example.minisns.repository;

import com.example.minisns.domain.Post;
import com.example.minisns.dto.PostResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("""
            select new com.example.minisns.dto.PostResponse(
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

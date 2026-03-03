package com.example.minisns.controller;

import com.example.minisns.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        commentService.delete(id);
    }
}

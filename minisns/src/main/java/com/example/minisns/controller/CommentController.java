package com.example.minisns.controller;

import com.example.minisns.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id,
                       @RequestParam("userId") Long userId) {
        commentService.delete(userId, id);
    }
}

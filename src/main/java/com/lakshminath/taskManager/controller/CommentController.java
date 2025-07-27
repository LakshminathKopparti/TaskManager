package com.lakshminath.taskManager.controller;

import com.lakshminath.taskManager.model.Comment;
import com.lakshminath.taskManager.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin // Allow all origins; you can specify origins if needed
@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<Comment> addComment(@RequestParam Long taskId, @RequestParam Long userId, @RequestParam String content) {
        Comment comment = commentService.addComment(taskId, userId, content);
        return ResponseEntity.ok(comment);
    }

    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<Comment>> getCommentsByTask(@PathVariable Long taskId) {
        List<Comment> comments = commentService.getCommentsByTask(taskId);
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Comment>> getCommentsByUser(@PathVariable Long userId) {
        List<Comment> comments = commentService.getCommentsByUser(userId);
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/task/{taskId}/user/{userId}")
    public ResponseEntity<List<Comment>> getCommentsByUserForTask(@PathVariable Long userId, @PathVariable Long taskId) {
        List<Comment> comments = commentService.getCommentsByUserForTask(userId, taskId);
        return ResponseEntity.ok(comments);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long commentId, @RequestParam String content) {
        Comment updated = commentService.updateComment(commentId, content);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }
} 
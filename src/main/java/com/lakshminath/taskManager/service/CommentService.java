package com.lakshminath.taskManager.service;

import com.lakshminath.taskManager.model.Comment;
import com.lakshminath.taskManager.model.Task;
import com.lakshminath.taskManager.model.User;
import com.lakshminath.taskManager.repo.CommentRepo;
import com.lakshminath.taskManager.repo.TaskRepo;
import com.lakshminath.taskManager.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    private UserRepo userRepo;

    public Comment addComment(Long taskId, Long userId, String content) {
        if (content == null || content.trim().isEmpty()) {
            throw new RuntimeException("Comment content cannot be empty");
        }
        Task task = taskRepo.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Comment comment = new Comment();
        comment.setTask(task);
        comment.setAuthor(user);
        comment.setContent(content);
        return commentRepo.save(comment);
    }

    public List<Comment> getCommentsByTask(Long taskId) {
        Task task = taskRepo.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));
        return commentRepo.findByTask(task);
    }

    public List<Comment> getCommentsByUser(Long userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return commentRepo.findByAuthor(user);
    }

    public List<Comment> getCommentsByUserForTask(Long userId, Long taskId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Task task = taskRepo.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));
        return commentRepo.findByTask(task).stream()
                .filter(comment -> comment.getAuthor() != null && comment.getAuthor().getUserId().equals(userId))
                .toList();
    }

    public Comment updateComment(Long commentId, String newContent) {
        if (newContent == null || newContent.trim().isEmpty()) {
            throw new RuntimeException("Comment content cannot be empty");
        }
        Comment comment = commentRepo.findById(commentId).orElseThrow(() -> new RuntimeException("Comment not found"));
        comment.setContent(newContent);
        return commentRepo.save(comment);
    }

    public void deleteComment(Long commentId) {
        commentRepo.deleteById(commentId);
    }
}

package com.lakshminath.taskManager.repo;

import com.lakshminath.taskManager.model.Comment;
import com.lakshminath.taskManager.model.Task;
import com.lakshminath.taskManager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {
    List<Comment> findByTask(Task task);
    List<Comment> findByAuthor(User author);
} 

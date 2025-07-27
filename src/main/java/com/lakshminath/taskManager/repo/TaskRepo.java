package com.lakshminath.taskManager.repo;

import com.lakshminath.taskManager.model.Task;
import com.lakshminath.taskManager.model.TaskStatus;
import com.lakshminath.taskManager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {
    
    List<Task> findByAssignedTo(User assignedTo);
    List<Task> findByAssignedBy(User assignedBy);
    List<Task> findByStatus(TaskStatus status);
    List<Task> findByTeam_Id(Long teamId);
    List<Task> findByProject_ProjectId(Long projectId);
    List<Task> findByOwner_UserId(Long userId);
    List<Task> findByAssignedTo_UserId(Long userId);
    @Query("SELECT COUNT(t) FROM Task t WHERE t.assignedTo.userId = :userId")
    int countByAssignedToUserId(@Param("userId") Long userId);

}


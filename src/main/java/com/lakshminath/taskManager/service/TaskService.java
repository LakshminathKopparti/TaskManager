package com.lakshminath.taskManager.service;

import com.lakshminath.taskManager.model.Task;
import com.lakshminath.taskManager.model.TaskStatus;
import com.lakshminath.taskManager.model.User;
import com.lakshminath.taskManager.repo.TaskRepo;
import com.lakshminath.taskManager.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    private UserRepo userRepo;

    public Task createTask(Task task) {
        return taskRepo.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public List<Task> getTasksByTeamId(Long teamId) {
        return taskRepo.findByTeam_Id(teamId);
    }

    public List<Task> getTasksByProjectId(Long projectId) {
        return taskRepo.findByProject_ProjectId(projectId);
    }

    public List<Task> getTasksByOwnerId(Long ownerId) {
        return taskRepo.findByOwner_UserId(ownerId);
    }

    // Optional: get tasks assigned to a user
    public List<Task> getTasksByAssignedTo(Long userId) {
        return taskRepo.findByAssignedTo_UserId(userId);
    }

    // Update task with partial update support
    public Task updateTask(Long id, Task updatedTask) {
        Task existingTask = getTaskById(id);
        if (updatedTask.getTaskTitle() != null) existingTask.setTaskTitle(updatedTask.getTaskTitle());
        if (updatedTask.getTaskDescription() != null) existingTask.setTaskDescription(updatedTask.getTaskDescription());
        if (updatedTask.getStatus() != null) existingTask.setStatus(updatedTask.getStatus());
        if (updatedTask.getDeadline() != null) existingTask.setDeadline(updatedTask.getDeadline());
        if (updatedTask.getAssignedBy() != null) existingTask.setAssignedBy(updatedTask.getAssignedBy());
        if (updatedTask.getAssignedTo() != null) existingTask.setAssignedTo(updatedTask.getAssignedTo());
        if (updatedTask.getTeam() != null) existingTask.setTeam(updatedTask.getTeam());
        if (updatedTask.getProject() != null) existingTask.setProject(updatedTask.getProject());
        if (updatedTask.getOwner() != null) existingTask.setOwner(updatedTask.getOwner());
        return taskRepo.save(existingTask);
    }

    public void deleteTask(Long id) {
        taskRepo.deleteById(id);
    }

    public Task changeTaskStatus(Long taskId, TaskStatus status) {
        Task task = getTaskById(taskId);
        task.setStatus(status);
        return taskRepo.save(task);
    }

    public int countTasksAssignedTo(Long userId) {
        return taskRepo.countByAssignedToUserId(userId);
    }
}

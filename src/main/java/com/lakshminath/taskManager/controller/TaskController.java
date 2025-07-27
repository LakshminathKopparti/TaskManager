package com.lakshminath.taskManager.controller;

import com.lakshminath.taskManager.model.Task;
import com.lakshminath.taskManager.model.TaskStatus;
import com.lakshminath.taskManager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin // Allow all origins; you can specify origins if needed
@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping // Working
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        return ResponseEntity.ok(createdTask);
    }

    @GetMapping // Working
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}") // Working
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }

    @PutMapping("/{id}") // Working
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        Task task = taskService.updateTask(id, updatedTask);
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/{id}") //workig
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/assignedTo/{userId}") // working
    public ResponseEntity<List<Task>> getTasksByAssignedTo(@PathVariable Long userId) {
        List<Task> tasks = taskService.getTasksByAssignedTo(userId);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/countMyTasks/{userId}")
    public Integer countMyTasks(@PathVariable Long userId) {
        return taskService.countTasksAssignedTo(userId);
    }


    @PatchMapping("/{id}/status") //working
    public ResponseEntity<Task> changeTaskStatus(@PathVariable Long id, @RequestParam TaskStatus status) {
        Task task = taskService.changeTaskStatus(id, status);
        return ResponseEntity.ok(task);
    }

    @GetMapping("/team/{teamId}")
    public ResponseEntity<List<Task>> getTasksByTeamId(@PathVariable Long teamId) {
        List<Task> tasks = taskService.getTasksByTeamId(teamId);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/project/{projectId}") // working
    public ResponseEntity<List<Task>> getTasksByProjectId(@PathVariable Long projectId) {
        List<Task> tasks = taskService.getTasksByProjectId(projectId);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/owner/{ownerId}") //Working
    public ResponseEntity<List<Task>> getTasksByOwnerId(@PathVariable Long ownerId) {
        List<Task> tasks = taskService.getTasksByOwnerId(ownerId);
        return ResponseEntity.ok(tasks);
    }

    // Optional: Get tasks by status
    @GetMapping("/status") //Working
    public ResponseEntity<List<Task>> getTasksByStatus(@RequestParam TaskStatus status) {
        List<Task> tasks = taskService.getAllTasks();
        tasks = tasks.stream().filter(t -> t.getStatus() == status).toList();
        return ResponseEntity.ok(tasks);
    }



    // Optional: Get tasks due before a certain date
  /*  @GetMapping("/due-before")
    public ResponseEntity<List<Task>> getTasksDueBefore(@RequestParam("deadline") java.time.LocalDateTime deadline) {
        List<Task> tasks = taskService.getAllTasks();
        tasks = tasks.stream().filter(t -> t.getDeadline() != null && t.getDeadline().isBefore(deadline)).toList();
        return ResponseEntity.ok(tasks);
    }*/
} 
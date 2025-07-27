package com.lakshminath.taskManager.controller;

import com.lakshminath.taskManager.model.Project;
import com.lakshminath.taskManager.model.ProjectType;
import com.lakshminath.taskManager.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;


//DONE ALL
@CrossOrigin // Allow all origins; you can specify origins if needed
@RestController
@RequestMapping("/projects")
public class ProjectController { //#Verifyed

    @Autowired
    private ProjectService projectService;

    @PostMapping // DONE
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        Project created = projectService.createProject(project);
        return ResponseEntity.ok(created);
    }

    @GetMapping//DONE
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/{id}") //DONE
    public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
        Project project = projectService.getProjectById(id);
        return ResponseEntity.ok(project);
    }

    @PutMapping("/{id}") //DONE
    public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody Project updatedProject) {
        Project project = projectService.updateProject(id, updatedProject);
        return ResponseEntity.ok(project);
    }

    @DeleteMapping("/{id}") //DONE
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/projectsAssociated/{userId}")
    public ResponseEntity<Integer> countProjects(@PathVariable Long userId) {
        Integer count = projectService.countUserProjects(userId);
        return ResponseEntity.ok(count);
    }


    @GetMapping("/createdBy/{userId}") //DONE
    public ResponseEntity<List<Project>> getProjectsCreatedByUser(@PathVariable Long userId) {
        List<Project> projects = projectService.getProjectsCreatedByUser(userId);
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/member/{userId}") //DONE
    public ResponseEntity<List<Project>> getProjectsOfUser(@PathVariable Long userId) {
        List<Project> projects = projectService.getProjectsOfUser(userId);
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/filterByType") //DONE
    public ResponseEntity<List<Project>> getProjectsByType(@RequestParam("type") ProjectType type) {
        List<Project> projects = projectService.getAllProjects().stream()
                .filter(p -> p.getProjectType() == type)
                .toList();
        return ResponseEntity.ok(projects);
    }
} 
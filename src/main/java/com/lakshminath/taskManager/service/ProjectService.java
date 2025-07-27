package com.lakshminath.taskManager.service;

import com.lakshminath.taskManager.model.Project;
import com.lakshminath.taskManager.model.ProjectType;
import com.lakshminath.taskManager.model.Team;
import com.lakshminath.taskManager.model.User;
import com.lakshminath.taskManager.repo.ProjectRepo;
import com.lakshminath.taskManager.repo.UserRepo;
import com.lakshminath.taskManager.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TeamService teamService;


    public Project createProject(Project project) {
        // Set persistent createdBy user
        if (project.getCreatedBy() != null && project.getCreatedBy().getUserId() != null) {
            Long userId = project.getCreatedBy().getUserId();
            User user = userRepo.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
            project.setCreatedBy(user);
        }

        // Set persistent members
        if (project.getMembers() != null && !project.getMembers().isEmpty()) {
            List<User> persistentMembers = project.getMembers().stream()
                    .map(u -> userRepo.findById(u.getUserId())
                            .orElseThrow(() -> new RuntimeException("Member not found: " + u.getUserId())))
                    .toList();
            project.setMembers(persistentMembers);
        }

        if (project.getMembers() != null && !project.getMembers().isEmpty()) {
            project.setProjectType(ProjectType.COLLABORATIVE);
            // Create and link a team
            Team team = new Team();
            team.setName(project.getProjectName() + " Team");
            team.setCreatedBy(project.getCreatedBy());
            team.setMembers(project.getMembers());
            Team savedTeam = teamService.createTeam(team);
            project.setTeam(savedTeam);
        } else {
            project.setProjectType(ProjectType.PERSONAL);
            project.setTeam(null);
        }

        return projectRepo.save(project);
    }



    public List<Project> getAllProjects() {
        return projectRepo.findAll();
    }

    public Project getProjectById(Long id) {
        return projectRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + id));
    }

    public Project updateProject(Long id, Project updatedProject) {
        Project existing = getProjectById(id);
        if (updatedProject.getProjectName() != null) existing.setProjectName(updatedProject.getProjectName());
        if (updatedProject.getProjectDescription() != null) existing.setProjectDescription(updatedProject.getProjectDescription());
        if (updatedProject.getCreatedBy() != null) existing.setCreatedBy(updatedProject.getCreatedBy());
        if (updatedProject.getMembers() != null) existing.setMembers(updatedProject.getMembers());
        return projectRepo.save(existing);
    }

    public void deleteProject(Long id) {
        projectRepo.deleteById(id);
    }

    public List<Project> getProjectsCreatedByUser(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        return projectRepo.findByCreatedBy(user);
    }

    public List<Project> getProjectsOfUser(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        return projectRepo.findByMembersContaining(user);
    }

    public Integer countUserProjects(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        List<Project>createdByUser = projectRepo.findByCreatedBy(user);
        List<Project>userAsMember = projectRepo.findByMembersContaining(user);
        return createdByUser.size()+userAsMember.size();
    }
}
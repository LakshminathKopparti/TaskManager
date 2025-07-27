package com.lakshminath.taskManager.service;

import com.lakshminath.taskManager.model.Team;
import com.lakshminath.taskManager.model.User;
import com.lakshminath.taskManager.repo.TeamRepo;
import com.lakshminath.taskManager.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    @Autowired
    private TeamRepo teamRepo;

    @Autowired
    private UserRepo userRepo;

    public Team createTeam(Team team) {
        return teamRepo.save(team);
    }

    public List<Team> getAllTeams() {
        return teamRepo.findAll();
    }

    public Team getTeamById(Long id) {
        return teamRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Team not found with id: " + id));
    }

    public Team updateTeam(Long id, Team updatedTeam) {
        Team existing = getTeamById(id);
        if (updatedTeam.getName() != null) existing.setName(updatedTeam.getName());
        if (updatedTeam.getCreatedBy() != null) existing.setCreatedBy(updatedTeam.getCreatedBy());
        if (updatedTeam.getMembers() != null) existing.setMembers(updatedTeam.getMembers());
        return teamRepo.save(existing);
    }

    public void deleteTeam(Long id) {
        teamRepo.deleteById(id);
    }

    public List<Team> getTeamsCreatedByUser(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        return teamRepo.findByCreatedBy(user);
    }

    public List<Team> getTeamsOfUser(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        return teamRepo.findByMembersContaining(user);
    }
} 
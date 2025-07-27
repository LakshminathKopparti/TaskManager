package com.lakshminath.taskManager.controller;

import com.lakshminath.taskManager.model.Team;
import com.lakshminath.taskManager.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin // Allow all origins; you can specify origins if needed
@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @PostMapping
    public ResponseEntity<Team> createTeam(@RequestBody Team team) {
        Team created = teamService.createTeam(team);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<Team>> getAllTeams() {
        List<Team> teams = teamService.getAllTeams();
        return ResponseEntity.ok(teams);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable Long id) {
        Team team = teamService.getTeamById(id);
        return ResponseEntity.ok(team);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable Long id, @RequestBody Team updatedTeam) {
        Team team = teamService.updateTeam(id, updatedTeam);
        return ResponseEntity.ok(team);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/createdBy/{userId}")
    public ResponseEntity<List<Team>> getTeamsCreatedByUser(@PathVariable Long userId) {
        List<Team> teams = teamService.getTeamsCreatedByUser(userId);
        return ResponseEntity.ok(teams);
    }

    @GetMapping("/member/{userId}")
    public ResponseEntity<List<Team>> getTeamsOfUser(@PathVariable Long userId) {
        List<Team> teams = teamService.getTeamsOfUser(userId);
        return ResponseEntity.ok(teams);
    }
} 
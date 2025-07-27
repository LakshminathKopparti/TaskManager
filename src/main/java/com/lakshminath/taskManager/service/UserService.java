package com.lakshminath.taskManager.service;

import com.lakshminath.taskManager.model.Project;
import com.lakshminath.taskManager.model.Task;
import com.lakshminath.taskManager.model.Team;
import com.lakshminath.taskManager.model.User;
import com.lakshminath.taskManager.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User login(String email, String password) {
        return userRepo.findByEmailId(email)
                .filter(user -> passwordEncoder.matches(password, user.getPassword()))
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
    }

    public User registerUser(User user) {
        if (userRepo.findByEmailId(user.getEmailId()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getUserById(Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    // Update user's password
    public User updatePassword(Long userId, String newPassword) {
        User user = getUserById(userId);
        user.setPassword(passwordEncoder.encode(newPassword));
        return userRepo.save(user);
    }

    // Get all teams associated with a user
    public List<Team> getTeamsOfUser(Long userId) {
        User user = getUserById(userId);
        return user.getTeams();
    }

    // Get all projects associated with a user
    public List<Project> getProjectsOfUser(Long userId) {
        User user = getUserById(userId);
        return user.getProjects();
    }

    // Get personal tasks (where user is the owner)
    public List<Task> getPersonalTasks(Long userId) {
        User user = getUserById(userId);
        return user.getPersonalTasks();
    }
}

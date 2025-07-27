
package com.lakshminath.taskManager.controller;

import com.lakshminath.taskManager.model.User;
import com.lakshminath.taskManager.model.Team;
import com.lakshminath.taskManager.model.Project;
import com.lakshminath.taskManager.model.Task;
import com.lakshminath.taskManager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
        import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.lakshminath.taskManager.security.JwtUtil;
import com.lakshminath.taskManager.service.CustomUserDetailsService;

@CrossOrigin(
        origins = "http://localhost:5174",
        allowCredentials = "true"
)
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @GetMapping //TESTED
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}") //TESTED
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/register")//TESTED
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        User newUser = userService.registerUser(user);
        // Auto-login: generate JWT
        final org.springframework.security.core.userdetails.UserDetails userDetails = userDetailsService.loadUserByUsername(newUser.getEmailId());
        final String jwt = jwtUtil.generateToken(userDetails);
        java.util.Map<String, Object> response = new java.util.HashMap<>();
        response.put("user", newUser);
        response.put("token", jwt);
        return ResponseEntity.ok(response);
    }

  /*  @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestParam String email, @RequestParam String password) {
        User user = userService.login(email, password);
        return ResponseEntity.ok(user);
    } */

    @PutMapping("/{id}/password") //TESTED
    public ResponseEntity<User> updatePassword(@PathVariable Long id, @RequestParam String newPassword) {
        User updatedUser = userService.updatePassword(id, newPassword);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/{id}/teams")
    public ResponseEntity<List<Team>> getTeamsOfUser(@PathVariable Long id) {
        List<Team> teams = userService.getTeamsOfUser(id);
        return ResponseEntity.ok(teams);
    }

    @GetMapping("/{id}/projects")
    public ResponseEntity<List<Project>> getProjectsOfUser(@PathVariable Long id) {
        List<Project> projects = userService.getProjectsOfUser(id);
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/{id}/tasks/personal")
    public ResponseEntity<List<Task>> getPersonalTasks(@PathVariable Long id) {
        List<Task> tasks = userService.getPersonalTasks(id);
        return ResponseEntity.ok(tasks);
    }
}


package com.lakshminath.taskManager.repo;

import com.lakshminath.taskManager.model.Team;
import com.lakshminath.taskManager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepo extends JpaRepository<Team, Long> {
    List<Team> findByCreatedBy(User user);
    List<Team> findByMembersContaining(User user);
} 
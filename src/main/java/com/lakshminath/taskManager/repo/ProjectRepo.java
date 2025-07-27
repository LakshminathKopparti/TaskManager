package com.lakshminath.taskManager.repo;


import com.lakshminath.taskManager.model.Project;
import com.lakshminath.taskManager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepo extends JpaRepository<Project, Long> {
    List<Project> findByCreatedBy(User user);
    List<Project> findByMembersContaining(User user);
}

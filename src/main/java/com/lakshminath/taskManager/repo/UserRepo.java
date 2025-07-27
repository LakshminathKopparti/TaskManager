package com.lakshminath.taskManager.repo;

import com.lakshminath.taskManager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User> findByEmailId(String emailId);
}

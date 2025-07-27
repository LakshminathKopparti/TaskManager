package com.lakshminath.taskManager.service;

import com.lakshminath.taskManager.model.User;
import com.lakshminath.taskManager.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    public UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        com.lakshminath.taskManager.model.User user = userRepo.findByEmailId(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(user.getEmailId())
                .password(user.getPassword())
                .roles("USER"); // You can enhance this to use roles from your User entity
        return builder.build();
    }

    public User findByEmailId(String email) {
        return userRepo.findByEmailId(email).orElse(null);
    }

} 
package com.example.demo.auth;

import org.springframework.stereotype.Service;

import java.util.Optional;

public interface ApplicationUserDao {
    Optional<ApplicationUser> selectApplicationUserByUsername(String username);
}

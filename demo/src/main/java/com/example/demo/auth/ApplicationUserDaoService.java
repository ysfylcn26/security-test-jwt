package com.example.demo.auth;

import com.example.demo.security.ApplicationRole;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.demo.security.ApplicationRole.STUDENT;

@Repository
public class ApplicationUserDaoService implements ApplicationUserDao{

    private final PasswordEncoder passwordEncoder;

    public ApplicationUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return Optional.of(ApplicationUser.builder().username("anna").password(passwordEncoder.encode("password"))
                .grantedAuthorities(STUDENT.getPermission()).isAccountNonExpired(true)
                .isAccountNonLocked(true).isCredentialsNonExpired(true).isEnabled(true).build());
    }
}

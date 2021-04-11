package com.demo.test.security.service;

import com.college.etut.entity.User;
import com.college.etut.repository.UserRepository;
import com.college.etut.security.UserDetailsDao;
import com.college.etut.utils.ErrorMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsDaoService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(ErrorMessages.USERNAME_NOT_FOUND));
        return UserDetailsDao.builder().username(user.getUsername()).password(user.getPassword())
                .authorities(user.getRoleType().gerPermissons()).isAccountNonExpired(true)
                .isAccountNonLocked(true).isCredentialsNonExpired(true).isEnabled(true).build();
    }
}

package com.demo.test.service;

import com.college.etut.entity.User;
import com.college.etut.exceptions.ResourceNotFoundException;
import com.college.etut.repository.UserRepository;
import com.college.etut.utils.ErrorMessages;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public User getUserByUserName(@NonNull String userName) {
        return userRepository.findByUsername(userName)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.USER_NOT_FOUND));
    }
}
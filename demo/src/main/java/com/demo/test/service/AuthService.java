package com.demo.test.service;

import com.college.etut.entity.User;
import com.college.etut.exceptions.ResourceAlreadyExists;
import com.college.etut.model.request.AuthenticationRequest;
import com.college.etut.model.request.SignUpRequest;
import com.college.etut.model.response.GenericReturnValue;
import com.college.etut.repository.UserRepository;
import com.college.etut.utils.ErrorMessages;
import com.college.etut.utils.ResponseMessages;
import com.college.etut.utils.UtilMethods;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public String login(AuthenticationRequest request) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ResponseMessages.LOGIN_SUCCESS_USER;
    }

    @Transactional
    public GenericReturnValue<String> signUp(SignUpRequest signUpRequest) {
        log.info("There is this user");
        if (userRepository.existsByUsername(signUpRequest.getUsername()))
            throw new ResourceAlreadyExists(ErrorMessages.USERNAME_ALREADY_EXIST);
        if (!UtilMethods.isNullOrBlankCheck(signUpRequest.getEmail())
                && userRepository.existsByEmail(signUpRequest.getEmail()))
            throw new ResourceAlreadyExists(ErrorMessages.EMAIL_ALREADY_EXIST);
        User user = User.builder().username(signUpRequest.getUsername()).email(signUpRequest.getEmail())
                .userUuid(UUID.randomUUID()).password(passwordEncoder.encode(signUpRequest.getPassword()))
                .roleType(signUpRequest.getRoleType()).build();
        userRepository.save(user);
        return new GenericReturnValue<>(ResponseMessages.SIGNUP_SUCCESS_USER);
    }

}

package com.demo.test.security.filter;

import com.college.etut.exceptions.UnauthorizedException;
import com.college.etut.model.request.AuthenticationRequest;
import com.college.etut.security.JwtConfig;
import com.college.etut.utils.ErrorMessages;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.net.HttpHeaders;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@RequiredArgsConstructor
public class UserAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtConfig jwtConfig;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            AuthenticationRequest authenticationRequest = new ObjectMapper().readValue(request.getInputStream(), AuthenticationRequest.class);
            Authentication authentication = new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword());
            return authenticationManager.authenticate(authentication);
        } catch (IOException e) {
            throw new UnauthorizedException(ErrorMessages.AUTHENTICATION_ERROR);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String token = Jwts.builder().setSubject(authResult.getName())
                .claim(JwtConfig.CLAIM_KEY, authResult.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(jwtConfig.jwtExpiredTime())
                .signWith(jwtConfig.secretKey())
                .compact();
        response.setHeader(HttpHeaders.AUTHORIZATION, JwtConfig.AUTHORIZATION_PREFIX+token);
    }
}

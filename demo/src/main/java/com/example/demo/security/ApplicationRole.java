package com.example.demo.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.demo.security.ApplicationPermission.*;

public enum ApplicationRole {
    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(STUDENT_READ, STUDENT_WRITE, COURSE_READ, COURSE_WRITE)),
    ADMINTRANIEE(Sets.newHashSet(STUDENT_READ, COURSE_READ));

    private final Set<ApplicationPermission> permission;

    ApplicationRole(Set<ApplicationPermission> permission) {
        this.permission = permission;
    }

    public Set<SimpleGrantedAuthority> getPermission(){
        Set<SimpleGrantedAuthority> permission = this.permission.stream().map(x -> new SimpleGrantedAuthority(x.name()))
                .collect(Collectors.toSet());
        permission.add(new SimpleGrantedAuthority("ROLE_"+ name()));
        return permission;
    }
}

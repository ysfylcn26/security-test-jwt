package com.demo.test.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static com.college.etut.entity.enums.PermissionType.*;
import static com.college.etut.entity.enums.PermissionType.ADMIN_READ;
import static com.college.etut.entity.enums.PermissionType.ADMIN_WRITE;
import static com.college.etut.entity.enums.PermissionType.ANONYMOUS_READ;
import static com.college.etut.entity.enums.PermissionType.ANONYMOUS_WRITE;
import static com.college.etut.entity.enums.PermissionType.LESSON_READ;
import static com.college.etut.entity.enums.PermissionType.LESSON_WRITE;
import static com.college.etut.entity.enums.PermissionType.STUDENT_READ;
import static com.college.etut.entity.enums.PermissionType.STUDENT_WRITE;
import static com.college.etut.entity.enums.PermissionType.TEACHER_READ;
import static com.college.etut.entity.enums.PermissionType.TEACHER_WRITE;

public enum RoleType {
    ANONYMOUS(Sets.newHashSet(ANONYMOUS_WRITE, ANONYMOUS_READ)),
    STUDENT(Sets.newHashSet(STUDENT_WRITE, STUDENT_READ, LESSON_READ)),
    TEACHER(Sets.newHashSet(TEACHER_WRITE, TEACHER_READ, STUDENT_READ, LESSON_READ)),
    ADMIN(Sets.newHashSet(ADMIN_WRITE, ADMIN_READ, TEACHER_WRITE, TEACHER_READ, STUDENT_WRITE, STUDENT_READ, LESSON_WRITE, LESSON_READ));

    private final Set<PermissionType> permissionTypes;

    RoleType(Set<PermissionType> permissionTypes){
        this.permissionTypes = permissionTypes;
    }

    public Set<SimpleGrantedAuthority> gerPermissons(){
        Set<SimpleGrantedAuthority> permissions = permissionTypes.stream()
                .map(p -> new SimpleGrantedAuthority(p.getPermission())).collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_"+name()));
        return permissions;
    }

    @JsonCreator
    public static RoleType decode(String roleType){
        return Arrays.stream(values()).filter(r -> r.name() == roleType).findFirst().orElse(ANONYMOUS);
    }

    @JsonValue
    public String encode(){
        return name();
    }
}

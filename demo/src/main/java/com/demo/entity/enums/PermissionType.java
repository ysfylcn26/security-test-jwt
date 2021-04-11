package com.demo.test.entity.enums;

public enum PermissionType {
    LESSON_WRITE("lesson:write"),
    LESSON_READ("lesson:read"),
    ADMIN_WRITE("admin_write"),
    ADMIN_READ("admin:read"),
    TEACHER_WRITE("teacher:write"),
    TEACHER_READ("teacher:read"),
    STUDENT_WRITE("student:write"),
    STUDENT_READ("student:read"),
    ANONYMOUS_WRITE("anonymous:write"),
    ANONYMOUS_READ("anonymous:read");

    private final String permission;

    PermissionType(String permission){
        this.permission = permission;
    }

    public  String getPermission(){
        return permission;
    }
}

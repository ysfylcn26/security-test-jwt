package com.example.demo.controller;

import com.example.demo.dto.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
public class TestController {
    private static final List<Student> studentList = Arrays.asList(
       new Student(1, "Yusuf"),
       new Student(2, "Okan"),
       new Student(3, "Fatih")
    ) ;

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudent(@PathVariable("studentId") int id){
        Student student = studentList.stream().filter(x -> x.getId() == id).findFirst().get();
        return ResponseEntity.ok(student);
    }
}

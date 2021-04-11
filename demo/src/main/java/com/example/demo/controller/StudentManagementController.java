package com.example.demo.controller;

import com.example.demo.dto.Student;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/management/api/v1/student")
@Slf4j
public class StudentManagementController {
    private static final List<Student> studentList = Arrays.asList(
            new Student(1, "Yusuf"),
            new Student(2, "Okan"),
            new Student(3, "Fatih")
    ) ;

    @GetMapping
    public ResponseEntity<List<Student>> getAll(){
        return ResponseEntity.ok(studentList);
    }

    @PostMapping
    public void insertStudent(@RequestBody Student student){
        log.info("insert name---- "+student.getName(), " ---- "+student.getId());
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable("id") int id){
        log.info("selete id-----: "+ id);
    }

    @PutMapping("/{id}")
    public void updateStudent(@PathVariable("id") int id, @RequestBody Student student){
        log.info("update id-----: "+ id);
    }
}

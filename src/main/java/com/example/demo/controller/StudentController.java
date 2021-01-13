package com.example.demo.controller;

import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/test")
    public ResponseEntity<Object> welcome() {
        Random rand=new Random();
        return ResponseEntity.ok("<-- Welcome to Akshay's deployment POC -->#"+rand.nextInt(1000));

    }

    @PostMapping("/add")
    public ResponseEntity<Object> addStudent(@RequestBody Map<String, Object> data) {
        return studentService.addStudent(data);

    }

    @GetMapping("/data")
    public ResponseEntity<Object> getStudentData() {

        return studentService.getStudents();
    }


    @GetMapping("/getById/{id}")
    public ResponseEntity<Object> getStudent(@PathVariable("id") Long id) {
        return (studentService.getStudent(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteStudent(@PathVariable("id") Long id) {

        return studentService.deleteStudent(id);
    }

}

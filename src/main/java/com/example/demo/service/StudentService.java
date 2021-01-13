package com.example.demo.service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface StudentService {

    ResponseEntity<Object> addStudent(Map<String, Object> data);

    ResponseEntity<Object> getStudent(Long data);

    ResponseEntity<Object> getStudents();

    ResponseEntity<Object> deleteStudent(Long data);


}

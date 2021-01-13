package com.example.demo.service.implementation;

import com.example.demo.repository.StudentRespository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class StudentServiceImplementationTest {
    @InjectMocks
    StudentServiceImplementation studentServiceImplementation;

    @Mock
    StudentRespository studentRespository;

    @Test
    public void addStudentTest() {
        Map<String, Object> data = new HashMap<>();
        data.put("name", "akshay");
        data.put("standard", "12th");
        data.put("age", 24);
        ResponseEntity<Object> responseEntity = studentServiceImplementation.addStudent(data);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void getStudentsTest() {

        ResponseEntity<Object> responseEntity = studentServiceImplementation.getStudents();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    @Test
    public void getStudentTest() {
        Long id=1L;
        ResponseEntity<Object> responseEntity = studentServiceImplementation.getStudent(id);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    @Test
    public void deleteStudentTest() {
        Long id=1L;
        ResponseEntity<Object> responseEntity = studentServiceImplementation.deleteStudent(id);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }


}
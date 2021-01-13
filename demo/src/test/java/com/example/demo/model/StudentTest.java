package com.example.demo.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class StudentTest {
    @InjectMocks
    Student student;

    @Test
    public void studentModelTest() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        student.setId(1);
        student.setName("Akshay gade");
        student.setAge(24);
        student.setStandard("12th");

        String test = objectMapper.writeValueAsString(student);
        assertEquals("{\"id\":1,\"name\":\"Akshay gade\",\"standard\":\"12th\",\"age\":24}", test);
    }
}
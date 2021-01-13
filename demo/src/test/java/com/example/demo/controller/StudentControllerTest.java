package com.example.demo.controller;

import com.example.demo.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class StudentControllerTest {

    @InjectMocks
    StudentController studentController;
    @Mock
    StudentService studentService;
    private MockMvc mockMvc;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(studentController)
                .build();
    }

    @Test
    public void WelcomeTest() throws Exception {
        Map<String, Object> data = new HashMap<>();
        data.put("name", "akshay");
        data.put("standard", "12th");
        data.put("age", 24);

        MvcResult response = mockMvc.perform(
                MockMvcRequestBuilders.get("/student/test")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(data))).andReturn();
        assertEquals(HttpStatus.OK.value(), response.getResponse().getStatus());

    }

    @Test
    public void registerTest() throws Exception {
        Map<String, Object> data = new HashMap<>();
        data.put("name", "akshay");
        data.put("standard", "12th");
        data.put("age", 24);
        when(studentService.addStudent(Mockito.any())).thenReturn(ResponseEntity.ok(data));
        MvcResult response = mockMvc.perform(
                MockMvcRequestBuilders.post("/student/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(data))).andReturn();
        assertEquals(HttpStatus.OK.value(), response.getResponse().getStatus());

    }

    @Test
    public void getStudentsTest() throws Exception {
        Long id = 1L;
        //when(studentService.getStudent(Mockito.any())).thenReturn(ResponseEntity.ok(id));
        MvcResult response = mockMvc.perform(
                MockMvcRequestBuilders.get("/student/data")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(id))).andReturn();
        assertEquals(HttpStatus.OK.value(), response.getResponse().getStatus());

    }

    @Test
    public void getStudentByIdTest() throws Exception {
        Long id = 1L;
        when(studentService.getStudent(Mockito.any())).thenReturn(ResponseEntity.ok(id));
        MvcResult response = mockMvc.perform(
                MockMvcRequestBuilders.get("/student/getById/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(id))).andReturn();
        assertEquals(HttpStatus.OK.value(), response.getResponse().getStatus());

    }

    @Test
    public void deleteStudentByIdTest() throws Exception {
        Long id = 1L;
        when(studentService.deleteStudent(Mockito.any())).thenReturn(ResponseEntity.ok(id));
        MvcResult response = mockMvc.perform(
                MockMvcRequestBuilders.delete("/student/delete/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(id))).andReturn();
        assertEquals(HttpStatus.OK.value(), response.getResponse().getStatus());

    }
}
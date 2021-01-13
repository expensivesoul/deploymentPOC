package com.example.demo.service.implementation;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRespository;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
public class StudentServiceImplementation implements StudentService {
    @Autowired
    StudentRespository studentRespository;

    @Override
    public ResponseEntity<Object> addStudent(Map<String, Object> data) {
        Student student=new Student();
        student.setName(data.get("name").toString());
        student.setAge((Integer) data.get("age"));
        student.setStandard(data.get("standard").toString());
        studentRespository.save(student);
        return ResponseEntity.ok("Student added Successfully");
    }
    @Override
    public ResponseEntity<Object> getStudent(Long id) {

        return ResponseEntity.ok(studentRespository.findById(id));
    }
    @Override
    public ResponseEntity<Object> getStudents() {

        return ResponseEntity.ok(studentRespository.findAll());
    }
    @Override
    public ResponseEntity<Object> deleteStudent(Long id) {
        studentRespository.deleteById(id);
        return ResponseEntity.ok("Student delete successfully with id->"+id);
    }
}

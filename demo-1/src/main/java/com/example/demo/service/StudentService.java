package com.example.demo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student enrollStudent(Student student) {
        // Perform any additional business logic here
        return studentRepository.save(student);
    }

    // Other methods if needed
}


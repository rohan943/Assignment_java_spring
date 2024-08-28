package com.example.demo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Teacher;
import com.example.demo.repository.TeacherRepository;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public Teacher enrollTeacher(Teacher teacher) {
        // Perform additional business logic if needed
        return teacherRepository.save(teacher);
    }

    // Other methods if needed
}

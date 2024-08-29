package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.ApiResponse;
import com.example.demo.entity.Teacher;
import com.example.demo.service.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private static final Logger logger = LoggerFactory.getLogger(TeacherController.class);

    @Autowired
    private TeacherService teacherService;
    
    @PostMapping
    public ResponseEntity<ApiResponse<Teacher>> enrollTeacher(@RequestBody Teacher teacher) {
        Teacher enrolledTeacher = teacherService.enrollTeacher(teacher);
        ApiResponse<Teacher> response = new ApiResponse<>("Teacher enrolled successfully", enrolledTeacher, true);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
        ApiResponse<Void> response = new ApiResponse<>("Teacher deleted successfully", true);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Teacher>> updateTeacher(@PathVariable Long id, @RequestBody Teacher teacher) {
        Teacher updatedTeacher = teacherService.updateTeacher(id, teacher);
        ApiResponse<Teacher> response = new ApiResponse<>("Teacher updated successfully", updatedTeacher, true);
        return ResponseEntity.ok(response);
    }
}


package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.ApiResponse;
import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/students")
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<ApiResponse<Student>> enrollStudent(@RequestBody Student student) {
        Student enrolledStudent = studentService.enrollStudent(student);
        ApiResponse<Student> response = new ApiResponse<>("Student enrolled successfully", enrolledStudent, true);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        ApiResponse<Void> response = new ApiResponse<>("Student deleted successfully", true);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(id, student);
        ApiResponse<Student> response = new ApiResponse<>("Student updated successfully", updatedStudent, true);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{studentId}/teachers/{teacherId}")
    public void addTeacherToStudent(@PathVariable Long studentId, @PathVariable Long teacherId) {
        logger.info("Request to add teacher with ID: {} to student with ID: {}", teacherId, studentId);
        studentService.addTeacherToStudent(studentId, teacherId);
        logger.info("Teacher with ID: {} added to student with ID: {}", teacherId, studentId);
    }

    @DeleteMapping("/{studentId}/teachers/{teacherId}")
    public void removeTeacherFromStudent(@PathVariable Long studentId, @PathVariable Long teacherId) {
        logger.info("Request to remove teacher with ID: {} from student with ID: {}", teacherId, studentId);
        studentService.removeTeacherFromStudent(studentId, teacherId);
        logger.info("Teacher with ID: {} removed from student with ID: {}", teacherId, studentId);
    }
    
    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Long id) {
        return studentService.getStudent(id);
    }
}





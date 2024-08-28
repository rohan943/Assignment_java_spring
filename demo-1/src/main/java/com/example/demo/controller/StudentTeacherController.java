package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.controller.AddressController.AddressRequest;
import com.example.demo.entity.Address;
import com.example.demo.entity.ErrorResponse;
import com.example.demo.entity.Student;
import com.example.demo.entity.SuccessResponse;
import com.example.demo.entity.Teacher;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.TeacherRepository;

@RestController
@RequestMapping("/students-teachers")
public class StudentTeacherController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private AddressRepository addressRepository;

    @PostMapping("/map")
    public ResponseEntity<?> mapStudentToTeacher(@RequestBody MappingRequest mappingRequest) {
        try {
            Student student = studentRepository.findById(mappingRequest.getStudentId())
                    .orElseThrow(() -> new RuntimeException("Student not found"));
            Teacher teacher = teacherRepository.findById(mappingRequest.getTeacherId())
                    .orElseThrow(() -> new RuntimeException("Teacher not found"));

            student.getTeachers().add(teacher);
            teacher.getStudents().add(student);

            studentRepository.save(student);
            teacherRepository.save(teacher);

            return ResponseEntity.ok(new SuccessResponse("Student mapped to teacher successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage()));
        }
    }


    @DeleteMapping("/unmap")
    public ResponseEntity<?> unmapStudentFromTeacher(@RequestBody MappingRequest mappingRequest) {
        try {
            Student student = studentRepository.findById(mappingRequest.getStudentId())
                    .orElseThrow(() -> new RuntimeException("Student not found"));
            Teacher teacher = teacherRepository.findById(mappingRequest.getTeacherId())
                    .orElseThrow(() -> new RuntimeException("Teacher not found"));

            student.getTeachers().remove(teacher);
            teacher.getStudents().remove(student);

            studentRepository.save(student);
            teacherRepository.save(teacher);

            return ResponseEntity.ok(new SuccessResponse("Student unmapped from teacher successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage()));
        }
    }

    public static class MappingRequest {
        private Long studentId;
        private Long teacherId;

        // Getters and Setters
        public Long getStudentId() {
            return studentId;
        }

        public void setStudentId(Long studentId) {
            this.studentId = studentId;
        }

        public Long getTeacherId() {
            return teacherId;
        }

        public void setTeacherId(Long teacherId) {
            this.teacherId = teacherId;
        }
    }
}


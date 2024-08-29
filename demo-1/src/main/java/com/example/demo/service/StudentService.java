package com.example.demo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Student;
import com.example.demo.entity.Teacher;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.TeacherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    public Student enrollStudent(Student student) {
        logger.info("Enrolling student: {}", student);
        Student savedStudent = studentRepository.save(student);
        logger.info("Student enrolled successfully: {}", savedStudent);
        return savedStudent;
    }

    public void deleteStudent(Long id) {
        logger.info("Deleting student with ID: {}", id);
        studentRepository.deleteById(id);
        logger.info("Student deleted successfully with ID: {}", id);
    }

    public Student updateStudent(Long id, Student studentDetails) {
        logger.info("Updating student with ID: {} with details: {}", id, studentDetails);
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
        student.setName(studentDetails.getName());
        Student updatedStudent = studentRepository.save(student);
        logger.info("Student updated successfully: {}", updatedStudent);
        return updatedStudent;
    }

    @Transactional
    public void addTeacherToStudent(Long studentId, Long teacherId) {
        logger.info("Adding teacher with ID: {} to student with ID: {}", teacherId, studentId);
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(() -> new RuntimeException("Teacher not found"));

        student.getTeachers().add(teacher);
        studentRepository.save(student);
        logger.info("Teacher with ID: {} added to student with ID: {}", teacherId, studentId);
    }

    @Transactional
    public void removeTeacherFromStudent(Long studentId, Long teacherId) {
        logger.info("Removing teacher with ID: {} from student with ID: {}", teacherId, studentId);
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(() -> new RuntimeException("Teacher not found"));

        student.getTeachers().remove(teacher);
        studentRepository.save(student);
        logger.info("Teacher with ID: {} removed from student with ID: {}", teacherId, studentId);
    }
    
    public Student getStudent(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
    }
}

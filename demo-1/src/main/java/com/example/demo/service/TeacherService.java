package com.example.demo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Teacher;
import com.example.demo.repository.TeacherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class TeacherService {

    private static final Logger logger = LoggerFactory.getLogger(TeacherService.class);

    @Autowired
    private TeacherRepository teacherRepository;

    public Teacher enrollTeacher(Teacher teacher) {
        logger.info("Enrolling teacher: {}", teacher);
        Teacher savedTeacher = teacherRepository.save(teacher);
        logger.info("Teacher enrolled successfully: {}", savedTeacher);
        return savedTeacher;
    }

    public void deleteTeacher(Long id) {
        logger.info("Deleting teacher with ID: {}", id);
        teacherRepository.deleteById(id);
        logger.info("Teacher deleted successfully with ID: {}", id);
    }

    public Teacher updateTeacher(Long id, Teacher teacherDetails) {
        logger.info("Updating teacher with ID: {} with details: {}", id, teacherDetails);
        Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> new RuntimeException("Teacher not found"));
        teacher.setName(teacherDetails.getName());
        // Update other fields as necessary
        Teacher updatedTeacher = teacherRepository.save(teacher);
        logger.info("Teacher updated successfully: {}", updatedTeacher);
        return updatedTeacher;
    }
}


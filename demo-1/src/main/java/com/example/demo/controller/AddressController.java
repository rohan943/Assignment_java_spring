package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Address;
import com.example.demo.entity.Student;
import com.example.demo.entity.Teacher;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.TeacherRepository;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @PostMapping("/add")
    public ResponseEntity<?> addAddress(@RequestBody AddressRequest addressRequest) {
        try {
            Address address = new Address();
            address.setStreet(addressRequest.getStreet());
            address.setCity(addressRequest.getCity());

            if (addressRequest.getStudentId() != null) {
                Student student = studentRepository.findById(addressRequest.getStudentId())
                        .orElseThrow(() -> new RuntimeException("Student not found"));
                address.setStudent(student);
            }

            if (addressRequest.getTeacherId() != null) {
                Teacher teacher = teacherRepository.findById(addressRequest.getTeacherId())
                        .orElseThrow(() -> new RuntimeException("Teacher not found"));
                address.setTeacher(teacher);
            }

            Address savedAddress = addressRepository.save(address);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedAddress);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage()));
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAddress(@PathVariable Long id, @RequestBody AddressRequest addressRequest) {
        try {
            Address address = addressRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Address not found"));

            address.setStreet(addressRequest.getStreet());
            address.setCity(addressRequest.getCity());

            Address updatedAddress = addressRepository.save(address);
            return ResponseEntity.ok(updatedAddress);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage()));
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable Long id) {
        try {
            Address address = addressRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Address not found"));

            addressRepository.delete(address);
            return ResponseEntity.ok(new SuccessResponse("Address deleted successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage()));
        }
    }

    public static class SuccessResponse {
        private String message;

        public SuccessResponse(String message) {
            this.message = message;
        }

        // Getter
        public String getMessage() {
            return message;
        }
    }



    // Other methods (update, delete) here

    public static class AddressRequest {
        private String street;
        private String city;
        private Long studentId;
        private Long teacherId;
		public String getStreet() {
			return street;
		}
		public void setStreet(String street) {
			this.street = street;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
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

        // Getters and Setters
    }

    public static class ErrorResponse {
        private String error;

        public ErrorResponse(String error) {
            this.error = error;
        }

        // Getter
        public String getError() {
            return error;
        }
    }
}

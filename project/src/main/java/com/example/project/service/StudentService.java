package com.example.project.service;

import com.example.project.entity.Student;
import com.example.project.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
        if (studentByEmail.isPresent()) {
            throw new IllegalStateException("Student email exist");
        } else{
            studentRepository.save(student);
        }
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void deleteStudentByEmail(String email) {
        Student student = studentRepository.findStudentByEmail(email.trim()).orElseThrow(() -> new IllegalStateException("Student not found"));
        studentRepository.delete(student);
    }

    @Transactional
    public void updateStudent(long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException("Student not found"));

        if(name != null && name.length() > 0 && !name.equals(student.getName())) {
            student.setName(name);
        }

        if(email != null && email.length() > 0 && !email.equals(student.getEmail())) {
            student.setEmail(email);
        }

        studentRepository.save(student);
    }
}

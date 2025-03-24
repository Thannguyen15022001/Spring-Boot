package com.example.project.controller;

import com.example.project.entity.Student;
import com.example.project.service.StudentService;
import org.hibernate.annotations.Parameter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {
    private final StudentService studentService;

    public Controller(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    private List<Student> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping("/student")
    private void addStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }

    @DeleteMapping("/student/{email}")
    private void deleteStudent(@PathVariable("email") String email) {
        studentService.deleteStudentByEmail(email);
    }

    @PutMapping("/student/{studentID}")
    private void updateStudent(@PathVariable("studentID") long studentID, @RequestParam(required = false) String name, @RequestParam(required = false) String email) {
        studentService.updateStudent(studentID, name, email);

    }



}

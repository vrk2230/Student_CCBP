package com.example.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.school.model.Student;
import com.example.school.service.StudentH2Service;

import java.util.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class StudentController {

    @Autowired
    private StudentH2Service studentService;

    @GetMapping("/students")
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @GetMapping("/student/{studentId}")
    public Student getStudentById(@PathVariable("studentId") int studentId) {
        return studentService.getStudentById(studentId);
    }

    @PostMapping("/students")
    public Student addsStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PostMapping("/students/bulk")
    public String bulkAddStudents(@RequestBody List<Student> students) {
        return studentService.addStudentBulk(students);
    }

    @PutMapping("students/{id}")
    public Student updateStudent(@PathVariable("id") int studentId, @RequestBody Student student) {
        return studentService.updateStudentById(studentId, student);
    }

    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable("id") int studentId) {
        studentService.deleteStudent(studentId);
    }

}

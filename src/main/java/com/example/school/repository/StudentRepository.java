package com.example.school.repository;

import java.util.ArrayList;
import java.util.List;

import com.example.school.model.Student;

public interface StudentRepository {
    ArrayList<Student> getStudents();

    Student addStudent(Student student);

    String addStudentBulk(List<Student> students);

    Student getStudentById(int studentId);

    Student updateStudentById(int studentId, Student student);

    void deleteStudent(int studentId);

}
package com.example.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.school.model.Student;
import com.example.school.model.StudentRowMapper;
import com.example.school.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class StudentH2Service implements StudentRepository {

    @Autowired
    private JdbcTemplate db;

    @Override
    public ArrayList<Student> getStudents() {
        return (ArrayList<Student>) db.query("SELECT * FROM STUDENT", new StudentRowMapper());
    }

    @Override
    public Student addStudent(Student student) {
        db.update("INSERT INTO STUDENT(studentName, gender, standard) VALUES(?,?,?)", student.getStudentName(),
                student.getGender(), student.getStandard());
        return db.queryForObject("SELECT * FROM STUDENT WHERE studentName = ? AND gender = ? AND standard = ?",
                new StudentRowMapper(), student.getStudentName(), student.getGender(), student.getStandard());
    }

    @Override
    public String addStudentBulk(List<Student> students) {
        int count = 0;
        for (Student student : students) {
            try {
                addStudent(student);
                count++;
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return "Successfully added " + count + " students";
    }

    @Override
    public Student getStudentById(int studentId) {
        try {
            return db.queryForObject("SELECT * FROM STUDENT WHERE studentId = ?", new StudentRowMapper(), studentId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public Student updateStudentById(int studentId, Student student) {
        if (student.getStudentName() != null) {
            db.update("UPDATE STUDENT SET studentName = ? WHERE studentId = ?", student.getStudentName(), studentId);
        }

        if (student.getGender() != null) {
            db.update("UPDATE STUDENT SET gender = ? WHERE studentId = ?", student.getGender(), studentId);
        }

        if (student.getStandard() != null) {
            db.update("UPDATE STUDENT SET standard = ? WHERE studentId = ?", student.getStandard(), studentId);
        }
        return getStudentById(studentId);
    }

    @Override
    public void deleteStudent(int studentId) {
        db.update("DELETE FROM STUDENT WHERE studentId = ?", studentId);
    }

}

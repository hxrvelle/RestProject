package org.example.repository;

import org.example.model.Student;

import java.util.List;

public interface StudentRepo {
    List<Student> getAllActiveStudents();
    Student getStudentById(int id);
    void createStudent(Student student);
    void modifyStudent(int id, Student student);
    void deleteStudent(int id);
}

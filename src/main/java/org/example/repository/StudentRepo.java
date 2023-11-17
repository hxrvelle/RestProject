package org.example.repository;

import org.example.model.Student;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface StudentRepo {
    List<Student> getAllActiveStudents();
    Student getStudentById(int id) throws SQLException, IOException, ClassNotFoundException;
    void createStudent(Student student) throws SQLException, IOException, ClassNotFoundException;
    void modifyStudent(int id, Student student) throws SQLException, IOException, ClassNotFoundException;
    void deleteStudent(int id) throws SQLException, IOException, ClassNotFoundException;
}

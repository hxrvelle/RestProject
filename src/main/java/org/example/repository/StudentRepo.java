package org.example.repository;

import org.example.model.Student;

import java.util.List;

public interface StudentRepo {
    public List<Student> getAllActiveStudents();
    public Student getStudentById(int id);
    public void createStudent(Student student);
    public void modifyStudent(int id, Student student);
    public void deleteStudent(int id);
}

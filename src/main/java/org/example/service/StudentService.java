package org.example.service;

import org.example.controller.dto.StudentIncomingDto;
import org.example.controller.dto.StudentOutgoingDto;
import org.example.controller.mapper.StudentDtoMapper;
import org.example.model.Student;
import org.example.repository.impl.StudentRepoImpl;

import java.util.List;

public interface StudentService {

    List<StudentOutgoingDto> getAllActiveStudents();

    StudentOutgoingDto getStudentById(int id);

    void createStudent(StudentIncomingDto studentDto);

    void modifyStudent(int id, StudentIncomingDto studentDto);

    void deleteStudent(int id);

    /**Logical check methods */
    String getStudentsCheck(String[] path);
    String createStudentCheck(String surname, String name, String group, String date);
    String updateStudentCheckId(String[] path);
    String updateStudentCheck(String[] path, String surname, String name, String group, String date);
    String deleteStudentCheck(String[] path);

}

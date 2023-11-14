package org.example.service;

import org.example.controller.dto.StudentIncomingDto;
import org.example.controller.dto.StudentOutgoingDto;
import org.example.controller.mapper.StudentDtoMapper;
import org.example.model.Student;
import org.example.repository.impl.StudentRepoImpl;

import java.util.List;

public interface StudentService {

    public List<StudentOutgoingDto> getAllActiveStudents();

    public StudentOutgoingDto getStudentById(int id);

    public void createStudent(StudentIncomingDto studentDto);

    public void modifyStudent(int id, StudentIncomingDto studentDto);

    public void deleteStudent(int id);
}

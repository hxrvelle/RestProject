package org.example.service.impl;

import org.example.controller.dto.StudentIncomingDto;
import org.example.controller.dto.StudentOutgoingDto;
import org.example.controller.mapper.StudentDtoMapper;
import org.example.model.Student;
import org.example.repository.impl.StudentRepoImpl;
import org.example.service.StudentService;

import java.sql.Date;
import java.util.List;

public class StudentServiceImpl implements StudentService {
    private final StudentRepoImpl studentRepoImpl;

    public StudentServiceImpl(StudentRepoImpl studentRepoImpl) {
        this.studentRepoImpl = studentRepoImpl;
    }

    @Override
    public List<StudentOutgoingDto> getAllActiveStudents() {
        List<Student> students = studentRepoImpl.getAllActiveStudents();
        return StudentDtoMapper.INSTANCE.mapToDtoList(students);
    }

    @Override
    public StudentOutgoingDto getStudentById(int id) {
        Student student = studentRepoImpl.getStudentById(id);
        return StudentDtoMapper.INSTANCE.mapToDto(student);
    }

    @Override
    public void createStudent(StudentIncomingDto studentDto) {
        Student student = StudentDtoMapper.INSTANCE.mapToEntity(studentDto);
        studentRepoImpl.createStudent(student);
    }

    @Override
    public void modifyStudent(int id, StudentIncomingDto studentDto) {
        Student student = StudentDtoMapper.INSTANCE.mapToEntity(studentDto);
        studentRepoImpl.modifyStudent(id, student);
    }

    @Override
    public void deleteStudent(int id) {
        studentRepoImpl.deleteStudent(id);
    }
}

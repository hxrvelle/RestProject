package service;

import org.example.controller.mapper.StudentDtoMapper;
import org.example.model.Student;
import org.example.repository.impl.StudentRepoImpl;
import org.example.service.impl.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class StudentServiceTests {
    @InjectMocks
    private StudentServiceImpl studentService;
    @Mock
    private StudentRepoImpl studentRepo;
    @Mock
    private StudentDtoMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllActiveStudentsTest() {
        when(studentRepo.getAllActiveStudents()).thenReturn(new ArrayList<>());
        studentService.getAllActiveStudents();

        verify(studentRepo, atLeastOnce()).getAllActiveStudents();
    }

    @Test
    void getStudentByIdTest() {
        int id = 1;

        when(studentRepo.getStudentById(id)).thenReturn(new Student());
        studentService.getStudentById(id);

        verify(studentRepo, atLeastOnce()).getStudentById(id);
    }

    @Test
    void createStudentTest() {
        studentService.createStudent(any());

        doNothing().when(studentRepo).createStudent(any());
        verify(studentRepo, atLeastOnce()).createStudent(any());
    }

    @Test
    void modifyStudent() {
        studentService.modifyStudent(anyInt(), any());

        doNothing().when(studentRepo).modifyStudent(anyInt(), any());
        verify(studentRepo, atLeastOnce()).modifyStudent(anyInt(), any());
    }

    @Test
    void deleteStudent() {
        studentService.deleteStudent(anyInt());

        doNothing().when(studentRepo).deleteStudent(anyInt());
        verify(studentRepo, atLeastOnce()).deleteStudent(anyInt());
    }
}

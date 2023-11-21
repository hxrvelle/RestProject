package service;

import org.example.controller.dto.StudentIncomingDto;
import org.example.controller.mapper.StudentDtoMapper;
import org.example.model.Student;
import org.example.repository.impl.StudentRepoImpl;
import org.example.service.impl.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class StudentServiceTests {
    @InjectMocks
    private StudentServiceImpl studentService;
    @Mock
    private StudentRepoImpl studentRepo;

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

    //Check methods

    @Test
    void testGetStudentsCheckValidId() {
        int validId = 1;
        Student mockStudent = new Student();
        mockStudent.setId(validId);

        when(studentRepo.getStudentById(validId)).thenReturn(mockStudent);

        String[] path = {"", "1"};
        String result = studentService.getStudentsCheck(path);

        assertEquals("1", result);
    }

    @Test
    void testGetStudentsCheckInvalidId() {
        int invalidId = 0;
        Student mockStudent = new Student();

        when(studentRepo.getStudentById(invalidId)).thenReturn(mockStudent);

        String[] path = {"", "0"};
        String result = studentService.getStudentsCheck(path);

        assertEquals("0", result);
    }

    @Test
    void testGetStudentsCheckNonNumericInput() {
        String[] path = {"", "abc"};
        String result = studentService.getStudentsCheck(path);

        assertEquals("4", result);
    }

    @Test
    void testGetStudentsCheckEmptyPath() {
        List<Student> mockEmptyList = Collections.emptyList();

        when(studentRepo.getAllActiveStudents()).thenReturn(mockEmptyList);

        String[] path = {""};
        String result = studentService.getStudentsCheck(path);

        assertEquals("2", result);
    }

    @Test
    void testGetStudentsCheckNonEmptyPath() {
        List<Student> mockStudentsList = Arrays.asList(new Student(), new Student()); // Simulating a non-empty list

        when(studentRepo.getAllActiveStudents()).thenReturn(mockStudentsList);

        String[] path = {""};
        String result = studentService.getStudentsCheck(path);

        assertEquals("3", result);
    }

    @Test
    void testCreateStudentCheckNullValues() {
        String result = studentService.createStudentCheck(null, null, null, "2023-01-15");

        assertEquals("0", result);
        verifyNoInteractions(studentRepo);
    }

    @Test
    void testCreateStudentCheckInvalidDate() {
        String result = studentService.createStudentCheck("Doe", "John", "A", "12-12-2023");

        assertEquals("1", result);
        verifyNoInteractions(studentRepo);
    }

    @Test
    void testCreateStudentCheckNullDate() {
        String result = studentService.createStudentCheck("Doe", "John", "A", null);

        assertEquals("0", result);
        verifyNoInteractions(studentRepo);
    }

    @Test
    void testUpdateStudentCheckNullPath() {
        String[] path = {};

        String result = studentService.updateStudentCheck(path, "Doe", "John", "A", "2023-01-15");

        assertEquals("0", result);
        verifyNoInteractions(studentRepo);
    }

    @Test
    void testUpdateStudentCheckInvalidIdInPath() {
        String[] path = {"", "abc"};

        String result = studentService.updateStudentCheck(path, "Doe", "John", "A", "2023-01-15");

        assertEquals("4", result);
        verifyNoInteractions(studentRepo);
    }

    @Test
    void testDeleteStudentCheckValidId() {
        int id = 1;
        String[] path = {"", String.valueOf(id)};
        Student mockStudent = new Student();
        mockStudent.setId(id);

        when(studentRepo.getStudentById(id)).thenReturn(mockStudent);
        doNothing().when(studentRepo).deleteStudent(id);

        String result = studentService.deleteStudentCheck(path);

        assertEquals("2", result);
        verify(studentRepo, times(1)).deleteStudent(id);
    }

    @Test
    void testDeleteStudentCheckNullPath() {
        String[] path = {};

        String result = studentService.deleteStudentCheck(path);

        assertEquals("0", result);
        verifyNoInteractions(studentRepo);
    }

    @Test
    void testDeleteStudentCheckInvalidIdInPath() {
        String[] path = {"", "abc"};

        String result = studentService.deleteStudentCheck(path);

        assertEquals("3", result);
        verifyNoInteractions(studentRepo);
    }
}

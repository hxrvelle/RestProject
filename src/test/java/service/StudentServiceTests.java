package service;

import org.example.controller.dto.StudentIncomingDto;
import org.example.controller.dto.StudentOutgoingDto;
import org.example.controller.mapper.StudentDtoMapper;
import org.example.model.Student;
import org.example.repository.impl.StudentRepoImpl;
import org.example.service.impl.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

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
        List<StudentOutgoingDto> studentList = studentService.getAllActiveStudents();

        Mockito.when(mapper.mapToDtoList(studentRepo.getAllActiveStudents())).thenReturn(studentList);
        Mockito.verify(studentRepo).getAllActiveStudents();
    }

    @Test
    void getStudentByIdTest() {
        int id = 1;
        StudentOutgoingDto student = studentService.getStudentById(id);

        Mockito.when(mapper.mapToDto(studentRepo.getStudentById(id))).thenReturn(student);
        Mockito.verify(studentRepo).getStudentById(id);
    }

    @Test
    void createStudentTest() {
        String name = "Name";
        String surname = "Surname";
        String group = "Group";
        String date = "2023-11-11";

        StudentIncomingDto student = new StudentIncomingDto();
        student.setSurname(surname);
        student.setName(name);
        student.setGroup(group);
        student.setDate(Date.valueOf(date));

        //studentService.createStudentCheck(surname, name, group, date);
        studentService.createStudent(student);

        Mockito.verify(studentRepo).createStudent(mapper.mapToEntity(student));
    }

    @Test
    void modifyStudent() {
    }

    @Test
    void deleteStudent() {
    }
}

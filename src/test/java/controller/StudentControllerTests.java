package controller;

import org.example.controller.StudentController;
import org.example.controller.dto.StudentIncomingDto;
import org.example.controller.dto.StudentOutgoingDto;
import org.example.service.impl.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import static org.mockito.Mockito.*;

public class StudentControllerTests {
    @InjectMocks
    private StudentController controller;
    @Mock
    private StudentServiceImpl service;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private PrintWriter writer;

    @BeforeEach
    void setUp() {
        controller = new StudentController();
        MockitoAnnotations.openMocks(this);
        StringWriter stringWriter = new StringWriter();
        writer = new PrintWriter(stringWriter);
    }

    @Test
    void doGetAllStudentsTest() throws IOException {
        List<StudentOutgoingDto> students = service.getAllActiveStudents();

        when(request.getPathInfo()).thenReturn("/");
        when(service.getAllActiveStudents()).thenReturn(students);
        when(response.getWriter()).thenReturn(writer);

        controller.doGet(request, response);

        verify(service).getAllActiveStudents();
    }

    @Test
    void doGetStudentById() throws IOException {
        StudentOutgoingDto student = service.getStudentById(1);

        when(request.getPathInfo()).thenReturn("/1");
        when(service.getStudentById(1)).thenReturn(student);
        when(response.getWriter()).thenReturn(writer);

        controller.doGet(request, response);

        verify(service).getStudentById(1);
    }

    @Test
    void doPostTest() throws IOException {
        when(request.getParameter("surname")).thenReturn("Surname");
        when(request.getParameter("name")).thenReturn("Name");
        when(request.getParameter("group")).thenReturn("Group");
        when(request.getParameter("date")).thenReturn("2023-10-10");

        controller.doPost(request, response);

        verify(service).createStudent(any(StudentIncomingDto.class));
    }

    @Test
    void doPutTest() {
        when(request.getParameter("path")).thenReturn("/1");
        when(request.getParameter("surname")).thenReturn("Surname");
        when(request.getParameter("name")).thenReturn("Name");
        when(request.getParameter("group")).thenReturn("Group");
        when(request.getParameter("date")).thenReturn("2023-10-10");
    }

    @Test
    void doDeleteTest() {
        when(request.getParameter("path")).thenReturn("/1");
    }
}

package controller;

import org.example.controller.StudentController;
import org.example.controller.dto.StudentIncomingDto;
import org.example.controller.dto.StudentOutgoingDto;
import org.example.controller.responseHandlers.general.SuccessResponse;
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
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StudentControllerTests {
    @Mock
    private StudentServiceImpl service;
    @Mock
    private SuccessResponse success;
    @Mock
    private StudentIncomingDto student;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private PrintWriter writer;
    @InjectMocks
    private StudentController controller;

    @BeforeEach
    void setUp() {
        controller = new StudentController();
        service = new StudentServiceImpl();
        success = new SuccessResponse();
        student = new StudentIncomingDto();

        MockitoAnnotations.openMocks(this);
        StringWriter stringWriter = new StringWriter();
        writer = new PrintWriter(stringWriter);
    }

    @Test
    void doGetAllStudentsTest() throws IOException {
        when(request.getPathInfo()).thenReturn("/");
        when(service.getStudentsCheck(any())).thenReturn("3");
        when(response.getWriter()).thenReturn(writer);

        String[] path = {""};

        service.getStudentsCheck(path);
        controller.doGet(request, response);

        verify(success, atLeastOnce()).successResponse(response, 200);
        verify(service, atLeastOnce()).getStudentsCheck(any());
    }

    @Test
    void doGetStudentById() throws IOException {
        when(request.getPathInfo()).thenReturn("/1");
        when(service.getStudentById(1)).thenReturn(new StudentOutgoingDto());
        when(service.getStudentsCheck(any())).thenReturn("1");
        when(response.getWriter()).thenReturn(writer);

        controller.doGet(request, response);

        verify(success, atLeastOnce()).successResponse(response, 200);
        verify(service, atLeastOnce()).getStudentsCheck(any());
    }

    @Test
    void doPostTest() throws IOException {
        when(request.getParameter("surname")).thenReturn("Surname");
        when(request.getParameter("name")).thenReturn("Name");
        when(request.getParameter("group")).thenReturn("Group");
        when(request.getParameter("date")).thenReturn("2023-10-10");

        when(service.createStudentCheck(anyString(), anyString(), anyString(), anyString())).thenReturn("2");

        controller.doPost(request, response);

        verify(success, atLeastOnce()).successResponse(response, 201);
        verify(service, atLeastOnce()).createStudentCheck(anyString(), anyString(), anyString(), anyString());
    }

    @Test
    void doPutTest() throws IOException {
        when(request.getPathInfo()).thenReturn("/1");
        when(request.getParameter("surname")).thenReturn("Surname");
        when(request.getParameter("name")).thenReturn("Name");
        when(request.getParameter("group")).thenReturn("Group");
        when(request.getParameter("date")).thenReturn("2023-10-10");

        when(service.updateStudentCheck(any(), anyString(), anyString(), anyString(), anyString())).thenReturn("2");

        controller.doPut(request, response);

        verify(success, atLeastOnce()).successResponse(response, 200);
        verify(service, atLeastOnce()).updateStudentCheck(any(), anyString(), anyString(), anyString(), anyString());
    }

    @Test
    void doDeleteTest() throws IOException {
        when(request.getPathInfo()).thenReturn("/1");

        when(service.deleteStudentCheck(any())).thenReturn("2");

        controller.doDelete(request, response);

        verify(success, atLeastOnce()).successResponse(response, 200);
        verify(service, atLeastOnce()).deleteStudentCheck(any());
    }
}
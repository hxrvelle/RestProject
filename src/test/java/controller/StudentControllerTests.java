package controller;

import org.example.controller.StudentController;
import org.example.controller.dto.StudentOutgoingDto;
import org.example.controller.responseHandlers.StudentErrorResponses;
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

import static org.mockito.Mockito.*;

public class StudentControllerTests {
    @InjectMocks
    private StudentController controller;
    @Mock
    private StudentServiceImpl service;
    @Mock
    private SuccessResponse success;
    @Mock
    private StudentErrorResponses error;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private PrintWriter writer;

    @BeforeEach
    void setUp() {
        controller = new StudentController();
        service = new StudentServiceImpl();
        success = new SuccessResponse();
        error = new StudentErrorResponses();

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
    void doGetAllStudentsTestError_noStudents() throws IOException {
        when(request.getPathInfo()).thenReturn("/");
        when(service.getStudentsCheck(any())).thenReturn("2");
        when(response.getWriter()).thenReturn(writer);

        String[] path = {""};

        service.getStudentsCheck(path);
        controller.doGet(request, response);

        verify(error, atLeastOnce()).noStudents(response);
        verify(service, times(0)).getAllActiveStudents();
    }

    @Test
    void doGetStudentByIdTest() throws IOException {
        when(request.getPathInfo()).thenReturn("/1");
        when(service.getStudentById(1)).thenReturn(new StudentOutgoingDto());
        when(service.getStudentsCheck(any())).thenReturn("1");
        when(response.getWriter()).thenReturn(writer);

        controller.doGet(request, response);

        verify(success, atLeastOnce()).successResponse(response, 200);
        verify(service, atLeastOnce()).getStudentsCheck(any());
    }

    @Test
    void doGetStudentByIdTestError_noStudentId() throws IOException {
        when(request.getPathInfo()).thenReturn("/");
        when(service.getStudentsCheck(any())).thenReturn("0");
        when(response.getWriter()).thenReturn(writer);

        String[] path = {""};

        service.getStudentsCheck(path);
        controller.doGet(request, response);

        verify(error, atLeastOnce()).noStudentId(response);
        verify(service, times(0)).getStudentById(anyInt());
    }

    @Test
    void doGetStudentByIdTestError_invalidStudentId() throws IOException {
        when(request.getPathInfo()).thenReturn("/1111");
        when(service.getStudentsCheck(any())).thenReturn("4");
        when(response.getWriter()).thenReturn(writer);

        String[] path = {""};

        service.getStudentsCheck(path);
        controller.doGet(request, response);

        verify(error, atLeastOnce()).invalidStudentId(response);
        verify(service, times(0)).getStudentById(anyInt());
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
    void doPostTestError_parametersMissing() throws IOException {
        when(request.getParameter("surname")).thenReturn("");
        when(request.getParameter("name")).thenReturn("");
        when(request.getParameter("group")).thenReturn("");
        when(request.getParameter("date")).thenReturn("");

        when(service.createStudentCheck(anyString(), anyString(), anyString(), anyString())).thenReturn("0");

        controller.doPost(request, response);

        verify(error, atLeastOnce()).parametersMissing(response);
        verify(service, times(0)).createStudent(any());
    }

    @Test
    void doPostTestError_dateFormat() throws IOException {
        when(request.getParameter("surname")).thenReturn("Surname");
        when(request.getParameter("name")).thenReturn("Name");
        when(request.getParameter("group")).thenReturn("Group");
        when(request.getParameter("date")).thenReturn("12-11-2010");

        when(service.createStudentCheck(anyString(), anyString(), anyString(), anyString())).thenReturn("1");

        controller.doPost(request, response);

        verify(error, atLeastOnce()).dateFormat(response);
        verify(service, times(0)).createStudent(any());
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
    void doPutTestError_noStudentId() throws IOException {
        when(request.getPathInfo()).thenReturn("/");
        when(request.getParameter("surname")).thenReturn("Surname");
        when(request.getParameter("name")).thenReturn("Name");
        when(request.getParameter("group")).thenReturn("Group");
        when(request.getParameter("date")).thenReturn("2023-10-10");

        when(service.updateStudentCheck(any(), anyString(), anyString(), anyString(), anyString())).thenReturn("0");

        controller.doPut(request, response);

        verify(error, atLeastOnce()).noStudentId(response);
        verify(service, times(0)).modifyStudent(anyInt(), any());
    }

    @Test
    void doPutTestError_parametersMissing() throws IOException {
        when(request.getPathInfo()).thenReturn("/1");
        when(request.getParameter("surname")).thenReturn("");
        when(request.getParameter("name")).thenReturn("");
        when(request.getParameter("group")).thenReturn("");
        when(request.getParameter("date")).thenReturn("");

        when(service.updateStudentCheck(any(), anyString(), anyString(), anyString(), anyString())).thenReturn("3");

        controller.doPut(request, response);

        verify(error, atLeastOnce()).noParameters(response);
        verify(service, times(0)).modifyStudent(anyInt(), any());
    }

    @Test
    void doDeleteTest() throws IOException {
        when(request.getPathInfo()).thenReturn("/1");
        when(service.deleteStudentCheck(any())).thenReturn("2");

        controller.doDelete(request, response);

        verify(success, atLeastOnce()).successResponse(response, 200);
        verify(service, atLeastOnce()).deleteStudentCheck(any());
    }

    @Test
    void doDeleteTestError_noStudentId() throws IOException {
        when(request.getPathInfo()).thenReturn("/");
        when(service.deleteStudentCheck(any())).thenReturn("0");

        controller.doDelete(request, response);

        verify(error, atLeastOnce()).noStudentId(response);
        verify(service, times(0)).deleteStudent(anyInt());
    }

    @Test
    void doDeleteTestError_studentDoesntExist() throws IOException {
        when(request.getPathInfo()).thenReturn("/1111");
        when(service.deleteStudentCheck(any())).thenReturn("1");

        controller.doDelete(request, response);

        verify(error, atLeastOnce()).studentDoesntExist(response);
        verify(service, times(0)).deleteStudent(anyInt());
    }
}
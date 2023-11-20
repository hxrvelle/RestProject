package controller;

import org.example.controller.PhoneController;
import org.example.controller.dto.PhoneOutgoingDto;
import org.example.controller.dto.StudentIncomingDto;
import org.example.controller.responseHandlers.general.SuccessResponse;
import org.example.service.impl.PhoneServiceImpl;
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
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PhoneControllerTests {
    @InjectMocks
    private PhoneController controller;
    @Mock
    private PhoneServiceImpl service;
    @Mock
    private SuccessResponse success;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private PrintWriter writer;

    @BeforeEach
    void setUp() {
        controller = new PhoneController();
        service = new PhoneServiceImpl();
        success = new SuccessResponse();

        MockitoAnnotations.openMocks(this);
        StringWriter stringWriter = new StringWriter();
        writer = new PrintWriter(stringWriter);
    }

    @Test
    void doGetStudentPhonesTest() throws IOException {
        when(request.getPathInfo()).thenReturn("/1");
        when(service.getStudentPhones(1)).thenReturn(new ArrayList<>());
        when(service.getStudentPhonesCheck(any())).thenReturn("3");
        when(response.getWriter()).thenReturn(writer);

        controller.doGet(request, response);

        verify(success, atLeastOnce()).successResponse(response, 200);
        verify(service, atLeastOnce()).getStudentPhones(1);
    }

    @Test
    void doPostTest() throws IOException {
        when(request.getPathInfo()).thenReturn("/1");
        when(request.getParameter("number")).thenReturn("999");

        when(service.addStudentPhoneCheck(any(), anyString())).thenReturn("3");

        controller.doPost(request, response);

        verify(success, atLeastOnce()).successResponse(response, 201);
        verify(service, atLeastOnce()).addStudentPhoneCheck(any(), anyString());
    }

    @Test
    void doPutTest() throws IOException {
        when(request.getPathInfo()).thenReturn("/2");
        when(request.getParameter("number")).thenReturn("888");

        when(service.updateStudentPhoneCheck(any(), anyString())).thenReturn("2");

        controller.doPut(request, response);

        verify(success, atLeastOnce()).successResponse(response, 200);
        verify(service, atLeastOnce()).updateStudentPhoneCheck(any(), anyString());
    }

    @Test
    void doDeleteTest() throws IOException {
        when(request.getPathInfo()).thenReturn("/1");

        when(service.deleteStudentPhoneCheck(any())).thenReturn("2");

        controller.doDelete(request, response);

        verify(success, atLeastOnce()).successResponse(response, 200);
        verify(service, atLeastOnce()).deleteStudentPhoneCheck(any());
    }
}

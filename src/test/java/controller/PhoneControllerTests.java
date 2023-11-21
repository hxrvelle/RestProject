package controller;

import org.example.controller.PhoneController;
import org.example.controller.responseHandlers.PhoneErrorResponses;
import org.example.controller.responseHandlers.StudentErrorResponses;
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
    private PhoneErrorResponses error;
    @Mock
    private StudentErrorResponses sError;
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
        error = new PhoneErrorResponses();
        sError = new StudentErrorResponses();

        MockitoAnnotations.openMocks(this);
        StringWriter stringWriter = new StringWriter();
        writer = new PrintWriter(stringWriter);
    }

    @Test
    void doGetStudentPhonesTest() throws IOException {
        when(request.getPathInfo()).thenReturn("/1");
        when(service.getStudentPhonesCheck(any())).thenReturn("3");
        when(response.getWriter()).thenReturn(writer);

        controller.doGet(request, response);

        verify(success, atLeastOnce()).successResponse(response, 200);
        verify(service, atLeastOnce()).getStudentPhones(1);
    }

    @Test
    void doGetStudentPhonesTestError_noPhoneNumbers() throws IOException {
        when(request.getPathInfo()).thenReturn("/1");
        when(service.getStudentPhonesCheck(any())).thenReturn("2");
        when(response.getWriter()).thenReturn(writer);

        controller.doGet(request, response);

        verify(error, atLeastOnce()).noPhoneNumbers(response);
        verify(service, times(0)).getStudentPhones(anyInt());
    }
    @Test
    void doGetStudentPhonesTestError_noStudentId() throws IOException {
        when(request.getPathInfo()).thenReturn("/");
        when(service.getStudentPhonesCheck(any())).thenReturn("0");
        when(response.getWriter()).thenReturn(writer);

        controller.doGet(request, response);

        verify(sError, atLeastOnce()).noStudentId(response);
        verify(service, times(0)).getStudentPhones(anyInt());
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
    void doPostTestError_noPhone() throws IOException {
        when(request.getPathInfo()).thenReturn("/1");
        when(request.getParameter("number")).thenReturn("");

        when(service.addStudentPhoneCheck(any(), anyString())).thenReturn("2");

        controller.doPost(request, response);

        verify(error, atLeastOnce()).noPhone(response);
        verify(service, times(0)).addStudentPhone(any());
    }

    @Test
    void doPostTestError_noStudentId() throws IOException {
        when(request.getPathInfo()).thenReturn("/");
        when(request.getParameter("number")).thenReturn("888");

        when(service.addStudentPhoneCheck(any(), anyString())).thenReturn("0");

        controller.doPost(request, response);

        verify(sError, atLeastOnce()).noStudentId(response);
        verify(service, times(0)).addStudentPhone(any());
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
    void doPutTestError_noPhoneId() throws IOException {
        when(request.getPathInfo()).thenReturn("/");
        when(request.getParameter("number")).thenReturn("888");

        when(service.updateStudentPhoneCheck(any(), anyString())).thenReturn("3");

        controller.doPut(request, response);

        verify(error, atLeastOnce()).noPhoneId(response);
        verify(service, times(0)).updateStudentPhone(anyInt(), any());
    }

    @Test
    void doPutTestError_noPhone() throws IOException {
        when(request.getPathInfo()).thenReturn("/1");
        when(request.getParameter("number")).thenReturn("");

        when(service.updateStudentPhoneCheck(any(), anyString())).thenReturn("1");

        controller.doPut(request, response);

        verify(error, atLeastOnce()).noPhone(response);
        verify(service, times(0)).updateStudentPhone(anyInt(), any());
    }

    @Test
    void doDeleteTest() throws IOException {
        when(request.getPathInfo()).thenReturn("/1");

        when(service.deleteStudentPhoneCheck(any())).thenReturn("2");

        controller.doDelete(request, response);

        verify(success, atLeastOnce()).successResponse(response, 200);
        verify(service, atLeastOnce()).deleteStudentPhoneCheck(any());
    }

    @Test
    void doDeleteTestError_noPhoneId() throws IOException {
        when(request.getPathInfo()).thenReturn("/");

        when(service.deleteStudentPhoneCheck(any())).thenReturn("0");

        controller.doDelete(request, response);

        verify(error, atLeastOnce()).noPhoneId(response);
        verify(service, times(0)).deleteStudentPhone(anyInt());
    }

    @Test
    void doDeleteTestError_phoneDoesntExist() throws IOException {
        when(request.getPathInfo()).thenReturn("/1111");

        when(service.deleteStudentPhoneCheck(any())).thenReturn("1");

        controller.doDelete(request, response);

        verify(error, atLeastOnce()).phoneDoesntExist(response);
        verify(service, times(0)).deleteStudentPhone(anyInt());
    }
}

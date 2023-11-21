package controller;

import org.example.controller.TermController;
import org.example.controller.dto.TermOutgoingDto;
import org.example.controller.responseHandlers.TermErrorResponses;
import org.example.controller.responseHandlers.general.SuccessResponse;
import org.example.service.impl.TermServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.atLeastOnce;

public class TermControllerTests {
    @InjectMocks
    private TermController controller;
    @Mock
    private TermServiceImpl service;
    @Mock
    private SuccessResponse success;
    @Mock
    private TermErrorResponses error;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private PrintWriter writer;

    @BeforeEach
    void setUp() {
        controller = new TermController();
        service = new TermServiceImpl();
        success = new SuccessResponse();
        error = new TermErrorResponses();

        MockitoAnnotations.openMocks(this);
        StringWriter stringWriter = new StringWriter();
        writer = new PrintWriter(stringWriter);
    }

    @Test
    void doGetAllTermsTest() throws IOException {
        when(request.getPathInfo()).thenReturn("/");
        when(service.getTermsCheck(any())).thenReturn("9");
        when(response.getWriter()).thenReturn(writer);

        String[] path = {""};

        service.getTermsCheck(path);
        controller.doGet(request, response);

        verify(success, atLeastOnce()).successResponse(response, 200);
        verify(service, atLeastOnce()).getTermsCheck(any());
    }

    @Test
    void doGetAllTermsTestError_noTerms() throws IOException {
        when(request.getPathInfo()).thenReturn("/");
        when(service.getTermsCheck(any())).thenReturn("8");
        when(response.getWriter()).thenReturn(writer);

        String[] path = {""};

        service.getTermsCheck(path);
        controller.doGet(request, response);

        verify(error, atLeastOnce()).noTerms(response);
        verify(service, times(0)).getAllActiveTerm();
    }

    @Test
    void doGetTermByIdTest() throws IOException {
        when(request.getPathInfo()).thenReturn("/1");
        when(service.getTermById(1)).thenReturn(new TermOutgoingDto());
        when(service.getTermsCheck(any())).thenReturn("0");
        when(response.getWriter()).thenReturn(writer);

        controller.doGet(request, response);

        verify(success, atLeastOnce()).successResponse(response, 200);
        verify(service, atLeastOnce()).getTermsCheck(any());
    }

    @Test
    void doGetTermByIdTestError_termDoesntExist() throws IOException {
        when(request.getPathInfo()).thenReturn("/1111");
        when(service.getTermsCheck(any())).thenReturn("00");
        when(response.getWriter()).thenReturn(writer);

        controller.doGet(request, response);

        verify(error, atLeastOnce()).termDoesntExist(response);
        verify(service, times(0)).getTermById(anyInt());
    }

    @Test
    void doGetTermDisciplinesTest() throws IOException {
        when(request.getPathInfo()).thenReturn("/1/disciplines");
        when(service.getTermDisciplines(1)).thenReturn(new ArrayList<>());
        when(service.getTermsCheck(any())).thenReturn("5");
        when(response.getWriter()).thenReturn(writer);

        controller.doGet(request, response);

        verify(success, atLeastOnce()).successResponse(response, 200);
        verify(service, atLeastOnce()).getTermsCheck(any());
    }

    @Test
    void doGetTermDisciplinesTestError_noDisciplines() throws IOException {
        when(request.getPathInfo()).thenReturn("/1/disciplines");
        when(service.getTermsCheck(any())).thenReturn("4");
        when(response.getWriter()).thenReturn(writer);

        controller.doGet(request, response);

        verify(error, atLeastOnce()).noDisciplines(response);
        verify(service, times(0)).getTermDisciplines(anyInt());
    }

    @Test
    void doPostTest() throws SQLException, IOException {
        when(request.getParameter("disciplines")).thenReturn("1,2,3");
        when(request.getParameter("duration")).thenReturn("6 months");
        when(service.createTermCheck(anyString(), anyString())).thenReturn("1");

        controller.doPost(request, response);

        verify(success, atLeastOnce()).successResponse(response, 201);
        verify(service, atLeastOnce()).createTermCheck(anyString(), anyString());
    }

    @Test
    void doPostTestError_noDisciplinesProvided() throws SQLException, IOException {
        when(request.getParameter("disciplines")).thenReturn("");
        when(request.getParameter("duration")).thenReturn("6 months");
        when(service.createTermCheck(anyString(), anyString())).thenReturn("0");

        controller.doPost(request, response);

        verify(error, atLeastOnce()).noDisciplinesProvided(response);
        verify(service, times(0)).createTerm(any());
    }

    @Test
    void doPostTestError_invalidDisciplineId() throws SQLException, IOException {
        when(request.getParameter("disciplines")).thenReturn("999");
        when(request.getParameter("duration")).thenReturn("6 months");
        when(service.createTermCheck(anyString(), anyString())).thenReturn("2");

        controller.doPost(request, response);

        verify(error, atLeastOnce()).invalidDisciplineId(response);
        verify(service, times(0)).createTerm(any());
    }

    @Test
    void doPutTest() throws SQLException, IOException {
        when(request.getPathInfo()).thenReturn("/1");
        when(request.getParameter("disciplines")).thenReturn("1,2,3");
        when(request.getParameter("duration")).thenReturn("6 months");
        when(service.modifyTermCheck(any(), anyString(), anyString())).thenReturn("6");

        controller.doPut(request, response);

        verify(success, atLeastOnce()).successResponse(response, 200);
        verify(service, atLeastOnce()).modifyTermCheck(any(), anyString(), anyString());
    }

    @Test
    void doPutTestError_invalidTermId() throws SQLException, IOException {
        when(request.getPathInfo()).thenReturn("/1111");
        when(request.getParameter("disciplines")).thenReturn("1,2,3");
        when(request.getParameter("duration")).thenReturn("6 months");
        when(service.modifyTermCheck(any(), anyString(), anyString())).thenReturn("0");

        controller.doPut(request, response);

        verify(error, atLeastOnce()).invalidTermId(response);
        verify(service, times(0)).modifyTerm(any(), anyInt());
    }

    @Test
    void doPutTestError_noTermId() throws SQLException, IOException {
        when(request.getPathInfo()).thenReturn("/");
        when(request.getParameter("disciplines")).thenReturn("1,2,3");
        when(request.getParameter("duration")).thenReturn("6 months");
        when(service.modifyTermCheck(any(), anyString(), anyString())).thenReturn("1");

        controller.doPut(request, response);

        verify(error, atLeastOnce()).noTermId(response);
        verify(service, times(0)).modifyTerm(any(), anyInt());
    }

    @Test
    void doDeleteTest() throws IOException {
        when(request.getPathInfo()).thenReturn("/1");
        when(service.deleteTermCheck(any())).thenReturn("2");

        controller.doDelete(request, response);

        verify(success, atLeastOnce()).successResponse(response, 200);
        verify(service, atLeastOnce()).deleteTermCheck(any());
    }

    @Test
    void doDeleteTestError_noTermId() throws IOException {
        when(request.getPathInfo()).thenReturn("/");
        when(service.deleteTermCheck(any())).thenReturn("0");

        controller.doDelete(request, response);

        verify(error, atLeastOnce()).noTermId(response);
        verify(service, times(0)).deleteTerm(anyInt());
    }

    @Test
    void doDeleteTestError_termDoesntExist() throws IOException {
        when(request.getPathInfo()).thenReturn("/1111");
        when(service.deleteTermCheck(any())).thenReturn("1");

        controller.doDelete(request, response);

        verify(error, atLeastOnce()).termDoesntExist(response);
        verify(service, times(0)).deleteTerm(anyInt());
    }
}

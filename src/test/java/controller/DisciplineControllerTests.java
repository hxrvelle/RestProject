package controller;

import org.example.controller.DisciplineController;
import org.example.controller.dto.DisciplineOutgoingDto;
import org.example.controller.dto.TermOutgoingDto;
import org.example.controller.responseHandlers.DisciplineErrorResponses;
import org.example.controller.responseHandlers.general.SuccessResponse;
import org.example.service.impl.DisciplineServiceImpl;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class DisciplineControllerTests {
    @InjectMocks
    private DisciplineController controller;
    @Mock
    private DisciplineServiceImpl service;
    @Mock
    private SuccessResponse success;
    @Mock
    private DisciplineErrorResponses error;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private PrintWriter writer;

    @BeforeEach
    void setUp() {
        controller = new DisciplineController();
        service = new DisciplineServiceImpl();
        success = new SuccessResponse();
        error = new DisciplineErrorResponses();

        MockitoAnnotations.openMocks(this);
        StringWriter stringWriter = new StringWriter();
        writer = new PrintWriter(stringWriter);
    }

    @Test
    void doGetAllDisciplinesTest() throws IOException {
        when(request.getPathInfo()).thenReturn("/");
        when(service.getDisciplinesCheck(any())).thenReturn("9");
        when(response.getWriter()).thenReturn(writer);

        String[] path = {""};

        service.getDisciplinesCheck(path);
        controller.doGet(request, response);

        verify(success, atLeastOnce()).successResponse(response, 200);
        verify(service, atLeastOnce()).getDisciplinesCheck(any());
    }

    @Test
    void doGetAllDisciplinesTestError_noDisciplines() throws IOException {
        when(request.getPathInfo()).thenReturn("/");
        when(service.getDisciplinesCheck(any())).thenReturn("8");
        when(response.getWriter()).thenReturn(writer);

        String[] path = {""};

        service.getDisciplinesCheck(path);
        controller.doGet(request, response);

        verify(error, atLeastOnce()).noDisciplines(response);
        verify(service, times(0)).getAllActiveDisciplines();
    }

    @Test
    void doGetDisciplineByIdTest() throws IOException {
        when(request.getPathInfo()).thenReturn("/1");
        when(service.getDisciplineById(1)).thenReturn(new DisciplineOutgoingDto());
        when(service.getDisciplinesCheck(any())).thenReturn("0");
        when(response.getWriter()).thenReturn(writer);

        controller.doGet(request, response);

        verify(success, atLeastOnce()).successResponse(response, 200);
        verify(service, atLeastOnce()).getDisciplinesCheck(any());
    }

    @Test
    void doGetDisciplineByIdTestError_disciplineDoesntExist() throws IOException {
        when(request.getPathInfo()).thenReturn("/111");
        when(service.getDisciplinesCheck(any())).thenReturn("00");
        when(response.getWriter()).thenReturn(writer);

        String[] path = {""};

        service.getDisciplinesCheck(path);
        controller.doGet(request, response);

        verify(error, atLeastOnce()).diesciplineDoesntExist(response);
        verify(service, times(0)).getDisciplineById(anyInt());
    }

    @Test
    void doGetDisciplineTermsTest() throws IOException {
        when(request.getPathInfo()).thenReturn("/1/disciplines");
        when(service.getDisciplineTerms(1)).thenReturn(new ArrayList<>());
        when(service.getDisciplinesCheck(any())).thenReturn("5");
        when(response.getWriter()).thenReturn(writer);

        controller.doGet(request, response);

        verify(success, atLeastOnce()).successResponse(response, 200);
        verify(service, atLeastOnce()).getDisciplinesCheck(any());
    }

    @Test
    void doGetDisciplineTermsTestError_noTerms() throws IOException {
        when(request.getPathInfo()).thenReturn("/1");
        when(service.getDisciplinesCheck(any())).thenReturn("4");
        when(response.getWriter()).thenReturn(writer);

        String[] path = {""};

        service.getDisciplinesCheck(path);
        controller.doGet(request, response);

        verify(error, atLeastOnce()).noTerms(response);
        verify(service, times(0)).getDisciplineTerms(anyInt());
    }

    @Test
    void doPostTest() throws IOException {
        when(request.getParameter("discipline")).thenReturn("Discipline");
        when(service.createDisciplineCheck(anyString())).thenReturn("1");

        controller.doPost(request, response);

        verify(success, atLeastOnce()).successResponse(response, 201);
        verify(service, atLeastOnce()).createDisciplineCheck(anyString());
    }

    @Test
    void doPostTestError_noDisciplineName() throws IOException {
        when(request.getParameter("discipline")).thenReturn("");
        when(service.createDisciplineCheck(anyString())).thenReturn("0");

        controller.doPost(request, response);

        verify(error, atLeastOnce()).noDisciplineName(response);
        verify(service, times(0)).createDiscipline(any());
    }

    @Test
    void doPutTest() throws IOException {
        when(request.getPathInfo()).thenReturn("/1");
        when(request.getParameter("discipline")).thenReturn("Discipline");
        when(service.modifyDisciplineCheck(any(), anyString())).thenReturn("2");

        controller.doPut(request, response);

        verify(success, atLeastOnce()).successResponse(response, 200);
        verify(service, atLeastOnce()).modifyDisciplineCheck(any(), anyString());
    }

    @Test
    void doPutTestError_noDisciplineId() throws IOException {
        when(request.getPathInfo()).thenReturn("/");
        when(request.getParameter("discipline")).thenReturn("Discipline");
        when(service.modifyDisciplineCheck(any(), anyString())).thenReturn("0");

        controller.doPut(request, response);

        verify(error, atLeastOnce()).noDisciplineId(response);
        verify(service, times(0)).modifyDiscipline(any(), anyInt());
    }

    @Test
    void doPutTestError_disciplineDoesntExist() throws IOException {
        when(request.getPathInfo()).thenReturn("/1111");
        when(request.getParameter("discipline")).thenReturn("Discipline");
        when(service.modifyDisciplineCheck(any(), anyString())).thenReturn("1");

        controller.doPut(request, response);

        verify(error, atLeastOnce()).diesciplineDoesntExist(response);
        verify(service, times(0)).modifyDiscipline(any(), anyInt());
    }

    @Test
    void doDeleteTest() throws IOException {
        when(request.getPathInfo()).thenReturn("/1");
        when(service.deleteDisciplineCheck(any())).thenReturn("2");

        controller.doDelete(request, response);

        verify(success, atLeastOnce()).successResponse(response, 200);
        verify(service, atLeastOnce()).deleteDisciplineCheck(any());
    }

    @Test
    void doDeleteTestError_noDisciplineId() throws IOException {
        when(request.getPathInfo()).thenReturn("/");
        when(service.deleteDisciplineCheck(any())).thenReturn("0");

        controller.doDelete(request, response);

        verify(error, atLeastOnce()).noDisciplineId(response);
        verify(service, times(0)).deleteDiscipline(anyInt());
    }

    @Test
    void doDeleteTestError_invalidDisciplineId() throws IOException {
        when(request.getPathInfo()).thenReturn("/aaa");
        when(service.deleteDisciplineCheck(any())).thenReturn("3");

        controller.doDelete(request, response);

        verify(error, atLeastOnce()).invalidDisciplineId(response);
        verify(service, times(0)).deleteDiscipline(anyInt());
    }
}

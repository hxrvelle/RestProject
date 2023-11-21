package controller.responseHandlersTests;

import org.example.controller.responseHandlers.TermErrorResponses;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TermErrorResponsesTests {
    @InjectMocks
    private TermErrorResponses responses;
    @Mock
    private HttpServletResponse response;
    @Mock
    private PrintWriter writer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        responses = new TermErrorResponses();
        writer = new PrintWriter(new StringWriter());
    }

    @Test
    void termDoesntExistTest() throws IOException {
        when(response.getWriter()).thenReturn(writer);
        responses.termDoesntExist(response);
        verify(response).getWriter();
    }

    @Test
    void noTermsTest() throws IOException {
        when(response.getWriter()).thenReturn(writer);
        responses.noTerms(response);
        verify(response).getWriter();
    }

    @Test
    void noTermIdTest() throws IOException {
        when(response.getWriter()).thenReturn(writer);
        responses.noTermId(response);
        verify(response).getWriter();
    }

    @Test
    void noDisciplinesTest() throws IOException {
        when(response.getWriter()).thenReturn(writer);
        responses.noDisciplines(response);
        verify(response).getWriter();
    }

    @Test
    void noDisciplinesProvidedTest() throws IOException {
        when(response.getWriter()).thenReturn(writer);
        responses.noDisciplinesProvided(response);
        verify(response).getWriter();
    }

    @Test
    void disciplineDoesntExistTest() throws IOException {
        when(response.getWriter()).thenReturn(writer);
        responses.disciplineDoesntExist(response);
        verify(response).getWriter();
    }

    @Test
    void invalidTermIdTest() throws IOException {
        when(response.getWriter()).thenReturn(writer);
        responses.invalidTermId(response);
        verify(response).getWriter();
    }

    @Test
    void invalidDisciplineIdTest() throws IOException {
        when(response.getWriter()).thenReturn(writer);
        responses.invalidDisciplineId(response);
        verify(response).getWriter();
    }

    @Test
    void noChangesTest() throws IOException {
        when(response.getWriter()).thenReturn(writer);
        responses.noChanges(response);
        verify(response).getWriter();
    }

    @Test
    void invalidRequestTest() throws IOException {
        when(response.getWriter()).thenReturn(writer);
        responses.invalidRequest(response);
        verify(response).getWriter();
    }
}

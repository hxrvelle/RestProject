package controller.responseHandlersTests;

import org.example.controller.responseHandlers.DisciplineErrorResponses;
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

public class DisciplineErrorResponsesTests {
    @InjectMocks
    private DisciplineErrorResponses responses;
    @Mock
    private HttpServletResponse response;
    @Mock
    private PrintWriter writer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        responses = new DisciplineErrorResponses();
        writer = new PrintWriter(new StringWriter());
    }

    @Test
    void diesciplineDoesntExistTest() throws IOException {
        when(response.getWriter()).thenReturn(writer);
        responses.diesciplineDoesntExist(response);
        verify(response).getWriter();
    }

    @Test
    void noDisciplinesTest() throws IOException {
        when(response.getWriter()).thenReturn(writer);
        responses.noDisciplines(response);
        verify(response).getWriter();
    }

    @Test
    void noDisciplineIdTest() throws IOException {
        when(response.getWriter()).thenReturn(writer);
        responses.noDisciplineId(response);
        verify(response).getWriter();
    }

    @Test
    void noTermsTest() throws IOException {
        when(response.getWriter()).thenReturn(writer);
        responses.noTerms(response);
        verify(response).getWriter();
    }

    @Test
    void noDisciplineNameTest() throws IOException {
        when(response.getWriter()).thenReturn(writer);
        responses.noDisciplineName(response);
        verify(response).getWriter();
    }

    @Test
    void invalidDisciplineIdTest() throws IOException {
        when(response.getWriter()).thenReturn(writer);
        responses.invalidDisciplineId(response);
        verify(response).getWriter();
    }

    @Test
    void invalidRequestTest() throws IOException {
        when(response.getWriter()).thenReturn(writer);
        responses.invalidRequest(response);
        verify(response).getWriter();
    }
}

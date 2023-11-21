package controller.responseHandlersTests;

import org.example.controller.responseHandlers.StudentErrorResponses;
import org.example.controller.responseHandlers.general.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.mockito.Mockito.*;

public class StudentErrorResponsesTests {
    @InjectMocks
    private StudentErrorResponses responses;
    @Mock
    private HttpServletResponse response;
    @Mock
    private PrintWriter writer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        responses = new StudentErrorResponses();
        writer = new PrintWriter(new StringWriter());
    }

    @Test
    void noStudentIdTest() throws IOException {
        when(response.getWriter()).thenReturn(writer);
        responses.noStudentId(response);
        verify(response).getWriter();
    }

    @Test
    void studentDoesntExistTest() throws IOException {
        when(response.getWriter()).thenReturn(writer);
        responses.studentDoesntExist(response);
        verify(response).getWriter();
    }

    @Test
    void noStudentsTest() throws IOException {
        when(response.getWriter()).thenReturn(writer);
        responses.noStudents(response);
        verify(response).getWriter();
    }

    @Test
    void invalidStudentIdTest() throws IOException {
        when(response.getWriter()).thenReturn(writer);
        responses.invalidStudentId(response);
        verify(response).getWriter();
    }

    @Test
    void noParametersTest() throws IOException {
        when(response.getWriter()).thenReturn(writer);
        responses.noParameters(response);
        verify(response).getWriter();
    }

    @Test
    void parametersMissingTest() throws IOException {
        when(response.getWriter()).thenReturn(writer);
        responses.parametersMissing(response);
        verify(response).getWriter();
    }

    @Test
    void dateFormatTest() throws IOException {
        when(response.getWriter()).thenReturn(writer);
        responses.dateFormat(response);
        verify(response).getWriter();
    }
}

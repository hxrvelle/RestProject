package controller.responseHandlersTests;

import org.example.controller.responseHandlers.PhoneErrorResponses;
import org.example.controller.responseHandlers.StudentErrorResponses;
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

public class PhoneErrorResponsesTests {
    @InjectMocks
    private PhoneErrorResponses responses;
    @Mock
    private HttpServletResponse response;
    @Mock
    private PrintWriter writer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        responses = new PhoneErrorResponses();
        writer = new PrintWriter(new StringWriter());
    }

    @Test
    void noPhoneNumbersTest() throws IOException {
        when(response.getWriter()).thenReturn(writer);
        responses.noPhoneNumbers(response);
        verify(response).getWriter();
    }

    @Test
    void noPhoneTest() throws IOException {
        when(response.getWriter()).thenReturn(writer);
        responses.noPhone(response);
        verify(response).getWriter();
    }

    @Test
    void noPhoneIdTest() throws IOException {
        when(response.getWriter()).thenReturn(writer);
        responses.noPhoneId(response);
        verify(response).getWriter();
    }

    @Test
    void phoneDoesntExistTest() throws IOException {
        when(response.getWriter()).thenReturn(writer);
        responses.phoneDoesntExist(response);
        verify(response).getWriter();
    }

    @Test
    void invalidPhoneIdTest() throws IOException {
        when(response.getWriter()).thenReturn(writer);
        responses.invalidPhoneId(response);
        verify(response).getWriter();
    }
}

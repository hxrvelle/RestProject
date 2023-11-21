package controller.responseHandlersTests.general;

import org.example.controller.responseHandlers.general.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.*;

public class ErrorResponseTests {
    @InjectMocks
    private ErrorResponse error;
    @Mock
    private HttpServletResponse response;

    @BeforeEach
    void setUp() {
        error = new ErrorResponse();

        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testErrorResponse() {
        error.errorResponse(response);

        verify(response, times(1)).setStatus(400);
        verify(response, times(1)).setContentType("application/json");
        verify(response, times(1)).setCharacterEncoding("UTF-8");

        verifyNoMoreInteractions(response);
    }
}

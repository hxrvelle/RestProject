package controller.responseHandlersTests.general;

import org.example.controller.responseHandlers.general.SuccessResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.*;

public class SuccessResponseTests {
    @InjectMocks
    private SuccessResponse successResponse;
    @Mock
    private HttpServletResponse response;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSuccessResponse() {
        int status = 200;

        successResponse.successResponse(response, status);

        verify(response, times(1)).setStatus(status);
        verify(response, times(1)).setContentType("application/json");
        verify(response, times(1)).setCharacterEncoding("UTF-8");

        verifyNoMoreInteractions(response);
    }
}
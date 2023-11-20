package controller;

import org.example.controller.PhoneController;
import org.example.controller.dto.PhoneOutgoingDto;
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
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PhoneControllerTests {
    @InjectMocks
    private PhoneController controller;
    @Mock
    PhoneServiceImpl service;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private PrintWriter writer;

    @BeforeEach
    void setUp() {
        controller = new PhoneController();
        MockitoAnnotations.openMocks(this);
        StringWriter stringWriter = new StringWriter();
        writer = new PrintWriter(stringWriter);
    }

    @Test
    void doGetStudentPhonesTest() throws IOException {
        List<PhoneOutgoingDto> phones = service.getStudentPhones(1);

        when(request.getPathInfo()).thenReturn("/1");
        when(service.getStudentPhones(1)).thenReturn(phones);
        when(response.getWriter()).thenReturn(writer);

        controller.doGet(request, response);

        verify(service).getStudentPhones(1);
    }
}

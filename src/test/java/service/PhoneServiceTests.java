package service;

import org.example.controller.dto.PhoneIncomingDto;
import org.example.controller.mapper.PhoneDtoMapper;
import org.example.model.Phone;
import org.example.model.Student;
import org.example.repository.impl.PhoneRepoImpl;
import org.example.repository.impl.StudentRepoImpl;
import org.example.service.impl.PhoneServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PhoneServiceTests {
    @InjectMocks
    private PhoneServiceImpl phoneService;
    @Mock
    private PhoneRepoImpl phoneRepo;
    @Mock
    private StudentRepoImpl studentRepo;
    @Mock
    private PhoneDtoMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getStudentPhonesTest() {
        int id = 1;

        when(phoneRepo.getStudentPhones(id)).thenReturn(new ArrayList<>());
        phoneService.getStudentPhones(id);

        verify(phoneRepo, atLeastOnce()).getStudentPhones(id);
    }

    @Test
    void addStudentPhoneTest() {
        phoneService.addStudentPhone(any());

        doNothing().when(phoneRepo).addStudentPhone(any());
        verify(phoneRepo, atLeastOnce()).addStudentPhone(any());
    }

    @Test
    void updateStudentPhone() {
        phoneService.updateStudentPhone(anyInt(), any());

        doNothing().when(phoneRepo).updateStudentPhone(anyInt(), any());
        verify(phoneRepo, atLeastOnce()).updateStudentPhone(anyInt(), any());
    }

    @Test
    void deleteStudentPhone() {
        phoneService.deleteStudentPhone(anyInt());

        doNothing().when(phoneRepo).deleteStudentPhone(anyInt());
        verify(phoneRepo, atLeastOnce()).deleteStudentPhone(anyInt());
    }

    //Check methods

    @Test
    void testGetStudentPhonesCheckInvalidIdInPath() {
        String[] path = {"", "abc"};

        String result = phoneService.getStudentPhonesCheck(path);

        assertEquals("4", result);
        verifyNoInteractions(phoneRepo);
    }

    @Test
    void testGetStudentPhonesCheckEmptyPath() {
        String[] path = {};

        String result = phoneService.getStudentPhonesCheck(path);

        assertEquals("0", result);
        verifyNoInteractions(phoneRepo);
    }

    @Test
    void testGetStudentPhonesCheckNonExistingStudent() {
        int id = 1;
        String[] path = {"", String.valueOf(id)};

        when(studentRepo.getStudentById(id)).thenReturn(new Student());

        String result = phoneService.getStudentPhonesCheck(path);

        assertEquals("1", result);
        verifyNoMoreInteractions(phoneRepo);
    }

    @Test
    void testAddStudentPhoneCheckInvalidIdInPath() {
        String[] path = {"", "abc"};
        String phoneNumber = "1234567890";

        String result = phoneService.addStudentPhoneCheck(path, phoneNumber);

        assertEquals("4", result);
        verifyNoInteractions(phoneRepo);
    }

    @Test
    void testAddStudentPhoneCheckEmptyPath() {
        String[] path = {};
        String phoneNumber = "1234567890";

        String result = phoneService.addStudentPhoneCheck(path, phoneNumber);

        assertEquals("0", result);
        verifyNoInteractions(phoneRepo);
    }

    @Test
    void testAddStudentPhoneCheckNonExistingStudent() {
        int id = 1;
        String[] path = {"", String.valueOf(id)};
        String phoneNumber = "1234567890";

        when(studentRepo.getStudentById(id)).thenReturn(new Student());

        String result = phoneService.addStudentPhoneCheck(path, phoneNumber);

        assertEquals("1", result);
        verifyNoMoreInteractions(phoneRepo);
    }

    @Test
    void testUpdateStudentPhoneCheckInvalidIdInPath() {
        String[] path = {"", "abc"};
        String phoneNumber = "1234567890";

        String result = phoneService.updateStudentPhoneCheck(path, phoneNumber);

        assertEquals("4", result);
        verifyNoInteractions(phoneRepo);
    }

    @Test
    void testUpdateStudentPhoneCheckEmptyPath() {
        String[] path = {};
        String phoneNumber = "1234567890";

        String result = phoneService.updateStudentPhoneCheck(path, phoneNumber);

        assertEquals("3", result);
        verifyNoInteractions(phoneRepo);
    }

    @Test
    void testDeleteStudentPhoneCheckInvalidIdInPath() {
        String[] path = {"", "abc"};

        String result = phoneService.deleteStudentPhoneCheck(path);

        assertEquals("3", result); // Expected status for invalid ID in path
        verifyNoInteractions(phoneRepo); // Ensure phoneRepo method was not called
    }

    @Test
    void testDeleteStudentPhoneCheckEmptyPath() {
        String[] path = {};

        String result = phoneService.deleteStudentPhoneCheck(path);

        assertEquals("0", result); // Expected status for empty path
        verifyNoInteractions(phoneRepo); // Ensure phoneRepo method was not called
    }
}

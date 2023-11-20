package service;

import org.example.controller.mapper.PhoneDtoMapper;
import org.example.repository.impl.PhoneRepoImpl;
import org.example.service.impl.PhoneServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class PhoneServiceTests {
    @InjectMocks
    private PhoneServiceImpl phoneService;
    @Mock
    private PhoneRepoImpl phoneRepo;
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
}

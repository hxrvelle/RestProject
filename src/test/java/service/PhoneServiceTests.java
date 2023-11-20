package service;

import org.example.controller.dto.PhoneIncomingDto;
import org.example.controller.dto.PhoneOutgoingDto;
import org.example.controller.mapper.PhoneDtoMapper;
import org.example.model.Phone;
import org.example.repository.impl.PhoneRepoImpl;
import org.example.service.impl.PhoneServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

public class PhoneServiceTests {
    @Mock
    private PhoneRepoImpl phoneRepo;
    @Mock
    private PhoneServiceImpl phoneService;
    @Mock
    PhoneDtoMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getStudentPhonesTest() {
        int id = 1;
        List<PhoneOutgoingDto> studentPhones = phoneService.getStudentPhones(id);

        Mockito.when(mapper.mapToDtoList(phoneRepo.getStudentPhones(id))).thenReturn(studentPhones);
        Mockito.verify(phoneRepo).getStudentPhones(id);
    }

    @Test
    void addStudentPhone() {
    }

    @Test
    void updateStudentPhone() {
    }

    @Test
    void deleteStudentPhone() {
    }
}

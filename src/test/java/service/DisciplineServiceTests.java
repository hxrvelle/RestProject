package service;

import org.example.controller.dto.DisciplineOutgoingDto;
import org.example.controller.mapper.DisciplineDtoMapper;
import org.example.controller.mapper.TermDtoMapper;
import org.example.repository.impl.DisciplineRepoImpl;
import org.example.service.impl.DisciplineServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DisciplineServiceTests {
    @InjectMocks
    private DisciplineServiceImpl disciplineService;
    @Mock
    private DisciplineRepoImpl disciplineRepo;
    @Mock
    private DisciplineDtoMapper dMapper;
    @Mock
    private TermDtoMapper tMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllActiveDisciplines() {
        List<DisciplineOutgoingDto> disciplines = disciplineService.getAllActiveDisciplines();

        Mockito.when(dMapper.mapToDtoList(disciplineRepo.getAllActiveDisciplines())).thenReturn(disciplines);
        Mockito.verify(disciplineRepo).getAllActiveDisciplines();
    }

}

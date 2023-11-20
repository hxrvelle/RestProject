package service;

import org.example.controller.dto.DisciplineIncomingDto;
import org.example.controller.dto.DisciplineOutgoingDto;
import org.example.controller.dto.TermIncomingDto;
import org.example.controller.dto.TermOutgoingDto;
import org.example.controller.mapper.DisciplineDtoMapper;
import org.example.controller.mapper.TermDtoMapper;
import org.example.model.Discipline;
import org.example.model.Term;
import org.example.repository.impl.DisciplineRepoImpl;
import org.example.repository.impl.TermRepoImpl;
import org.example.service.impl.TermServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class TermServiceTests {
    @InjectMocks
    private TermServiceImpl termService;
    @Mock
    private TermRepoImpl termRepo;
    @Mock
    private TermDtoMapper tMapper;
    @Mock
    private DisciplineDtoMapper dMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllActiveTermTest() {
        List<TermOutgoingDto> terms = termService.getAllActiveTerm();

        when(tMapper.mapToDtoList(termRepo.getAllActiveTerm())).thenReturn(terms);
        Mockito.verify(termRepo).getAllActiveTerm();
    }

    @Test
    void getTermDisciplinesTest() {
        int id = 1;
        List<DisciplineOutgoingDto> disciplines = termService.getTermDisciplines(id);

        when(dMapper.mapToDtoList(termRepo.getTermDisciplines(id))).thenReturn(disciplines);
        Mockito.verify(termRepo).getTermDisciplines(id);
    }

    @Test
    void getTermByIdTest() {
        int id = 1;
        TermOutgoingDto term = termService.getTermById(id);

        when(tMapper.mapToDto(termRepo.getTermById(id))).thenReturn(term);
        Mockito.verify(termRepo).getTermById(id);
    }

    @Test
    void createTermTest() {
    }

    @Test
    void modifyTermTest() {
    }

    @Test
    void deleteTermTest() {
    }
}

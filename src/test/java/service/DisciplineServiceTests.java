package service;

import org.example.controller.dto.DisciplineIncomingDto;
import org.example.model.Discipline;
import org.example.repository.impl.DisciplineRepoImpl;
import org.example.service.impl.DisciplineServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class DisciplineServiceTests {
    @InjectMocks
    private DisciplineServiceImpl disciplineService;
    @Mock
    private DisciplineRepoImpl disciplineRepo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllActiveDisciplinesTest() {
        when(disciplineRepo.getAllActiveDisciplines()).thenReturn(new ArrayList<>());
        disciplineService.getAllActiveDisciplines();

        verify(disciplineRepo, atLeastOnce()).getAllActiveDisciplines();
    }

    @Test
    void getDisciplineByIdTest() {
        int id = 1;

        when(disciplineRepo.getDisciplineById(id)).thenReturn(new Discipline());
        disciplineService.getDisciplineById(id);

        verify(disciplineRepo, atLeastOnce()).getDisciplineById(id);
    }

    @Test
    void getDisciplineTermsTest() {
        int id = 1;

        when(disciplineRepo.getDisciplineTerms(id)).thenReturn(new ArrayList<>());
        disciplineService.getDisciplineTerms(id);

        verify(disciplineRepo, atLeastOnce()).getDisciplineTerms(id);
    }

    @Test
    void createDisciplineTest() {
        DisciplineIncomingDto discipline = new DisciplineIncomingDto();
        discipline.setTerms(new ArrayList<>());

        disciplineService.createDiscipline(discipline);

        doNothing().when(disciplineRepo).createDiscipline(any());
        verify(disciplineRepo, atLeastOnce()).createDiscipline(any());
    }

    @Test
    void modifyDisciplineTest() {
        DisciplineIncomingDto discipline = new DisciplineIncomingDto();
        discipline.setTerms(new ArrayList<>());

        disciplineService.modifyDiscipline(discipline, 1);

        doNothing().when(disciplineRepo).modifyDiscipline(any(), anyInt());
        verify(disciplineRepo, atLeastOnce()).modifyDiscipline(any(), anyInt());
    }

    @Test
    void deleteDisciplineTest() {
        disciplineService.deleteDiscipline(anyInt());

        doNothing().when(disciplineRepo).deleteDiscipline(anyInt());
        verify(disciplineRepo, atLeastOnce()).deleteDiscipline(anyInt());
    }
}

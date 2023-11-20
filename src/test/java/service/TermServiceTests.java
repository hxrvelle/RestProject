package service;

import org.example.controller.dto.TermIncomingDto;
import org.example.model.Term;
import org.example.repository.impl.TermRepoImpl;
import org.example.service.impl.TermServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class TermServiceTests {
    @InjectMocks
    private TermServiceImpl termService;
    @Mock
    private TermRepoImpl termRepo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllActiveTermTest() {
        when(termRepo.getAllActiveTerm()).thenReturn(new ArrayList<>());
        termService.getAllActiveTerm();

        verify(termRepo, atLeastOnce()).getAllActiveTerm();
    }

    @Test
    void getTermDisciplinesTest() {
        int id = 1;

        when(termRepo.getTermDisciplines(id)).thenReturn(new ArrayList<>());
        termService.getTermDisciplines(id);

        verify(termRepo, atLeastOnce()).getTermDisciplines(id);
    }

    @Test
    void getTermByIdTest() {
        int id = 1;

        when(termRepo.getTermById(id)).thenReturn(new Term());
        termService.getTermById(id);

        verify(termRepo, atLeastOnce()).getTermById(id);
    }

    @Test
    void createTermTest() {
        TermIncomingDto term = new TermIncomingDto();
        term.setDisciplines(new ArrayList<>());

        termService.createTerm(term);

        doNothing().when(termRepo).createTerm(any());
        verify(termRepo, atLeastOnce()).createTerm(any());
    }

    @Test
    void modifyTermTest() {
        TermIncomingDto term = new TermIncomingDto();
        term.setDisciplines(new ArrayList<>());

        termService.modifyTerm(term, 1);

        doNothing().when(termRepo).modifyTerm(any(), anyInt());
        verify(termRepo, atLeastOnce()).modifyTerm(any(), anyInt());
    }

    @Test
    void deleteTermTest() {
        termService.deleteTerm(anyInt());

        doNothing().when(termRepo).deleteTerm(anyInt());
        verify(termRepo, atLeastOnce()).deleteTerm(anyInt());
    }
}

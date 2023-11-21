package service;

import org.example.controller.dto.TermIncomingDto;
import org.example.model.Discipline;
import org.example.model.Term;
import org.example.repository.impl.DisciplineRepoImpl;
import org.example.repository.impl.TermRepoImpl;
import org.example.service.impl.TermServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TermServiceTests {
    @InjectMocks
    private TermServiceImpl termService;
    @Mock
    private TermRepoImpl termRepo;
    @Mock
    private DisciplineRepoImpl disciplineRepo;

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

    //Check methods

    @Test
    void testGetTermsCheckPathLengthTwoInvalidId() {
        String[] path = {"", "abc"};

        String result = termService.getTermsCheck(path);

        assertEquals("1", result);
        verifyNoInteractions(termRepo);
    }
    @Test
    void testGetTermsCheckPathLengthGreaterThanTwoNonNumericId() {
        String[] path = {"", "abc", "disciplines"};

        String result = termService.getTermsCheck(path);

        assertEquals("7", result);
        verifyNoInteractions(termRepo);
    }
    @Test
    void testGetTermsCheckEmptyPath() {
        List<Term> terms = Collections.emptyList();

        when(termRepo.getAllActiveTerm()).thenReturn(terms);

        String[] path = {};

        String result = termService.getTermsCheck(path);

        assertEquals("8", result);
        verify(termRepo, times(1)).getAllActiveTerm();
    }

    @Test
    void testCreateTermCheckNullDisciplines() throws SQLException {
        String result = termService.createTermCheck(null, "10 weeks");

        assertEquals("0", result);
        verifyNoInteractions(disciplineRepo);
    }

    @Test
    void testCreateTermCheckEmptyDisciplines() throws SQLException {
        String result = termService.createTermCheck("", "10 weeks");

        assertEquals("0", result);
        verifyNoInteractions(disciplineRepo);
    }

    @Test
    void testCreateTermCheckInvalidDisciplines() throws SQLException {
        String result = termService.createTermCheck("abc,def", "10 weeks");

        assertEquals("2", result);
        verifyNoInteractions(disciplineRepo);
    }

    @Test
    void testCreateTermCheckNoMatchingDisciplines() throws SQLException {
        when(disciplineRepo.getDisciplineById(anyInt())).thenReturn(new Discipline());

        String result = termService.createTermCheck("1,2,3", "10 weeks");

        assertEquals("3", result);
        verify(disciplineRepo, times(3)).getDisciplineById(anyInt());
    }

    @Test
    void testModifyTermCheckInvalidId() throws SQLException {
        String[] path = {"", "abc"};

        String result = termService.modifyTermCheck(path, "1,2,3", "10 weeks");

        assertEquals("0", result);
        verifyNoInteractions(termRepo);
    }

    @Test
    void testModifyTermCheckNonExistingId() throws SQLException {
        int id = 1;
        String[] path = {"", String.valueOf(id)};

        when(termRepo.getTermById(id)).thenReturn(new Term());

        String result = termService.modifyTermCheck(path, "1,2,3", "10 weeks");

        assertEquals("2", result);
        verify(termRepo, times(1)).getTermById(id);
    }

    @Test
    void testDeleteTermCheckPathLengthLessThanOne() {
        String[] path = {};

        String result = termService.deleteTermCheck(path);

        assertEquals("0", result);
        verifyNoInteractions(termRepo);
    }

    @Test
    void testDeleteTermCheckInvalidPathLength() {
        String[] path = {"", "1", "2"};

        String result = termService.deleteTermCheck(path);

        assertEquals("3", result);
        verifyNoInteractions(termRepo);
    }

    @Test
    void testDeleteTermCheckInvalidId() {
        String[] path = {"", "abc"};

        String result = termService.deleteTermCheck(path);

        assertEquals("3", result);
        verifyNoInteractions(termRepo);
    }

    @Test
    void testDeleteTermCheckNonExistingId() {
        int id = 1;
        String[] path = {"", String.valueOf(id)};

        when(termRepo.getTermById(id)).thenReturn(new Term());

        String result = termService.deleteTermCheck(path);

        assertEquals("1", result);
        verify(termRepo, times(1)).getTermById(id);
    }
}

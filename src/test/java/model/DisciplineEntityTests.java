package model;

import org.example.model.Discipline;
import org.example.model.Term;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DisciplineEntityTests {
    private Discipline discipline;
    private List<Term> terms;

    @BeforeEach
    public void setUp() {
        terms = new ArrayList<>();
        terms.add(new Term(1, "Spring", "3 months", 1, new ArrayList<>()));
        terms.add(new Term(2, "Fall", "4 months", 2, new ArrayList<>()));

        discipline = new Discipline(1, "Math", 1, terms);
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals(1, discipline.getId());
        assertEquals("Math", discipline.getDiscipline());
        assertEquals(1, discipline.getStatus());
        assertEquals(terms, discipline.getTerms());

        discipline.setId(2);
        discipline.setDiscipline("Science");
        discipline.setStatus(2);
        List<Term> newTerms = new ArrayList<>();
        newTerms.add(new Term(3, "Winter", "5 months", 1, new ArrayList<>()));
        discipline.setTerms(newTerms);

        assertEquals(2, discipline.getId());
        assertEquals("Science", discipline.getDiscipline());
        assertEquals(2, discipline.getStatus());
        assertEquals(newTerms, discipline.getTerms());
    }

    @Test
    public void testEqualsAndHashCode() {
        List<Term> sameTerms = new ArrayList<>();
        sameTerms.add(new Term(1, "Spring", "3 months", 1, new ArrayList<>()));
        sameTerms.add(new Term(2, "Fall", "4 months", 2, new ArrayList<>()));

        Discipline sameDiscipline = new Discipline(1, "Math", 1, sameTerms);
        Discipline differentDiscipline = new Discipline(2, "Science", 2, new ArrayList<>());

        assertEquals(discipline, sameDiscipline);
        assertEquals(discipline.hashCode(), sameDiscipline.hashCode());

        assertNotEquals(discipline, differentDiscipline);
        assertNotEquals(discipline.hashCode(), differentDiscipline.hashCode());
    }

    @Test
    public void testToString() {
        String expected = "Discipline{id=1, discipline='Math', status=1}";
        assertEquals(expected, discipline.toString());
    }
}

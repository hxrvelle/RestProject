package model;

import org.example.model.Discipline;
import org.example.model.Term;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TermEntityTests {
    private Term term;
    private List<Discipline> disciplines;

    @BeforeEach
    public void setUp() {
        disciplines = new ArrayList<>();

        Discipline discipline = new Discipline();
        discipline.setId(1);
        discipline.setDiscipline("Math");

        disciplines.add(discipline);
        disciplines.add(discipline);
        disciplines.add(discipline);

        term = new Term(1, "Term 1", "3 months", 1, disciplines);
    }

    @Test
    public void testGettersAndSetters() {
        Discipline discipline = new Discipline();
        discipline.setId(1);
        discipline.setDiscipline("Math");

        assertEquals(1, term.getId());
        assertEquals("Term 1", term.getTerm());
        assertEquals("3 months", term.getDuration());
        assertEquals(1, term.getStatus());
        assertEquals(disciplines, term.getDisciplines());

        term.setId(2);
        term.setTerm("Fall");
        term.setDuration("4 months");
        term.setStatus(2);
        List<Discipline> newDisciplines = new ArrayList<>();
        newDisciplines.add(discipline);
        term.setDisciplines(newDisciplines);

        assertEquals(2, term.getId());
        assertEquals("Fall", term.getTerm());
        assertEquals("4 months", term.getDuration());
        assertEquals(2, term.getStatus());
        assertEquals(newDisciplines, term.getDisciplines());
    }

    @Test
    public void testEqualsAndHashCode() {
        Discipline discipline = new Discipline();
        discipline.setId(1);
        discipline.setDiscipline("Math");

        List<Discipline> sameDisciplines = new ArrayList<>();
        sameDisciplines.add(discipline);
        sameDisciplines.add(discipline);
        sameDisciplines.add(discipline);

        Term sameTerm = new Term(1, "Term 1", "3 months", 1, sameDisciplines);
        Term differentTerm = new Term(2, "Fall", "4 months", 2, new ArrayList<>());

        assertEquals(term, sameTerm);
        assertEquals(term.hashCode(), sameTerm.hashCode());

        assertNotEquals(term, differentTerm);
        assertNotEquals(term.hashCode(), differentTerm.hashCode());
    }

    @Test
    public void testToString() {
        String expected = "Term{id=1, term='Term 1', duration='3 months', status=1, disciplines=[Discipline{id=1, discipline='Math', status=0}, Discipline{id=1, discipline='Math', status=0}, Discipline{id=1, discipline='Math', status=0}]}";
        assertEquals(expected, term.toString());
    }
}

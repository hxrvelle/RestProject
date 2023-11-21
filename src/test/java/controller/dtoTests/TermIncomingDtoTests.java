package controller.dtoTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.example.controller.dto.DisciplineIncomingDto;
import org.example.controller.dto.TermIncomingDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TermIncomingDtoTests {

    private TermIncomingDto termIncomingDto;
    private List<DisciplineIncomingDto> disciplineIncomingDtos;

    @BeforeEach
    public void setUp() {
        disciplineIncomingDtos = new ArrayList<>();
        DisciplineIncomingDto discipline = new DisciplineIncomingDto();
        discipline.setId(1);
        discipline.setDiscipline("Math");
        disciplineIncomingDtos.add(discipline);
        disciplineIncomingDtos.add(discipline);

        termIncomingDto = new TermIncomingDto("Term 1", "3 months", 1, disciplineIncomingDtos);
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals("Term 1", termIncomingDto.getTerm());
        assertEquals("3 months", termIncomingDto.getDuration());
        assertEquals(1, termIncomingDto.getStatus());
        assertEquals(disciplineIncomingDtos, termIncomingDto.getDisciplines());

        termIncomingDto.setTerm("Term 2");
        termIncomingDto.setDuration("4 months");
        termIncomingDto.setStatus(2);
        List<DisciplineIncomingDto> newDisciplineIncomingDtos = new ArrayList<>();
        DisciplineIncomingDto newDiscipline = new DisciplineIncomingDto();
        newDiscipline.setDiscipline("Science");
        newDisciplineIncomingDtos.add(newDiscipline);
        termIncomingDto.setDisciplines(newDisciplineIncomingDtos);

        assertEquals("Term 2", termIncomingDto.getTerm());
        assertEquals("4 months", termIncomingDto.getDuration());
        assertEquals(2, termIncomingDto.getStatus());
        assertEquals(newDisciplineIncomingDtos, termIncomingDto.getDisciplines());
    }

    @Test
    public void testEqualsAndHashCode() {
        List<DisciplineIncomingDto> sameDisciplineIncomingDtos = new ArrayList<>();
        DisciplineIncomingDto discipline = new DisciplineIncomingDto();
        discipline.setId(1);
        discipline.setDiscipline("Math");
        sameDisciplineIncomingDtos.add(discipline);
        sameDisciplineIncomingDtos.add(discipline);

        TermIncomingDto sameTermIncomingDto = new TermIncomingDto("Term 1", "3 months", 1, sameDisciplineIncomingDtos);
        TermIncomingDto differentTermIncomingDto = new TermIncomingDto("Term 2", "4 months", 2, new ArrayList<>());

        assertEquals(termIncomingDto, sameTermIncomingDto);
        assertEquals(termIncomingDto.hashCode(), sameTermIncomingDto.hashCode());

        assertNotEquals(termIncomingDto, differentTermIncomingDto);
        assertNotEquals(termIncomingDto.hashCode(), differentTermIncomingDto.hashCode());
    }

    @Test
    public void testToString() {
        String expected = "TermIncomingDto{term='Term 1', duration='3 months', status=1, disciplines=[DisciplineIncomingDto{id=1, discipline='Math', status=0, terms=null}, DisciplineIncomingDto{id=1, discipline='Math', status=0, terms=null}]}";
        assertEquals(expected, termIncomingDto.toString());
    }
}

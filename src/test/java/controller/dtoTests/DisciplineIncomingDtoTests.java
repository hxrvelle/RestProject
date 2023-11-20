package controller.dtoTests;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.example.controller.dto.DisciplineIncomingDto;
import org.example.controller.dto.TermIncomingDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DisciplineIncomingDtoTests {

    private DisciplineIncomingDto disciplineIncomingDto;
    private List<TermIncomingDto> termIncomingDtos;

    @BeforeEach
    public void setUp() {
        termIncomingDtos = new ArrayList<>();
        List<DisciplineIncomingDto> disciplines = new ArrayList<>();

        DisciplineIncomingDto discipline = new DisciplineIncomingDto();
        discipline.setId(1);
        discipline.setDiscipline("Math");
        disciplines.add(discipline);
        disciplines.add(discipline);

        TermIncomingDto term = new TermIncomingDto();
        term.setTerm("Term 1");
        term.setDuration("3 months");

        termIncomingDtos.add(term);

        disciplineIncomingDto = new DisciplineIncomingDto(1, "Math", 1, termIncomingDtos);
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals(1, disciplineIncomingDto.getId());
        assertEquals("Math", disciplineIncomingDto.getDiscipline());
        assertEquals(1, disciplineIncomingDto.getStatus());
        assertEquals(termIncomingDtos, disciplineIncomingDto.getTerms());

        disciplineIncomingDto.setId(2);
        disciplineIncomingDto.setDiscipline("Science");
        disciplineIncomingDto.setStatus(2);
        List<TermIncomingDto> newTermIncomingDtos = new ArrayList<>();
        TermIncomingDto newTerm = new TermIncomingDto();
        newTerm.setTerm("Term 2");
        newTerm.setDuration("4 months");
        newTermIncomingDtos.add(newTerm);
        disciplineIncomingDto.setTerms(newTermIncomingDtos);

        assertEquals(2, disciplineIncomingDto.getId());
        assertEquals("Science", disciplineIncomingDto.getDiscipline());
        assertEquals(2, disciplineIncomingDto.getStatus());
        assertEquals(newTermIncomingDtos, disciplineIncomingDto.getTerms());
    }

    @Test
    public void testEqualsAndHashCode() {
        List<TermIncomingDto> sameTermIncomingDtos = new ArrayList<>();
        TermIncomingDto term = new TermIncomingDto();
        term.setTerm("Term 1");
        term.setDuration("3 months");
        sameTermIncomingDtos.add(term);

        DisciplineIncomingDto sameDisciplineIncomingDto = new DisciplineIncomingDto(1, "Math", 1, sameTermIncomingDtos);
        DisciplineIncomingDto differentDisciplineIncomingDto = new DisciplineIncomingDto(2, "Science", 2, new ArrayList<>());

        assertEquals(disciplineIncomingDto, sameDisciplineIncomingDto);
        assertEquals(disciplineIncomingDto.hashCode(), sameDisciplineIncomingDto.hashCode());

        assertNotEquals(disciplineIncomingDto, differentDisciplineIncomingDto);
        assertNotEquals(disciplineIncomingDto.hashCode(), differentDisciplineIncomingDto.hashCode());
    }

    @Test
    public void testToString() {
        String expected = "DisciplineIncomingDto{id=1, discipline='Math', status=1, terms=[TermIncomingDto{term='Term 1', duration='3 months', status=0, disciplines=null}]}";
        assertEquals(expected, disciplineIncomingDto.toString());
    }
}
package controller.dtoTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.example.controller.dto.DisciplineOutgoingDto;
import org.example.controller.dto.TermOutgoingDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DisciplineOutgoingDtoTests {

    private DisciplineOutgoingDto disciplineOutgoingDto;
    private List<TermOutgoingDto> termOutgoingDtos;

    @BeforeEach
    public void setUp() {
        termOutgoingDtos = new ArrayList<>();
        List<DisciplineOutgoingDto> disciplines = new ArrayList<>();

        DisciplineOutgoingDto discipline = new DisciplineOutgoingDto();
        discipline.setId(1);
        discipline.setDiscipline("Math");
        disciplines.add(discipline);
        disciplines.add(discipline);

        TermOutgoingDto term = new TermOutgoingDto();
        term.setTerm("Term 1");
        term.setDuration("3 months");

        termOutgoingDtos.add(term);

        disciplineOutgoingDto = new DisciplineOutgoingDto(1, "Math", 1, termOutgoingDtos);
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals(1, disciplineOutgoingDto.getId());
        assertEquals("Math", disciplineOutgoingDto.getDiscipline());
        assertEquals(1, disciplineOutgoingDto.getStatus());
        assertEquals(termOutgoingDtos, disciplineOutgoingDto.getTerms());

        disciplineOutgoingDto.setId(2);
        disciplineOutgoingDto.setDiscipline("Science");
        disciplineOutgoingDto.setStatus(2);
        List<TermOutgoingDto> newTermOutgoingDtos = new ArrayList<>();
        TermOutgoingDto newTerm = new TermOutgoingDto();
        newTerm.setTerm("Term 2");
        newTerm.setDuration("4 months");
        newTermOutgoingDtos.add(newTerm);
        disciplineOutgoingDto.setTerms(newTermOutgoingDtos);

        assertEquals(2, disciplineOutgoingDto.getId());
        assertEquals("Science", disciplineOutgoingDto.getDiscipline());
        assertEquals(2, disciplineOutgoingDto.getStatus());
        assertEquals(newTermOutgoingDtos, disciplineOutgoingDto.getTerms());
    }

    @Test
    public void testEqualsAndHashCode() {
        List<TermOutgoingDto> sameTermOutgoingDtos = new ArrayList<>();
        TermOutgoingDto term = new TermOutgoingDto();
        term.setTerm("Term 1");
        term.setDuration("3 months");
        sameTermOutgoingDtos.add(term);

        DisciplineOutgoingDto sameDisciplineOutgoingDto = new DisciplineOutgoingDto(1, "Math", 1, sameTermOutgoingDtos);
        DisciplineOutgoingDto differentDisciplineOutgoingDto = new DisciplineOutgoingDto(2, "Science", 2, new ArrayList<>());

        assertEquals(disciplineOutgoingDto, sameDisciplineOutgoingDto);
        assertEquals(disciplineOutgoingDto.hashCode(), sameDisciplineOutgoingDto.hashCode());

        assertNotEquals(disciplineOutgoingDto, differentDisciplineOutgoingDto);
        assertNotEquals(disciplineOutgoingDto.hashCode(), differentDisciplineOutgoingDto.hashCode());
    }

    @Test
    public void testToString() {
        String expected = "DisciplineOutgoingDto{id=1, discipline='Math', status=1, terms=[TermOutgoingDto{id=0, term='Term 1', duration='3 months', status=0, disciplines=null}]}";
        assertEquals(expected, disciplineOutgoingDto.toString());
    }
}
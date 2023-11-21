package controller.dtoTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.example.controller.dto.DisciplineOutgoingDto;
import org.example.controller.dto.TermOutgoingDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TermOutgoingDtoTests {

    private TermOutgoingDto termOutgoingDto;
    private List<DisciplineOutgoingDto> disciplineOutgoingDtos;

    @BeforeEach
    public void setUp() {
        disciplineOutgoingDtos = new ArrayList<>();
        DisciplineOutgoingDto discipline = new DisciplineOutgoingDto();
        discipline.setId(1);
        discipline.setDiscipline("Math");
        disciplineOutgoingDtos.add(discipline);
        disciplineOutgoingDtos.add(discipline);

        termOutgoingDto = new TermOutgoingDto(1, "Term 1", "3 months", 1, disciplineOutgoingDtos);
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals(1, termOutgoingDto.getId());
        assertEquals("Term 1", termOutgoingDto.getTerm());
        assertEquals("3 months", termOutgoingDto.getDuration());
        assertEquals(1, termOutgoingDto.getStatus());
        assertEquals(disciplineOutgoingDtos, termOutgoingDto.getDisciplines());

        termOutgoingDto.setId(2);
        termOutgoingDto.setTerm("Term 2");
        termOutgoingDto.setDuration("4 months");
        termOutgoingDto.setStatus(2);
        List<DisciplineOutgoingDto> newDisciplineOutgoingDtos = new ArrayList<>();
        DisciplineOutgoingDto newDiscipline = new DisciplineOutgoingDto();
        newDiscipline.setDiscipline("Science");
        newDisciplineOutgoingDtos.add(newDiscipline);
        termOutgoingDto.setDisciplines(newDisciplineOutgoingDtos);

        assertEquals(2, termOutgoingDto.getId());
        assertEquals("Term 2", termOutgoingDto.getTerm());
        assertEquals("4 months", termOutgoingDto.getDuration());
        assertEquals(2, termOutgoingDto.getStatus());
        assertEquals(newDisciplineOutgoingDtos, termOutgoingDto.getDisciplines());
    }

    @Test
    public void testEqualsAndHashCode() {
        List<DisciplineOutgoingDto> sameDisciplineOutgoingDtos = new ArrayList<>();
        DisciplineOutgoingDto discipline = new DisciplineOutgoingDto();
        discipline.setId(1);
        discipline.setDiscipline("Math");
        sameDisciplineOutgoingDtos.add(discipline);
        sameDisciplineOutgoingDtos.add(discipline);

        TermOutgoingDto sameTermOutgoingDto = new TermOutgoingDto(1, "Term 1", "3 months", 1, sameDisciplineOutgoingDtos);
        TermOutgoingDto differentTermOutgoingDto = new TermOutgoingDto(2, "Term 2", "4 months", 2, new ArrayList<>());

        assertEquals(termOutgoingDto, sameTermOutgoingDto);
        assertEquals(termOutgoingDto.hashCode(), sameTermOutgoingDto.hashCode());

        assertNotEquals(termOutgoingDto, differentTermOutgoingDto);
        assertNotEquals(termOutgoingDto.hashCode(), differentTermOutgoingDto.hashCode());
    }

    @Test
    public void testToString() {
        String expected = "TermOutgoingDto{id=1, term='Term 1', duration='3 months', status=1, disciplines=[DisciplineOutgoingDto{id=1, discipline='Math', status=0, terms=null}, DisciplineOutgoingDto{id=1, discipline='Math', status=0, terms=null}]}";
        assertEquals(expected, termOutgoingDto.toString());
    }
}
package controller.mapperTests;

import org.example.controller.dto.DisciplineIncomingDto;
import org.example.controller.dto.DisciplineOutgoingDto;
import org.example.controller.mapper.DisciplineDtoMapper;
import org.example.model.Discipline;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DisciplineDtoMapperTests {
    private final DisciplineDtoMapper mapper = DisciplineDtoMapper.INSTANCE;

    @Test
    void testMapToDto() {
        Discipline discipline = new Discipline();
        discipline.setId(1);

        DisciplineOutgoingDto dto = mapper.mapToDto(discipline);

        assertEquals(discipline.getId(), dto.getId());
    }

    @Test
    void testMapToDtoIncoming() {
        Discipline discipline = new Discipline();
        discipline.setId(1);

        DisciplineIncomingDto dto = mapper.mapToDtoIncoming(discipline);

        assertEquals(discipline.getId(), dto.getId());
    }

    @Test
    void testMapToEntity() {
        DisciplineIncomingDto incomingDto = new DisciplineIncomingDto();
        incomingDto.setDiscipline("Math");

        Discipline discipline = mapper.mapToEntity(incomingDto);

        assertEquals(incomingDto.getDiscipline(), discipline.getDiscipline());
    }

    @Test
    void testMapToDtoList() {
        Discipline discipline1 = new Discipline();
        discipline1.setDiscipline("Math");

        Discipline discipline2 = new Discipline();
        discipline2.setDiscipline("Science");

        List<Discipline> disciplineList = Arrays.asList(discipline1, discipline2);

        List<DisciplineOutgoingDto> dtoList = mapper.mapToDtoList(disciplineList);

        assertEquals(disciplineList.size(), dtoList.size());

        for (int i = 0; i < disciplineList.size(); i++) {
            assertEquals(disciplineList.get(i).getDiscipline(), dtoList.get(i).getDiscipline());
        }
    }

    @Test
    void testMapToDtoIncomingList() {
        Discipline discipline1 = new Discipline();
        discipline1.setDiscipline("Math");

        Discipline discipline2 = new Discipline();
        discipline2.setDiscipline("Science");

        List<Discipline> disciplineList = Arrays.asList(discipline1, discipline2);

        List<DisciplineIncomingDto> dtoList = mapper.mapToDtoIncoming(disciplineList);

        assertEquals(disciplineList.size(), dtoList.size());

        for (int i = 0; i < disciplineList.size(); i++) {
            assertEquals(disciplineList.get(i).getDiscipline(), dtoList.get(i).getDiscipline());
        }
    }

    @Test
    void testMapToEntityList() {
        DisciplineIncomingDto dto1 = new DisciplineIncomingDto();
        dto1.setDiscipline("Math");

        DisciplineIncomingDto dto2 = new DisciplineIncomingDto();
        dto2.setDiscipline("Science");

        List<DisciplineIncomingDto> dtos = Arrays.asList(dto1, dto2);

        List<Discipline> disciplineList = mapper.mapToEntityList(dtos);

        assertEquals(dtos.size(), disciplineList.size());

        for (int i = 0; i < dtos.size(); i++) {
            assertEquals(dtos.get(i).getDiscipline(), disciplineList.get(i).getDiscipline());
        }
    }
}

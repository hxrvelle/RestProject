package controller.mapperTests;

import org.example.controller.dto.TermIncomingDto;
import org.example.controller.dto.TermOutgoingDto;
import org.example.controller.mapper.TermDtoMapper;
import org.example.model.Term;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TermDtoMapperTests {
    private final TermDtoMapper mapper = TermDtoMapper.INSTANCE;

    @Test
    void testMapToDto() {
        Term term = new Term();
        term.setId(1);

        TermOutgoingDto dto = mapper.mapToDto(term);

        assertEquals(term.getId(), dto.getId());
    }

    @Test
    void testMapToEntity() {
        TermIncomingDto incomingDto = new TermIncomingDto();
        incomingDto.setTerm("Term 1");

        Term term = mapper.mapToEntity(incomingDto);

        assertEquals(incomingDto.getTerm(), term.getTerm());
    }

    @Test
    void testMapToDtoList() {
        Term term1 = new Term();
        term1.setTerm("Term 1");

        Term term2 = new Term();
        term1.setTerm("Term 2");

        List<Term> termList = Arrays.asList(term1, term2);

        List<TermOutgoingDto> dtoList = mapper.mapToDtoList(termList);

        assertEquals(termList.size(), dtoList.size());

        for (int i = 0; i < termList.size(); i++) {
            assertEquals(termList.get(i).getTerm(), dtoList.get(i).getTerm());
        }
    }

    @Test
    void testMapToEntityList() {
        TermIncomingDto dto1 = new TermIncomingDto();
        dto1.setTerm("Term 1");

        TermIncomingDto dto2 = new TermIncomingDto();
        dto2.setTerm("Term 2");

        List<TermIncomingDto> dtos = Arrays.asList(dto1, dto2);

        List<Term> termList = mapper.mapToEntityList(dtos);

        assertEquals(dtos.size(), termList.size());

        for (int i = 0; i < dtos.size(); i++) {
            assertEquals(dtos.get(i).getTerm(), termList.get(i).getTerm());
        }
    }
}

package org.example.controller.mapper;

import org.example.controller.dto.TermIncomingDto;
import org.example.controller.dto.TermOutgoingDto;
import org.example.model.Term;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TermDtoMapper {
    TermDtoMapper INSTANCE = Mappers.getMapper(TermDtoMapper.class);

    @Mapping(target = "id", source = "id")
    TermOutgoingDto mapToDto(Term entity);
    Term mapToEntity(TermIncomingDto dto);
    List<TermOutgoingDto> mapToDtoList(List<Term> entities);
    List<Term> mapToEntityList(List<TermIncomingDto> dtos);
}

package org.example.controller.mapper;

import org.example.controller.dto.DisciplineIncomingDto;
import org.example.controller.dto.DisciplineOutgoingDto;
import org.example.model.Discipline;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DisciplineDtoMapper {
    DisciplineDtoMapper INSTANCE = Mappers.getMapper(DisciplineDtoMapper.class);

    @Mapping(target = "id", source = "id")
    DisciplineOutgoingDto mapToDto(Discipline entity);
    DisciplineIncomingDto mapToDtoIncoming(Discipline entity);
    Discipline mapToEntity(DisciplineIncomingDto dto);
    List<DisciplineOutgoingDto> mapToDtoList(List<Discipline> entities);
    List<DisciplineIncomingDto> mapToDtoIncoming(List<Discipline> entities);
    List<Discipline> mapToEntityList(List<DisciplineIncomingDto> dtos);
}

package org.example.controller.mapper;

import org.example.controller.dto.PhoneIncomingDto;
import org.example.controller.dto.PhoneOutgoingDto;
import org.example.model.Phone;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PhoneDtoMapper {
    PhoneDtoMapper INSTANCE = Mappers.getMapper(PhoneDtoMapper.class);

    @Mapping(target = "id", source = "id")
    PhoneOutgoingDto mapToDto(Phone entity);
    Phone mapToEntity(PhoneIncomingDto dto);
    List<PhoneOutgoingDto> mapToDtoList(List<Phone> entities);
    List<Phone> mapToEntityList(List<PhoneIncomingDto> dtos);
}

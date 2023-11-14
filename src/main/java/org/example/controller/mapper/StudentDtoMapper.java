package org.example.controller.mapper;

import org.example.controller.dto.StudentIncomingDto;
import org.example.controller.dto.StudentOutgoingDto;
import org.example.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface StudentDtoMapper {
    StudentDtoMapper INSTANCE = Mappers.getMapper(StudentDtoMapper.class);

    @Mapping(target = "id", source = "id")
    StudentOutgoingDto mapToDto(Student entity);
    Student mapToEntity(StudentIncomingDto dto);
    List<StudentOutgoingDto> mapToDtoList(List<Student> entities);
    List<Student> mapToEntityList(List<StudentIncomingDto> dtos);
}

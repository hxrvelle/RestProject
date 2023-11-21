package controller.mapperTests;

import org.example.controller.dto.StudentIncomingDto;
import org.example.controller.dto.StudentOutgoingDto;
import org.example.controller.mapper.StudentDtoMapper;
import org.example.model.Student;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentDtoMapperTests {
    private final StudentDtoMapper mapper = StudentDtoMapper.INSTANCE;

    @Test
    void testMapToDto() {
        Student student = new Student();
        student.setId(1);

        StudentOutgoingDto dto = mapper.mapToDto(student);

        assertEquals(student.getId(), dto.getId());
    }

    @Test
    void testMapToEntity() {
        StudentIncomingDto incomingDto = new StudentIncomingDto();
        incomingDto.setName("John Doe");

        Student student = mapper.mapToEntity(incomingDto);

        assertEquals(incomingDto.getName(), student.getName());
    }

    @Test
    void testMapToDtoList() {
        Student student1 = new Student();
        student1.setId(1);

        Student student2 = new Student();
        student2.setId(2);

        List<Student> studentList = Arrays.asList(student1, student2);

        List<StudentOutgoingDto> dtoList = mapper.mapToDtoList(studentList);

        assertEquals(studentList.size(), dtoList.size());

        for (int i = 0; i < studentList.size(); i++) {
            assertEquals(studentList.get(i).getId(), dtoList.get(i).getId());
        }
    }
}

package controller.dtoTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.sql.Date;
import java.util.ArrayList;

import org.example.controller.dto.PhoneOutgoingDto;
import org.example.controller.dto.StudentOutgoingDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StudentOutgoingDtoTests {

    private StudentOutgoingDto studentOutgoingDto;
    private ArrayList<PhoneOutgoingDto> phoneNumbers;

    @BeforeEach
    public void setUp() {
        phoneNumbers = new ArrayList<>();
        PhoneOutgoingDto phone = new PhoneOutgoingDto();
        phone.setId(1);
        phone.setPhoneNumber("123456789");
        phoneNumbers.add(phone);
        phoneNumbers.add(phone);

        studentOutgoingDto = new StudentOutgoingDto(1, "Doe", "John", "Group A", Date.valueOf("2023-12-12"), 1, phoneNumbers);
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals(1, studentOutgoingDto.getId());
        assertEquals("Doe", studentOutgoingDto.getSurname());
        assertEquals("John", studentOutgoingDto.getName());
        assertEquals("Group A", studentOutgoingDto.getGroup());
        assertEquals(Date.valueOf("2023-12-12"), studentOutgoingDto.getDate());
        assertEquals(1, studentOutgoingDto.getStatus());
        assertEquals(phoneNumbers, studentOutgoingDto.getPhoneNumbers());

        studentOutgoingDto.setId(2);
        studentOutgoingDto.setSurname("Smith");
        studentOutgoingDto.setName("Alice");
        studentOutgoingDto.setGroup("Group B");
        studentOutgoingDto.setDate(Date.valueOf("2024-01-01"));
        studentOutgoingDto.setStatus(2);
        ArrayList<PhoneOutgoingDto> newPhoneNumbers = new ArrayList<>();
        PhoneOutgoingDto newPhone = new PhoneOutgoingDto();
        newPhone.setPhoneNumber("987654321");
        newPhoneNumbers.add(newPhone);
        studentOutgoingDto.setPhoneNumbers(newPhoneNumbers);

        assertEquals(2, studentOutgoingDto.getId());
        assertEquals("Smith", studentOutgoingDto.getSurname());
        assertEquals("Alice", studentOutgoingDto.getName());
        assertEquals("Group B", studentOutgoingDto.getGroup());
        assertEquals(Date.valueOf("2024-01-01"), studentOutgoingDto.getDate());
        assertEquals(2, studentOutgoingDto.getStatus());
        assertEquals(newPhoneNumbers, studentOutgoingDto.getPhoneNumbers());
    }

    @Test
    public void testEqualsAndHashCode() {
        ArrayList<PhoneOutgoingDto> samePhoneNumbers = new ArrayList<>();
        PhoneOutgoingDto phone = new PhoneOutgoingDto();
        phone.setId(1);
        phone.setPhoneNumber("123456789");
        samePhoneNumbers.add(phone);
        samePhoneNumbers.add(phone);

        StudentOutgoingDto sameStudentOutgoingDto = new StudentOutgoingDto(1, "Doe", "John", "Group A", Date.valueOf("2023-12-12"), 1, samePhoneNumbers);
        StudentOutgoingDto differentStudentOutgoingDto = new StudentOutgoingDto(2, "Smith", "Alice", "Group B", Date.valueOf("2024-01-01"), 2, new ArrayList<>());

        assertEquals(studentOutgoingDto, sameStudentOutgoingDto);
        assertEquals(studentOutgoingDto.hashCode(), sameStudentOutgoingDto.hashCode());

        assertNotEquals(studentOutgoingDto, differentStudentOutgoingDto);
        assertNotEquals(studentOutgoingDto.hashCode(), differentStudentOutgoingDto.hashCode());
    }

    @Test
    public void testToString() {
        String expected = "StudentOutgoingDto{id=1, surname='Doe', name='John', group='Group A', date=2023-12-12, status=1, phoneNumbers=[PhoneOutgoingDto{id=1, phoneNumber='123456789', studentId=0}, PhoneOutgoingDto{id=1, phoneNumber='123456789', studentId=0}]}";
        assertEquals(expected, studentOutgoingDto.toString());
    }
}
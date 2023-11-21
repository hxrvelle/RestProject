package controller.dtoTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.sql.Date;
import java.util.ArrayList;

import org.example.controller.dto.PhoneIncomingDto;
import org.example.controller.dto.StudentIncomingDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StudentIncomingDtoTests {

    private StudentIncomingDto studentIncomingDto;
    private ArrayList<PhoneIncomingDto> phoneNumbers;

    @BeforeEach
    public void setUp() {
        phoneNumbers = new ArrayList<>();
        PhoneIncomingDto phone = new PhoneIncomingDto();
        phone.setPhoneNumber("123456789");
        phoneNumbers.add(phone);
        phoneNumbers.add(phone);

        Date date = Date.valueOf("2023-12-12");

        studentIncomingDto = new StudentIncomingDto("Doe", "John", "Group A", date, 1, phoneNumbers);
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals("Doe", studentIncomingDto.getSurname());
        assertEquals("John", studentIncomingDto.getName());
        assertEquals("Group A", studentIncomingDto.getGroup());
        assertEquals(1, studentIncomingDto.getStatus());
        assertEquals(phoneNumbers, studentIncomingDto.getPhoneNumbers());

        studentIncomingDto.setSurname("Smith");
        studentIncomingDto.setName("Jane");
        studentIncomingDto.setGroup("Group B");
        studentIncomingDto.setStatus(2);
        ArrayList<PhoneIncomingDto> newPhoneNumbers = new ArrayList<>();
        PhoneIncomingDto newPhone = new PhoneIncomingDto();
        newPhone.setPhoneNumber("987654321");
        newPhoneNumbers.add(newPhone);
        studentIncomingDto.setPhoneNumbers(newPhoneNumbers);

        assertEquals("Smith", studentIncomingDto.getSurname());
        assertEquals("Jane", studentIncomingDto.getName());
        assertEquals("Group B", studentIncomingDto.getGroup());
        assertEquals(2, studentIncomingDto.getStatus());
        assertEquals(newPhoneNumbers, studentIncomingDto.getPhoneNumbers());
    }

    @Test
    public void testEqualsAndHashCode() {
        ArrayList<PhoneIncomingDto> samePhoneNumbers = new ArrayList<>();
        PhoneIncomingDto phone = new PhoneIncomingDto();
        phone.setPhoneNumber("123456789");
        samePhoneNumbers.add(phone);
        samePhoneNumbers.add(phone);

        Date date = Date.valueOf("2023-12-12");

        StudentIncomingDto sameStudentIncomingDto = new StudentIncomingDto("Doe", "John", "Group A", date, 1, samePhoneNumbers);
        StudentIncomingDto differentStudentIncomingDto = new StudentIncomingDto("Smith", "Jane", "Group B", date, 2, new ArrayList<>());

        assertEquals(studentIncomingDto, sameStudentIncomingDto);
        assertEquals(studentIncomingDto.hashCode(), sameStudentIncomingDto.hashCode());

        assertNotEquals(studentIncomingDto, differentStudentIncomingDto);
        assertNotEquals(studentIncomingDto.hashCode(), differentStudentIncomingDto.hashCode());
    }

    @Test
    public void testToString() {
        String expected = "StudentIncomingDTO{surname='Doe', name='John', group='Group A', date=2023-12-12, status=1, phoneNumbers=[PhoneIncomingDto{studentId=0, phoneNumber='123456789'}, PhoneIncomingDto{studentId=0, phoneNumber='123456789'}]}";
        assertEquals(expected, studentIncomingDto.toString());
    }
}

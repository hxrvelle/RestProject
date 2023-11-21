package controller.dtoTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.example.controller.dto.PhoneIncomingDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PhoneIncomingDtoTests {

    private PhoneIncomingDto phoneIncomingDto;

    @BeforeEach
    public void setUp() {
        phoneIncomingDto = new PhoneIncomingDto(1, "123456789");
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals(1, phoneIncomingDto.getStudentId());
        assertEquals("123456789", phoneIncomingDto.getPhoneNumber());

        phoneIncomingDto.setStudentId(2);
        phoneIncomingDto.setPhoneNumber("987654321");

        assertEquals(2, phoneIncomingDto.getStudentId());
        assertEquals("987654321", phoneIncomingDto.getPhoneNumber());
    }

    @Test
    public void testEqualsAndHashCode() {
        PhoneIncomingDto samePhoneIncomingDto = new PhoneIncomingDto(1, "123456789");
        PhoneIncomingDto differentPhoneIncomingDto = new PhoneIncomingDto(2, "987654321");

        assertEquals(phoneIncomingDto, samePhoneIncomingDto);
        assertEquals(phoneIncomingDto.hashCode(), samePhoneIncomingDto.hashCode());

        assertNotEquals(phoneIncomingDto, differentPhoneIncomingDto);
        assertNotEquals(phoneIncomingDto.hashCode(), differentPhoneIncomingDto.hashCode());
    }

    @Test
    public void testToString() {
        String expected = "PhoneIncomingDto{studentId=1, phoneNumber='123456789'}";
        assertEquals(expected, phoneIncomingDto.toString());
    }
}
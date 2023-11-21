package controller.dtoTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.example.controller.dto.PhoneOutgoingDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PhoneOutgoingDtoTests {

    private PhoneOutgoingDto phoneOutgoingDto;

    @BeforeEach
    public void setUp() {
        phoneOutgoingDto = new PhoneOutgoingDto(1, "123456789", 1);
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals(1, phoneOutgoingDto.getId());
        assertEquals("123456789", phoneOutgoingDto.getPhoneNumber());
        assertEquals(1, phoneOutgoingDto.getStudentId());

        phoneOutgoingDto.setId(2);
        phoneOutgoingDto.setPhoneNumber("987654321");
        phoneOutgoingDto.setStudentId(2);

        assertEquals(2, phoneOutgoingDto.getId());
        assertEquals("987654321", phoneOutgoingDto.getPhoneNumber());
        assertEquals(2, phoneOutgoingDto.getStudentId());
    }

    @Test
    public void testEqualsAndHashCode() {
        PhoneOutgoingDto samePhoneOutgoingDto = new PhoneOutgoingDto(1, "123456789", 1);
        PhoneOutgoingDto differentPhoneOutgoingDto = new PhoneOutgoingDto(2, "987654321", 2);

        assertEquals(phoneOutgoingDto, samePhoneOutgoingDto);
        assertEquals(phoneOutgoingDto.hashCode(), samePhoneOutgoingDto.hashCode());

        assertNotEquals(phoneOutgoingDto, differentPhoneOutgoingDto);
        assertNotEquals(phoneOutgoingDto.hashCode(), differentPhoneOutgoingDto.hashCode());
    }

    @Test
    public void testToString() {
        String expected = "PhoneOutgoingDto{id=1, phoneNumber='123456789', studentId=1}";
        assertEquals(expected, phoneOutgoingDto.toString());
    }
}
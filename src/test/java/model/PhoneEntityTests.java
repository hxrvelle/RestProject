package model;

import org.example.model.Phone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PhoneEntityTests {
    private Phone phone;

    @BeforeEach
    public void setUp() {
        phone = new Phone(1, "123456789", 1);
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals(1, phone.getId());
        assertEquals("123456789", phone.getPhoneNumber());
        assertEquals(1, phone.getStudentId());

        phone.setId(2);
        phone.setPhoneNumber("987654321");
        phone.setStudentId(2);

        assertEquals(2, phone.getId());
        assertEquals("987654321", phone.getPhoneNumber());
        assertEquals(2, phone.getStudentId());
    }

    @Test
    public void testEqualsAndHashCode() {
        Phone samePhone = new Phone(1, "123456789", 1);
        Phone differentPhone = new Phone(2, "987654321", 2);

        assertEquals(phone, samePhone);
        assertEquals(phone.hashCode(), samePhone.hashCode());

        assertNotEquals(phone, differentPhone);
        assertNotEquals(phone.hashCode(), differentPhone.hashCode());
    }

    @Test
    public void testToString() {
        String expected = "Phone{id=1, phoneNumber='123456789', studentId=1}";
        assertEquals(expected, phone.toString());
    }
}

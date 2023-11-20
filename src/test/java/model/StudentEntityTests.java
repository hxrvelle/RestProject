package model;

import org.example.model.Phone;
import org.example.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class StudentEntityTests {
    private Student student;
    private ArrayList<Phone> phoneNumbers;

    @BeforeEach
    public void setUp() {
        phoneNumbers = new ArrayList<>();

        Phone phone = new Phone();
        phone.setPhoneNumber("123456789");

        phoneNumbers.add(phone);
        phoneNumbers.add(phone);
        phoneNumbers.add(phone);

        Date date = Date.valueOf("2023-12-12");

        student = new Student(1, "Doe", "John", "A", date, 1, phoneNumbers);
    }

    @Test
    public void testGettersAndSetters() {
        Date date = Date.valueOf("2023-12-12");
        Phone phone = new Phone();
        phone.setPhoneNumber("123456789");

        assertEquals(1, student.getId());
        assertEquals("Doe", student.getSurname());
        assertEquals("John", student.getName());
        assertEquals("A", student.getGroup());
        assertEquals(date, student.getDate());
        assertEquals(1, student.getStatus());
        assertEquals(phoneNumbers, student.getPhoneNumbers());

        student.setId(2);
        student.setSurname("Smith");
        student.setName("Jane");
        student.setGroup("B");
        student.setDate(date);
        student.setStatus(2);
        ArrayList<Phone> newPhoneNumbers = new ArrayList<>();
        newPhoneNumbers.add(phone);
        student.setPhoneNumbers(newPhoneNumbers);

        assertEquals(2, student.getId());
        assertEquals("Smith", student.getSurname());
        assertEquals("Jane", student.getName());
        assertEquals("B", student.getGroup());
        assertEquals(date, student.getDate());
        assertEquals(2, student.getStatus());
        assertEquals(newPhoneNumbers, student.getPhoneNumbers());
    }

    @Test
    public void testEqualsAndHashCode() {
        Date date = Date.valueOf("2023-12-12");
        Phone phone = new Phone();
        phone.setPhoneNumber("123456789");

        Student sameStudent = new Student(1, "Doe", "John", "A", date, 1, phoneNumbers);
        Student differentStudent = new Student(2, "Smith", "Jane", "B", date, 2, new ArrayList<>());

        assertEquals(student, sameStudent);
        assertEquals(student.hashCode(), sameStudent.hashCode());

        assertNotEquals(student, differentStudent);
        assertNotEquals(student.hashCode(), differentStudent.hashCode());
    }

    @Test
    public void testToString() {
        String expected = "Student{id=1, surname='Doe', name='John', group='A', date=2023-12-12, status=1, phoneNumbers=[Phone{id=0, phoneNumber='123456789', studentId=0}, Phone{id=0, phoneNumber='123456789', studentId=0}, Phone{id=0, phoneNumber='123456789', studentId=0}]}";
        assertEquals(expected, student.toString());
    }
}

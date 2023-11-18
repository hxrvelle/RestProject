package repository;

import com.zaxxer.hikari.HikariConfig;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.example.db.ConnectionManager;
import org.example.model.Phone;
import org.example.repository.impl.PhoneRepoImpl;
import org.example.repository.impl.StudentRepoImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
public class PhoneRepoTests {
    private static HikariConfig config;
    private static PhoneRepoImpl phoneRepo;
    private static StudentRepoImpl studentRepo;

    @Container
    private static final MySQLContainer<?> mysqlContainer = new MySQLContainer<>("mysql:latest");

    @BeforeAll
    public static void setUp() {
        phoneRepo = new PhoneRepoImpl();
        studentRepo = new StudentRepoImpl();

        config = new HikariConfig();

        config.setJdbcUrl(mysqlContainer.getJdbcUrl());
        config.setUsername(mysqlContainer.getUsername());
        config.setPassword(mysqlContainer.getPassword());
        ConnectionManager.setData(config);

        try {
            ScriptRunner scriptRunner = new ScriptRunner(ConnectionManager.connection());
            URL resource = PhoneRepoTests.class.getClassLoader().getResource("tables.sql");
            assert resource != null;
            Reader reader = new BufferedReader(new FileReader(new File(resource.toURI())));
            scriptRunner.runScript(reader);
        } catch (SQLException | IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

        mysqlContainer.start();
    }

    @Test
    public void testGetPhoneById() {
        Phone phone = phoneRepo.getPhoneById(1);
        assertNotNull(phone);
        assertEquals(1, phone.getId());
        assertEquals(1, phone.getStudentId());
        assertEquals("1111111", phone.getPhoneNumber());
    }

    @Test
    public void testGetStudentPhones() {
        List<Phone> studentPhones = phoneRepo.getStudentPhones(1);
        assertNotNull(studentPhones);
        assertEquals(1, studentPhones.size());
        assertEquals("1111111", studentPhones.get(0).getPhoneNumber());
    }

    @Test
    public void testAddStudentPhone() {
        Phone phone = new Phone();
        phone.setStudentId(1);
        phone.setPhoneNumber("9876543210");
        phoneRepo.addStudentPhone(phone);

        int id = 0;

        ArrayList<Phone> phoneNumbers = studentRepo.getStudentById(1).getPhoneNumbers();
        for (int i = 0; i < phoneNumbers.size(); i++) {
            if (phoneNumbers.get(i).getPhoneNumber().equals("9876543210")) {
                id = phoneNumbers.get(i).getId();
            }
        }
        assertNotNull(phoneRepo.getPhoneById(id).getPhoneNumber());
        assertEquals("9876543210", phoneRepo.getPhoneById(id).getPhoneNumber());
    }

    @Test
    public void testUpdateStudentPhone() {
        Phone phone = new Phone();
        phone.setPhoneNumber("999999999");
        int idToUpdate = 1;
        phoneRepo.updateStudentPhone(idToUpdate, phone);

        Phone updatedPhone = phoneRepo.getPhoneById(idToUpdate);
        assertNotNull(updatedPhone);
        assertEquals("999999999", updatedPhone.getPhoneNumber());
    }

    @Test
    public void testDeleteStudentPhone() {
        int idToDelete = 2;
        phoneRepo.deleteStudentPhone(idToDelete);

        Phone deletedPhone = phoneRepo.getPhoneById(idToDelete);
        assertTrue(deletedPhone.getId() == 0);
    }

    @AfterAll
    public static void tearDown() {
        mysqlContainer.stop();
    }
}

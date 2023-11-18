package repository;

import com.zaxxer.hikari.HikariConfig;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.example.db.ConnectionManager;
import org.example.model.Student;
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
import java.util.List;

import static org.junit.Assert.assertEquals;

@Testcontainers
public class StudentRepoTests {
    private static HikariConfig config;
    private static StudentRepoImpl studentRepo;

    @Container
    private static final MySQLContainer<?> mysqlContainer = new MySQLContainer<>("mysql:latest")
            .withDatabaseName("test_db")
            .withUsername("testuser")
            .withPassword("testpassword");

    @BeforeAll
    public static void setUp() {
        studentRepo = new StudentRepoImpl();

        config = new HikariConfig();

        config.setJdbcUrl(mysqlContainer.getJdbcUrl());
        config.setUsername(mysqlContainer.getUsername());
        config.setPassword(mysqlContainer.getPassword());

        try {
            ScriptRunner scriptRunner = new ScriptRunner(ConnectionManager.connection());
            URL resource = StudentRepoTests.class.getClassLoader().getResource("tables.sql");
            assert resource != null;
            Reader reader = new BufferedReader(new FileReader(new File(resource.toURI())));
            scriptRunner.runScript(reader);
        } catch (SQLException | IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

        mysqlContainer.start();
    }

    @Test
    public void testGetAllActiveStudents() {
        List<Student> activeStudents = studentRepo.getAllActiveStudents();
        assertEquals(2, activeStudents.size());
    }

    @Test
    public void testGetStudentById() {
        // Write your test cases here using studentRepo.getStudentById()
        // Ensure the test cases cover different scenarios
    }

    // Other test methods for create, modify, and delete operations

    @AfterAll
    public static void tearDown() {
        // Cleanup resources after all tests
        // Stop the container after all tests are done
        mysqlContainer.stop();
    }
}
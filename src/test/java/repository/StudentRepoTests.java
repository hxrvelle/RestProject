package repository;

import com.zaxxer.hikari.HikariConfig;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.example.db.ConnectionManager;
import org.example.model.Student;
import org.example.repository.impl.StudentRepoImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@Testcontainers
public class StudentRepoTests {
    private static HikariConfig config;
    private static StudentRepoImpl studentRepo;

    @Container
    private static final MySQLContainer<?> mysqlContainer = new MySQLContainer<>("mysql:latest");

    @BeforeAll
    public static void setUp() {
        studentRepo = new StudentRepoImpl();

        config = new HikariConfig();

        config.setJdbcUrl(mysqlContainer.getJdbcUrl());
        config.setUsername(mysqlContainer.getUsername());
        config.setPassword(mysqlContainer.getPassword());
        ConnectionManager.setData(config);

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
        Assertions.assertEquals(2, activeStudents.size());
    }

    @Test
    public void testGetStudentById() {
        Student student = studentRepo.getStudentById(1);
        Assertions.assertEquals(1, student.getId());
    }

    @Test
    public void testCreateStudent() {
        Student student = new Student();
        student.setName("John");
        student.setSurname("Doe");
        student.setGroup("Group A");
        student.setDate(Date.valueOf("2023-11-19"));
        studentRepo.createStudent(student);

        int id = 0;

        List<Student> students = studentRepo.getAllActiveStudents();
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getName().equals("John")) id = students.get(i).getId();
        }

        Student createdStudent = studentRepo.getStudentById(id);
        Assertions.assertNotNull(createdStudent);
        Assertions.assertEquals("John", createdStudent.getName());
        Assertions.assertEquals("Doe", createdStudent.getSurname());
    }

    @Test
    public void testModifyStudent() {
        Student student = new Student();
        student.setName("John");
        student.setSurname("Doe");
        student.setGroup("Group A");
        student.setDate(Date.valueOf("2023-11-19"));
        studentRepo.modifyStudent(1, student);

        Student retrievedStudent = studentRepo.getStudentById(1);
        Assertions.assertNotNull(retrievedStudent);
        Assertions.assertEquals("John", retrievedStudent.getName());
        Assertions.assertEquals("Doe", retrievedStudent.getSurname());
    }

    @Test
    public void testDeleteStudent() {
        int studentStatus = studentRepo.getStudentById(1).getStatus();

        studentRepo.deleteStudent(1);
        int deletedStudentStatus = studentRepo.getStudentById(1).getStatus();
        Assertions.assertNotEquals(deletedStudentStatus, studentStatus);
    }

    @AfterAll
    public static void tearDown() {
        mysqlContainer.stop();
    }
}
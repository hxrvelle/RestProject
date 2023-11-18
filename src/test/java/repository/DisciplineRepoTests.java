package repository;

import com.zaxxer.hikari.HikariConfig;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.example.db.ConnectionManager;
import org.example.model.Discipline;
import org.example.model.Term;
import org.example.repository.impl.DisciplineRepoImpl;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DisciplineRepoTests {
    private static HikariConfig config;
    private static DisciplineRepoImpl disciplineRepo;
    @Container
    private static final MySQLContainer<?> mysqlContainer = new MySQLContainer<>("mysql:latest");

    @BeforeAll
    public static void setUp() {
        disciplineRepo = new DisciplineRepoImpl();

        config = new HikariConfig();

        config.setJdbcUrl(mysqlContainer.getJdbcUrl());
        config.setUsername(mysqlContainer.getUsername());
        config.setPassword(mysqlContainer.getPassword());
        ConnectionManager.setData(config);

        try {
            ScriptRunner scriptRunner = new ScriptRunner(ConnectionManager.connection());
            URL resource = DisciplineRepoTests.class.getClassLoader().getResource("tables.sql");
            assert resource != null;
            Reader reader = new BufferedReader(new FileReader(new File(resource.toURI())));
            scriptRunner.runScript(reader);
        } catch (SQLException | IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

        mysqlContainer.start();
    }

    @Test
    @Order(1)
    public void testGetAllActiveDisciplines() {
        List<Discipline> activeDisciplines = disciplineRepo.getAllActiveDisciplines();
        assertNotNull(activeDisciplines);
        assertEquals(2, activeDisciplines.size());
    }

    @Test
    @Order(2)
    public void testGetDisciplineTerms() {
        List<Term> disciplineTerms = disciplineRepo.getDisciplineTerms(1);
        assertNotNull(disciplineTerms);
        assertEquals(2, disciplineTerms.size());
    }

    @Test
    @Order(3)
    public void testGetDisciplineById() {
        Discipline discipline = disciplineRepo.getDisciplineById(1);
        assertNotNull(discipline);
        assertEquals(1, discipline.getId());
    }

    @Test
    @Order(4)
    public void testCreateDiscipline() {
        Discipline discipline = new Discipline();
        discipline.setDiscipline("Discipline C");
        disciplineRepo.createDiscipline(discipline);

        int id = 0;
        List<Discipline> disciplines = disciplineRepo.getAllActiveDisciplines();
        for (int i = 0; i < disciplines.size(); i++) {
            if (disciplines.get(i).getDiscipline().equals("Discipline C")) id = disciplines.get(i).getId();
        }

        Discipline addedDiscipline = disciplineRepo.getDisciplineById(id);
        assertNotNull(addedDiscipline);
        assertEquals("Discipline C", addedDiscipline.getDiscipline());
    }

    @Test
    @Order(5)
    public void testModifyDiscipline() {
        Discipline discipline = new Discipline();
        discipline.setDiscipline("Modified Discipline");
        disciplineRepo.modifyDiscipline(discipline, 1);

        Discipline updatedDiscipline = disciplineRepo.getDisciplineById(1);
        assertNotNull(updatedDiscipline);
        assertEquals("Modified Discipline", updatedDiscipline.getDiscipline());
    }

    @Test
    @Order(6)
    public void testDeleteDiscipline() {
        disciplineRepo.deleteDiscipline(2);

        Discipline deletedDiscipline = disciplineRepo.getDisciplineById(2);
        assertNotNull(deletedDiscipline);
        assertEquals(0, deletedDiscipline.getStatus());
    }

    @AfterAll
    public static void tearDown() {
        mysqlContainer.stop();
    }
}

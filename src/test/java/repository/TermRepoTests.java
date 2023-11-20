package repository;

import com.zaxxer.hikari.HikariConfig;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.example.db.ConnectionManager;
import org.example.model.Term;
import org.example.model.Discipline;
import org.example.repository.impl.DisciplineRepoImpl;
import org.example.repository.impl.TermRepoImpl;
import org.junit.jupiter.api.*;
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
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TermRepoTests {
    private static HikariConfig config;
    private static TermRepoImpl termRepo;
    private static DisciplineRepoImpl disciplineRepo;

    @Container
    private static final MySQLContainer<?> mysqlContainer = new MySQLContainer<>("mysql:latest");

    @BeforeAll
    public static void setUp() {
        termRepo = new TermRepoImpl();
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
    public void testGetAllActiveTerm() {
        List<Term> activeTerms = termRepo.getAllActiveTerm();
        assertNotNull(activeTerms);
        assertEquals(2, activeTerms.size());
    }

    @Test
    @Order(2)
    public void testGetTermDisciplines() {
        List<Discipline> termDisciplines = termRepo.getTermDisciplines(1);
        assertNotNull(termDisciplines);
        assertEquals(2, termDisciplines.size());
    }

    @Test
    @Order(3)
    public void testGetTermById() {
        Term term = termRepo.getTermById(1);
        assertNotNull(term);
        assertEquals(1, term.getId());
    }

    @Test
    @Order(4)
    public void testCreateTerm() {
        String discipline1Name = disciplineRepo.getDisciplineById(1).getDiscipline();
        String discipline2Name = disciplineRepo.getDisciplineById(2).getDiscipline();

        Term term = new Term();
        term.setDuration("6 months");

        List<Discipline> disciplines = new ArrayList<>();
        disciplines.add(disciplineRepo.getDisciplineById(1));
        disciplines.add(disciplineRepo.getDisciplineById(2));
        term.setDisciplines(disciplines);

        termRepo.createTerm(term);

        int idTerm = 0;
        int id1 = 0;
        int id2 = 0;

        List<Term> terms = termRepo.getAllActiveTerm();
        for (Term value : terms) {
            if (value.getDuration().equals("6 months")) idTerm = value.getId();
        }

        List<Discipline> disciplineList = termRepo.getTermDisciplines(idTerm);
        for (int i = 0; i < disciplineList.size(); i++) {
            if (disciplineList.get(i).getDiscipline().equals(discipline1Name)) id1 = disciplineList.get(i).getId();
            if (disciplineList.get(i).getDiscipline().equals(discipline2Name)) id2 = disciplineList.get(i).getId();
        }

        assertNotNull(term);
        assertEquals(discipline1Name, disciplineRepo.getDisciplineById(id1).getDiscipline());
        assertEquals(discipline2Name, disciplineRepo.getDisciplineById(id2).getDiscipline());
    }

    @Test
    @Order(5)
    public void testModifyTerm() {
        Term term = termRepo.getTermById(1);
        term.setDuration("5 months");

        termRepo.modifyTerm(term, 1);

        assertEquals("5 months", termRepo.getTermById(1).getDuration());
    }

    @Test
    @Order(6)
    public void testDeleteTerm() {
        termRepo.deleteTerm(2);

        Term deletedTerm = termRepo.getTermById(2);
        assertNotNull(deletedTerm);
        assertEquals(0, deletedTerm.getStatus());
    }

    @AfterAll
    public static void tearDown() {
        mysqlContainer.stop();
    }
}

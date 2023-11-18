package org.example.repository.impl;

import org.example.db.ConnectionManager;
import org.example.model.Discipline;
import org.example.model.Term;
import org.example.repository.TermRepo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TermRepoImpl implements TermRepo {
    private final ConnectionManager connectionManager = new ConnectionManager();
    private String query;
    @Override
    public List<Term> getAllActiveTerm() {
        List<Term> terms = new ArrayList<>();
        query = "SELECT term.id, term.term, term.duration, term.status, discipline.id, discipline.discipline, discipline.status FROM term LEFT JOIN term_discipline ON term.id = term_discipline.id_term LEFT JOIN discipline ON discipline.id = term_discipline.id_discipline WHERE term.status = 1;";
        try(
                Connection connection = ConnectionManager.connection();
                Statement statement = connectionManager.statement(connection);
                ResultSet rs = connectionManager.connect(statement, query)
        ) {
            while (rs.next()) {
                int termId = rs.getInt("id");
                boolean termExists = false;

                for (Term term : terms) {
                    if (term.getId() == termId) {
                        termExists = true;

                        Discipline discipline = new Discipline();
                        discipline.setId(rs.getInt("discipline.id"));
                        discipline.setDiscipline(rs.getString("discipline.discipline"));
                        discipline.setStatus(rs.getInt("discipline.status"));
                        term.getDisciplines().add(discipline);
                        break;
                    }
                }

                if (!termExists) {
                    Term term = new Term();
                    term.setId(rs.getInt("id"));
                    term.setTerm(rs.getString("term"));
                    term.setDuration(rs.getString("duration"));
                    term.setStatus(rs.getInt("status"));

                    List<Discipline> disciplines = new ArrayList<>();
                    Discipline discipline = new Discipline();
                    discipline.setId(rs.getInt("discipline.id"));
                    discipline.setDiscipline(rs.getString("discipline.discipline"));
                    discipline.setStatus(rs.getInt("discipline.status"));
                    disciplines.add(discipline);

                    term.setDisciplines(disciplines);
                    terms.add(term);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return terms;
    }

    @Override
    public List<Discipline> getTermDisciplines(int id) {
        List<Discipline> disciplines = new ArrayList<>();
        query = "SELECT discipline.* FROM students.discipline JOIN term_discipline on term_discipline.id_discipline = discipline.id JOIN term ON term.id = term_discipline.id_term WHERE term.id ='" + id + "';";
        try(
                Connection connection = ConnectionManager.connection();
                Statement statement = connectionManager.statement(connection);
                ResultSet rs = connectionManager.connect(statement, query)
        ) {
            while (rs.next()) {
                Discipline discipline = new Discipline();

                discipline.setId(rs.getInt("id"));
                discipline.setDiscipline(rs.getString("discipline"));
                discipline.setStatus(rs.getInt("status"));

                disciplines.add(discipline);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return disciplines;
    }

    @Override
    public Term getTermById(int id) {
        Term term = new Term();
        ArrayList<Discipline> disciplines = new ArrayList<>();

        query = "SELECT term.id, term.term, term.duration, term.status, discipline.id, discipline.discipline, discipline.status FROM term LEFT JOIN term_discipline ON term.id = term_discipline.id_term LEFT JOIN discipline ON discipline.id = term_discipline.id_discipline WHERE term.id ='" + id + "';";

        try(
                Connection connection = ConnectionManager.connection();
                Statement statement = connectionManager.statement(connection);
                ResultSet rs = connectionManager.connect(statement, query)
        ) {
            while (rs.next()){
                term.setId(rs.getInt("id"));
                term.setTerm(rs.getString("term"));
                term.setDuration(rs.getString("duration"));
                term.setStatus(rs.getInt("status"));

                Discipline discipline = new Discipline();
                discipline.setId(rs.getInt("discipline.id"));
                discipline.setDiscipline(rs.getString("discipline.discipline"));
                discipline.setStatus(rs.getInt("discipline.status"));
                disciplines.add(discipline);

                term.setDisciplines(disciplines);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return term;
    }

    @Override
    public void createTerm(Term term) throws SQLException {
        String query1 = "SELECT term FROM term ORDER BY id DESC LIMIT 1;";
        String lastTermName = "";
        ResultSet rs1 = null;
        try(
                Connection connection = ConnectionManager.connection();
                Statement statement = connectionManager.statement(connection);
                ResultSet rs = connectionManager.connect(statement, query1)
        ) {
            while (rs.next()) {
                lastTermName = rs.getString("term");
            }
            String[] partsOfLastName = lastTermName.split(" ");
            int numOfTerm = Integer.parseInt(partsOfLastName[1]);
            numOfTerm++;

            String query2 = "INSERT INTO `term` (`term`, `duration`) VALUES ('Семестр " + numOfTerm + "', '" + term.getDuration() + "');";
            connectionManager.updateConnect(statement, query2);

            String query3 = "SELECT id FROM term ORDER BY id DESC LIMIT 1;";
            rs1 = connectionManager.connect(statement, query3);
            int newTermId = 0;
            while (rs1.next()) {
                newTermId = rs1.getInt("id");
            }

            for (int i = 0; i < term.getDisciplines().size(); i++) {
                int disciplineId = term.getDisciplines().get(i).getId();
                String query4 = "INSERT INTO `term_discipline` (`id_term`, `id_discipline`) VALUES ('" + newTermId + "', '" + disciplineId + "');";
                connectionManager.updateConnect(statement, query4);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            assert rs1 != null;
            rs1.close();
        }
    }

    @Override
    public void modifyTerm(Term term, int termId) throws SQLException {
        query = "UPDATE `term` SET `duration` = '" + term.getDuration() + "' WHERE (`id` ='" + termId + "');";
        ResultSet rs = null;
        try(
                Connection connection = ConnectionManager.connection();
                Statement statement = connectionManager.statement(connection)
        ) {
            connectionManager.updateConnect(statement, query);

            String query1 = "SELECT * FROM term_discipline WHERE (`id_term` = '" + termId + "');";
            ArrayList<String> ids = new ArrayList<>();
            rs = connectionManager.connect(statement, query1);
            while (rs.next()) {
                ids.add(rs.getString("id"));
            }

            for (String id:ids) {
                String query2 = "DELETE FROM `mark` WHERE (`id_term_discipline` = '" + id + "');";
                connectionManager.voidConnect(statement, query2);
            }

            String query3 = "DELETE FROM `term_discipline` WHERE (`id_term` = '" + termId + "');";
            connectionManager.voidConnect(statement, query3);

            for (int i = 0; i < term.getDisciplines().size(); i++) {
                int disciplineId = term.getDisciplines().get(i).getId();
                String query4 = "INSERT INTO `term_discipline` (`id_term`, `id_discipline`) VALUES ('" + termId + "', '" + disciplineId + "');";
                connectionManager.updateConnect(statement, query4);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            assert rs != null;
            rs.close();
        }
    }

    @Override
    public void deleteTerm(int id) {
        query = "UPDATE `term` SET `status` = '0' WHERE (`id` ='" + id + "');";
        try (
                Connection connection = ConnectionManager.connection();
                Statement statement = connectionManager.statement(connection)
        ) {
            connectionManager.voidConnect(statement, query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

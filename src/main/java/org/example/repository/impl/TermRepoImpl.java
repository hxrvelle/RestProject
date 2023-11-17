package org.example.repository.impl;

import org.example.db.ConnectionManagerImpl;
import org.example.model.Discipline;
import org.example.model.Term;
import org.example.repository.TermRepo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TermRepoImpl implements TermRepo {
    private final ConnectionManagerImpl connectionManager = new ConnectionManagerImpl();
    private String query;
    @Override
    public List<Term> getAllActiveTerm() {
        List<Term> terms = new ArrayList<>();
        query = "SELECT term.id, term.term, term.duration, term.status, discipline.id, discipline.discipline, discipline.status FROM term LEFT JOIN term_discipline ON term.id = term_discipline.id_term LEFT JOIN discipline ON discipline.id = term_discipline.id_discipline WHERE term.status = 1;";
        try(
                Connection connection = connectionManager.connection();
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
                Connection connection = connectionManager.connection();
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
                Connection connection = connectionManager.connection();
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
                disciplines.add(discipline);

                term.setDisciplines(disciplines);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return term;
    }

    @Override
    public void createTerm(Term term) {

    }

    @Override
    public void modifyTerm(Term term, int id) {

    }

    @Override
    public void deleteTerm(int id) {

    }
}

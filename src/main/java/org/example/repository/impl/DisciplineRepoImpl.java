package org.example.repository.impl;

import org.example.db.ConnectionManager;
import org.example.model.Discipline;
import org.example.model.Term;
import org.example.repository.DisciplineRepo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DisciplineRepoImpl implements DisciplineRepo {
    private String query;
    @Override
    public List<Discipline> getAllActiveDisciplines() {
        List<Discipline> disciplines = new ArrayList<>();
        query = "SELECT discipline.id, discipline.discipline, discipline.status, term.id, term.term, term.duration, term.status FROM discipline LEFT JOIN term_discipline ON discipline.id = term_discipline.id_discipline LEFT JOIN term ON term.id = term_discipline.id_term WHERE discipline.status = 1;";
        try(
                Connection connection = ConnectionManager.connection();
                Statement statement = connection.createStatement();
                ResultSet rs = ConnectionManager.connect(statement, query)
        ) {
            while (rs.next()) {
                int disciplineId = rs.getInt("id");
                boolean disciplineExists = false;

                for(Discipline discipline : disciplines) {
                    if (discipline.getId() == disciplineId) {
                        disciplineExists = true;

                        Term term = new Term();
                        term.setId(rs.getInt("term.id"));
                        term.setTerm(rs.getString("term.term"));
                        term.setDuration(rs.getString("term.duration"));
                        term.setStatus(rs.getInt("term.status"));
                        discipline.getTerms().add(term);
                        break;
                    }
                }

                if (!disciplineExists) {
                    Discipline discipline = new Discipline();
                    discipline.setId(rs.getInt("discipline.id"));
                    discipline.setDiscipline(rs.getString("discipline"));
                    discipline.setStatus(rs.getInt("discipline.status"));

                    List<Term> terms = new ArrayList<>();
                    Term term = new Term();
                    term.setId(rs.getInt("term.id"));
                    term.setTerm(rs.getString("term.term"));
                    term.setDuration(rs.getString("term.duration"));
                    term.setStatus(rs.getInt("term.status"));
                    terms.add(term);

                    discipline.setTerms(terms);
                    disciplines.add(discipline);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return disciplines;
    }

    @Override
    public List<Term> getDisciplineTerms(int id) {
        List<Term> terms = new ArrayList<>();
        query = "SELECT term.* FROM term JOIN term_discipline on term_discipline.id_term = term.id JOIN discipline ON discipline.id = term_discipline.id_discipline WHERE discipline.id ='" + id + "';";
        try(
                Connection connection = ConnectionManager.connection();
                Statement statement = connection.createStatement();
                ResultSet rs = ConnectionManager.connect(statement, query)
        ) {
            while (rs.next()) {
                Term term = new Term();

                term.setId(rs.getInt("id"));
                term.setTerm(rs.getString("term"));
                term.setDuration(rs.getString("duration"));
                term.setStatus(rs.getInt("status"));

                terms.add(term);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return terms;
    }

    @Override
    public Discipline getDisciplineById(int id) {
        Discipline discipline = new Discipline();
        ArrayList<Term> terms = new ArrayList<>();

        query = "SELECT discipline.id, discipline.discipline, discipline.status, term.id, term.term, term.duration, term.status FROM discipline LEFT JOIN term_discipline ON discipline.id = term_discipline.id_discipline LEFT JOIN term ON term.id = term_discipline.id_term WHERE discipline.id ='" + id + "';";

        try(
                Connection connection = ConnectionManager.connection();
                Statement statement = connection.createStatement();
                ResultSet rs = ConnectionManager.connect(statement, query)
        ) {
            while (rs.next()){
                discipline.setId(rs.getInt("discipline.id"));
                discipline.setDiscipline(rs.getString("discipline.discipline"));
                discipline.setStatus(rs.getInt("discipline.status"));

                Term term = new Term();
                term.setId(rs.getInt("term.id"));
                term.setTerm(rs.getString("term.term"));
                term.setDuration(rs.getString("term.duration"));
                term.setStatus(rs.getInt("term.status"));
                terms.add(term);

                discipline.setTerms(terms);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return discipline;
    }

    @Override
    public void createDiscipline(Discipline discipline) {
        query = "INSERT INTO `discipline` (`discipline`) VALUES ('" + discipline.getDiscipline() + "');";
        try (
                Connection connection = ConnectionManager.connection();
                Statement statement = connection.createStatement()
        ) {
            ConnectionManager.updateConnect(statement, query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modifyDiscipline(Discipline discipline, int id) {
        query = "UPDATE `discipline` SET `discipline` = '" + discipline.getDiscipline() + "' WHERE (`id` = '" + id + "');";
        try (
                Connection connection = ConnectionManager.connection();
                Statement statement = connection.createStatement()
        ) {
            ConnectionManager.updateConnect(statement, query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDiscipline(int id) {
        query = "UPDATE `discipline` SET `status` = '0' WHERE (`id` ='" + id + "');";
        try (
                Connection connection = ConnectionManager.connection();
                Statement statement = connection.createStatement()
        ) {
            ConnectionManager.voidConnect(statement, query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

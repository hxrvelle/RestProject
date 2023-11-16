package org.example.repository.impl;

import org.example.db.ConnectionManagerImpl;
import org.example.model.Discipline;
import org.example.model.Term;
import org.example.repository.DisciplineRepo;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DisciplineRepoImpl implements DisciplineRepo {
    private final ConnectionManagerImpl connectionManager = new ConnectionManagerImpl();
    private String query;
    @Override
    public List<Discipline> getAllActiveDisciplines() {
        List<Discipline> disciplines = new ArrayList<>();

        query = "SELECT discipline.id, discipline.discipline, discipline.status, term.id, term.term, term.duration, term.status FROM discipline JOIN term_discipline ON discipline.id = term_discipline.id_discipline JOIN term ON term.id = term_discipline.id_term WHERE discipline.status = 1;";

        try {
            ResultSet rs = connectionManager.connect(query);
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
        return null;
    }

    @Override
    public Discipline getDisciplineById(int id) {
        Discipline discipline = new Discipline();
        ArrayList<Term> terms = new ArrayList<>();

        query = "SELECT discipline.id, discipline.discipline, discipline.status, term.id, term.term, term.duration, term.status FROM discipline JOIN term_discipline ON discipline.id = term_discipline.id_discipline JOIN term ON term.id = term_discipline.id_term WHERE discipline.id ='" + id + "';";

        try {
            ResultSet rs = connectionManager.connect(query);
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
    public void createDiscipline(String discipline) {

    }

    @Override
    public void modifyDiscipline(String discipline, int id) {

    }

    @Override
    public void deleteDiscipline(int id) {

    }
}

package org.example.repository;

import org.example.model.Discipline;
import org.example.model.Term;

import java.sql.SQLException;
import java.util.List;

public interface TermRepo {
    List<Term> getAllActiveTerm();

    List<Discipline> getTermDisciplines(int id);

    Term getTermById(int id);

    void createTerm(Term term) throws SQLException;

    void modifyTerm(Term term, int id) throws SQLException;

    void deleteTerm(int id);
}

package org.example.repository;

import org.example.model.Discipline;
import org.example.model.Term;

import java.util.List;

public interface TermRepo {
    List<Term> getAllActiveTerm();

    List<Discipline> getTermDisciplines(int id);

    Term getTermById(int id);

    void createTerm(String duration, List<Discipline> disciplines);

    void modifyTerm(String duration, List<Discipline> disciplines, int id);

    void deleteTerm(int id);
}

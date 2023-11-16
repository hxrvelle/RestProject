package org.example.repository;

import org.example.model.Discipline;
import org.example.model.Term;

import java.util.List;

public interface DisciplineRepo {
    List<Discipline> getAllActiveDisciplines();

    List<Term> getDisciplineTerms(int id);

    Discipline getDisciplineById(int id);

    void createDiscipline(String discipline);

    void modifyDiscipline(String discipline, int id);

    void deleteDiscipline(int id);
}

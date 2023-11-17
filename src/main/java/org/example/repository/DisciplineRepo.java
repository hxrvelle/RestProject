package org.example.repository;

import org.example.model.Discipline;
import org.example.model.Term;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface DisciplineRepo {
    List<Discipline> getAllActiveDisciplines();

    List<Term> getDisciplineTerms(int id);

    Discipline getDisciplineById(int id);

    void createDiscipline(Discipline discipline) throws SQLException, IOException, ClassNotFoundException;

    void modifyDiscipline(Discipline discipline, int id) throws SQLException, IOException, ClassNotFoundException;

    void deleteDiscipline(int id) throws SQLException, IOException, ClassNotFoundException;
}

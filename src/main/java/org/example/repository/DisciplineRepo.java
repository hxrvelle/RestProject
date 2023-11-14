package org.example.repository;

import org.example.model.Discipline;

import java.util.List;

public interface DisciplineRepo {
    List<Discipline> getAllActiveDisciplines();

    Discipline getDisciplineById(int id);

    void createDiscipline(String discipline);

    void modifyDiscipline(String discipline, int id);

    void deleteDiscipline(int id);
}

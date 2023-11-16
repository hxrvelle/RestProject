package org.example.repository.impl;

import org.example.model.Discipline;
import org.example.model.Term;
import org.example.repository.TermRepo;

import java.util.List;

public class TermRepoImpl implements TermRepo {
    @Override
    public List<Term> getAllActiveTerm() {
        return null;
    }

    @Override
    public List<Discipline> getTermDisciplines(int id) {
        return null;
    }

    @Override
    public Term getTermById(int id) {
        return null;
    }

    @Override
    public void createTerm(String duration, List<Discipline> disciplines) {

    }

    @Override
    public void modifyTerm(String duration, List<Discipline> disciplines, int id) {

    }

    @Override
    public void deleteTerm(int id) {

    }
}

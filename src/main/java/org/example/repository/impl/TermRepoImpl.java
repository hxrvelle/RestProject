package org.example.repository.impl;

import org.example.model.Term;
import org.example.repository.TermRepo;

import java.util.List;

public class TermRepoImpl implements TermRepo {
    @Override
    public List<Term> getAllActiveTerm() {
        return null;
    }

    @Override
    public Term getTermById(String id) {
        return null;
    }

    @Override
    public void createTerm(String duration, String[] disciplines) {

    }

    @Override
    public void modifyTerm(String duration, String[] disciplines, int termId) {

    }

    @Override
    public void deleteTerm(int termId) {

    }
}

package org.example.repository;

import org.example.model.Term;

import java.util.List;

public interface TermRepo {
    List<Term> getAllActiveTerm();

    Term getTermById(String id);

    void createTerm(String duration, String[] disciplines);

    void modifyTerm(String duration, String[] disciplines, int termId);

    void deleteTerm(int termId);
}

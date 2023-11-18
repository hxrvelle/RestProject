package org.example.service;

import org.example.controller.dto.DisciplineOutgoingDto;
import org.example.controller.dto.TermIncomingDto;
import org.example.controller.dto.TermOutgoingDto;
import org.example.model.Discipline;
import org.example.model.Term;

import java.util.List;

public interface TermService {
    List<TermOutgoingDto> getAllActiveTerm();

    List<DisciplineOutgoingDto> getTermDisciplines(int id);

    TermOutgoingDto getTermById(int id);

    void createTerm(TermIncomingDto termDto);

    void modifyTerm(TermIncomingDto termDto, int id);

    void deleteTerm(int id);

    String getTermsCheck(String[] path);
    String createTermCheck(String disciplines, String duration);
    String modifyDisciplineCheck(String[] path, String discipline);
    String deleteDisciplineCheck(String[] path);
}

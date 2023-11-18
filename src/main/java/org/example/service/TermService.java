package org.example.service;

import org.example.controller.dto.DisciplineOutgoingDto;
import org.example.controller.dto.TermIncomingDto;
import org.example.controller.dto.TermOutgoingDto;
import org.example.model.Discipline;
import org.example.model.Term;

import java.sql.SQLException;
import java.util.List;

public interface TermService {
    List<TermOutgoingDto> getAllActiveTerm();

    List<DisciplineOutgoingDto> getTermDisciplines(int id);

    TermOutgoingDto getTermById(int id);

    void createTerm(TermIncomingDto termDto) throws SQLException;

    void modifyTerm(TermIncomingDto termDto, int id) throws SQLException;

    void deleteTerm(int id);

    String getTermsCheck(String[] path);
    String createTermCheck(String disciplines, String duration) throws SQLException;
    String modifyTermCheck(String[] path, String disciplines, String duration) throws SQLException;
    String deleteTermCheck(String[] path);
}

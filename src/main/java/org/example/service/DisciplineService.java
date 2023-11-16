package org.example.service;

import org.example.controller.dto.DisciplineOutgoingDto;
import org.example.controller.dto.TermOutgoingDto;

import java.util.List;

public interface DisciplineService {
    List<DisciplineOutgoingDto> getAllActiveDisciplines();
    DisciplineOutgoingDto getDisciplineById(int id);
    List<TermOutgoingDto> getDisciplineTerms(int id);
    void createDiscipline(String discipline);
    void modifyDiscipline(String discipline, int id);
    void deleteDiscipline(int id);

    String getDisciplinesCheck(String[] path);
    String getTermsCheck(String[] path);
    String createDisciplineCheck(String discipline);
    String modifyDisciplineCheck(String[] path, String discipline);
    String deleteDisciplineCheck(String[] path);
}

package org.example.service.impl;

import org.example.controller.dto.DisciplineOutgoingDto;
import org.example.controller.dto.TermOutgoingDto;
import org.example.controller.mapper.DisciplineDtoMapper;
import org.example.controller.mapper.TermDtoMapper;
import org.example.model.Discipline;
import org.example.model.Term;
import org.example.repository.impl.DisciplineRepoImpl;
import org.example.service.DisciplineService;

import java.util.ArrayList;
import java.util.List;

public class DisciplineServiceImpl implements DisciplineService {
    private final DisciplineRepoImpl disciplineRepo;

    public DisciplineServiceImpl(DisciplineRepoImpl disciplineRepo) {
        this.disciplineRepo = disciplineRepo;
    }

    @Override
    public List<DisciplineOutgoingDto> getAllActiveDisciplines() {
        List<Discipline> disciplines = disciplineRepo.getAllActiveDisciplines();

        List<DisciplineOutgoingDto> disciplineDtos = new ArrayList<>();

        for (int i = 0; i < disciplines.size(); i++) {
            Discipline discipline = disciplines.get(i);

            List<Term> terms = discipline.getTerms();
            List<TermOutgoingDto> termDtos = TermDtoMapper.INSTANCE.mapToDtoList(terms);

            DisciplineOutgoingDto disciplineDto = DisciplineDtoMapper.INSTANCE.mapToDto(discipline);
            disciplineDto.setTerms(termDtos);

            disciplineDtos.add(disciplineDto);
        }
        return disciplineDtos;
    }

    @Override
    public DisciplineOutgoingDto getDisciplineById(int id) {
        Discipline discipline = disciplineRepo.getDisciplineById(id);

        List<Term> terms = discipline.getTerms();
        List<TermOutgoingDto> termDtos = TermDtoMapper.INSTANCE.mapToDtoList(terms);

        DisciplineOutgoingDto disciplineDto = DisciplineDtoMapper.INSTANCE.mapToDto(discipline);
        disciplineDto.setTerms(termDtos);

        return disciplineDto;
    }

    @Override
    public List<TermOutgoingDto> getDisciplineTerms(int id) {
        return null;
    }

    @Override
    public void createDiscipline(String discipline) {

    }

    @Override
    public void modifyDiscipline(String discipline, int id) {

    }

    @Override
    public void deleteDiscipline(int id) {

    }

    @Override
    public String getDisciplinesCheck(String[] path) {
        String status;
        int id;
        if (path.length > 1) {
            id = Integer.parseInt(path[1]);

            Discipline discipline = disciplineRepo.getDisciplineById(id);
            if (discipline.getId() == 0) status = "0";
            else status = "1";
        } else {
            List<Discipline> disciplines = disciplineRepo.getAllActiveDisciplines();
            if (disciplines.size() == 0) status = "2"; //no discipline
            else status = "3"; //ok
        }
        return status;
    }

    @Override
    public String getTermsCheck(String[] path) {
        return null;
    }

    @Override
    public String createDisciplineCheck(String discipline) {
        return null;
    }

    @Override
    public String modifyDisciplineCheck(String[] path, String discipline) {
        return null;
    }

    @Override
    public String deleteDisciplineCheck(String[] path) {
        return null;
    }
}

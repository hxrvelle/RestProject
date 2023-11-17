package org.example.service.impl;

import org.example.controller.dto.DisciplineOutgoingDto;
import org.example.controller.dto.TermIncomingDto;
import org.example.controller.dto.TermOutgoingDto;
import org.example.controller.mapper.DisciplineDtoMapper;
import org.example.controller.mapper.TermDtoMapper;
import org.example.model.Discipline;
import org.example.model.Term;
import org.example.repository.impl.TermRepoImpl;
import org.example.service.TermService;

import java.util.ArrayList;
import java.util.List;

public class TermServiceImpl implements TermService {
    private final TermRepoImpl termRepo;

    public TermServiceImpl(TermRepoImpl termRepo) {
        this.termRepo = termRepo;
    }

    @Override
    public List<TermOutgoingDto> getAllActiveTerm() {
        List<Term> terms = termRepo.getAllActiveTerm();

        List<TermOutgoingDto> termDtos = new ArrayList<>();

        for (int i = 0; i < terms.size(); i++) {
            Term term = terms.get(i);

            List<Discipline> disciplines = term.getDisciplines();
            List<DisciplineOutgoingDto> disciplineDtos = DisciplineDtoMapper.INSTANCE.mapToDtoList(disciplines);

            TermOutgoingDto termDto = TermDtoMapper.INSTANCE.mapToDto(term);
            termDto.setDisciplines(disciplineDtos);

            termDtos.add(termDto);
        }
        return termDtos;
    }

    @Override
    public List<DisciplineOutgoingDto> getTermDisciplines(int id) {
        List<Discipline> disciplines = termRepo.getTermDisciplines(id);
        return DisciplineDtoMapper.INSTANCE.mapToDtoList(disciplines);
    }

    @Override
    public TermOutgoingDto getTermById(int id) {
        Term term = termRepo.getTermById(id);

        List<Discipline> disciplines = term.getDisciplines();
        List<DisciplineOutgoingDto> disciplineDtos = DisciplineDtoMapper.INSTANCE.mapToDtoList(disciplines);

        TermOutgoingDto termDto = TermDtoMapper.INSTANCE.mapToDto(term);
        termDto.setDisciplines(disciplineDtos);

        return termDto;
    }

    @Override
    public void createTerm(TermIncomingDto termDto) {

    }

    @Override
    public void modifyTerm(TermIncomingDto termDto, int id) {

    }

    @Override
    public void deleteTerm(int id) {

    }

    @Override
    public String getTermsCheck(String[] path) {
        String status;
        int id;
        if (path.length == 2 && path[1].matches("\\d+")) {
            id = Integer.parseInt(path[1]);

            if (termRepo.getTermById(id).getId() == 0) status = "00";
            else status = "0";
        }
        else if (path.length == 2 && (!path[1].matches("\\d+"))) status = "1";
        else if (path.length == 2 && path[1].equals("disciplines")) status = "2";
        else if (path.length > 2 && path[1].matches("\\d+")) {
            id = Integer.parseInt(path[1]);

            if (path[2].equals("disciplines")) {
                if (termRepo.getTermById(id).getId() == 0) status = "3";
                else if (getTermDisciplines(id).size() == 0) status = "4";
                else status = "5";
            }
            else status = "6";
        }
        else if (path.length > 2 && (!path[1].matches("\\d+"))) status = "7";
        else {
            List<Term> terms = termRepo.getAllActiveTerm();
            if (terms.size() == 0) status = "8";
            else status = "9";
        }
        return status;
    }

    @Override
    public String createTermCheck(String term) {
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

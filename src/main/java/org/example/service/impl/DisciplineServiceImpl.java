package org.example.service.impl;

import org.example.controller.dto.DisciplineIncomingDto;
import org.example.controller.dto.DisciplineOutgoingDto;
import org.example.controller.dto.TermIncomingDto;
import org.example.controller.dto.TermOutgoingDto;
import org.example.controller.mapper.DisciplineDtoMapper;
import org.example.controller.mapper.TermDtoMapper;
import org.example.model.Discipline;
import org.example.model.Term;
import org.example.repository.impl.DisciplineRepoImpl;
import org.example.repository.impl.TermRepoImpl;
import org.example.service.DisciplineService;

import java.util.ArrayList;
import java.util.List;

public class DisciplineServiceImpl implements DisciplineService {
    private final DisciplineRepoImpl disciplineRepo;
    private final TermRepoImpl termRepo = new TermRepoImpl();

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
        List<Term> terms = disciplineRepo.getDisciplineTerms(id);
        return TermDtoMapper.INSTANCE.mapToDtoList(terms);
    }

    @Override
    public void createDiscipline(DisciplineIncomingDto disciplineDto) {
        Discipline discipline = DisciplineDtoMapper.INSTANCE.mapToEntity(disciplineDto);
        disciplineRepo.createDiscipline(discipline);
    }

    @Override
    public void modifyDiscipline(DisciplineIncomingDto disciplineDto, int id) {
        Discipline discipline = DisciplineDtoMapper.INSTANCE.mapToEntity(disciplineDto);
        disciplineRepo.modifyDiscipline(discipline, id);
    }

    @Override
    public void deleteDiscipline(int id) {
        disciplineRepo.deleteDiscipline(id);
    }

    @Override
    public String getDisciplinesCheck(String[] path) {
        String status;
        int id;
        if (path.length == 2 && path[1].matches("\\d+")) {
            id = Integer.parseInt(path[1]);

            if (disciplineRepo.getDisciplineById(id).getId() == 0) status = "00";
            else status = "0";
        }
        else if (path.length == 2 && (!path[1].matches("\\d+"))) status = "1";
        else if (path.length == 2 && path[1].equals("terms")) status = "2";
        else if (path.length > 2 && path[1].matches("\\d+")) {
            id = Integer.parseInt(path[1]);

            if (path[2].equals("terms")) {
                if (disciplineRepo.getDisciplineById(id).getId() == 0) status = "3";
                else if (getDisciplineTerms(id).size() == 0) status = "4";
                else status = "5";
            }
            else status = "6";
        }
        else if (path.length > 2 && (!path[1].matches("\\d+"))) status = "7";
        else {
            List<Discipline> disciplines = disciplineRepo.getAllActiveDisciplines();
            if (disciplines.size() == 0) status = "8";
            else status = "9";
        }
        return status;
    }

    @Override
    public String createDisciplineCheck(String disciplineName) {
        String status;

        if (disciplineName != null && (!disciplineName.equals(""))) {
            DisciplineIncomingDto discipline = new DisciplineIncomingDto();
            discipline.setDiscipline(disciplineName);
            createDiscipline(discipline);
            status = "1";
        }
        else status = "0";
        return status;
    }

    @Override
    public String modifyDisciplineCheck(String[] path, String discipline) {
        String status;
        int id;
        if (path.length == 2  && path[1].matches("\\d+")) {
            id = Integer.parseInt(path[1]);
            if (disciplineRepo.getDisciplineById(id).getId() == 0) status = "1";
            else {
                if (discipline != null && (!discipline.equals(""))) {
                    DisciplineIncomingDto disciplineDto = new DisciplineIncomingDto();
                    disciplineDto.setDiscipline(discipline);
                    modifyDiscipline(disciplineDto, id);
                    status = "2";
                } else status = "3";
            }
        } else if (path.length == 2  && (!path[1].matches("\\d+"))) {
            status = "4";
        } else {
            status = "0";
        }

        return status;
    }

    @Override
    public String deleteDisciplineCheck(String[] path) {
        String status;

        if (path.length < 1) status = "0";
        else if (path.length == 2  && path[1].matches("\\d+")) {
            int id = Integer.parseInt(path[1]);
            if (getDisciplineById(id).getId() == 0) status = "1";
            else {
                deleteDiscipline(id);
                status = "2";
            }
        } else {
            status = "3";
        }
        return status;
    }
}

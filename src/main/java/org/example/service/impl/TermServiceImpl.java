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
import org.example.service.TermService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TermServiceImpl implements TermService {
    private final TermRepoImpl termRepo = new TermRepoImpl();
    private final DisciplineRepoImpl disciplineRepo = new DisciplineRepoImpl();

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
        Term term;
        List<Discipline> disciplines = new ArrayList<>();
        for (int i = 0; i < termDto.getDisciplines().size(); i++) {
            disciplines.add(DisciplineDtoMapper.INSTANCE.mapToEntity(termDto.getDisciplines().get(i)));
        }
        term = TermDtoMapper.INSTANCE.mapToEntity(termDto);
        term.getDisciplines().clear();
        term.setDisciplines(disciplines);
        termRepo.createTerm(term);
    }

    @Override
    public void modifyTerm(TermIncomingDto termDto, int id) {
        Term term;
        List<Discipline> disciplines = new ArrayList<>();
        for (int i = 0; i < termDto.getDisciplines().size(); i++) {
            disciplines.add(DisciplineDtoMapper.INSTANCE.mapToEntity(termDto.getDisciplines().get(i)));
        }
        term = TermDtoMapper.INSTANCE.mapToEntity(termDto);
        term.getDisciplines().clear();
        term.setDisciplines(disciplines);
        termRepo.modifyTerm(term, id);
    }

    @Override
    public void deleteTerm(int id) {
        termRepo.deleteTerm(id);
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
    public String createTermCheck(String disciplines, String duration) throws SQLException {
        String status = "";

        if (disciplines == null) status = "0";
        else if (disciplines.length() > 1) {
            String[] disciplineIds = disciplines.split(",");

            boolean validIds = true;
            for (int i = 0; i < disciplineIds.length; i++) {
                if (!disciplineIds[i].matches("\\d+")) validIds = false;
            }

            if (!validIds) status = "2";
            else {
                List<Discipline> disciplinesList = new ArrayList<>();

                for (int j = 0; j < disciplineIds.length; j++) {
                    Discipline discipline = disciplineRepo.getDisciplineById(Integer.parseInt(disciplineIds[j]));
                    if (discipline.getId() != 0) disciplinesList.add(discipline);
                }

                if (disciplinesList.isEmpty()) status = "3";
                else {
                    List<DisciplineIncomingDto> disciplineDtos = DisciplineDtoMapper.INSTANCE.mapToDtoIncoming(disciplinesList);

                    if (duration == null) duration = "N недель";

                    TermIncomingDto termDto = new TermIncomingDto();
                    termDto.setDuration(duration);
                    termDto.setDisciplines(disciplineDtos);
                    createTerm(termDto);
                    status = "1";
                }
            }
        } else status = "0";
        return status;
    }

    @Override
    public String modifyTermCheck(String[] path, String disciplines, String duration) throws SQLException {
        String status;

        int id;
        if (path.length == 2  && path[1].matches("\\d+")) {
            id = Integer.parseInt(path[1]);

            if (termRepo.getTermById(id).getId() == 0) status = "2";
            else if ((disciplines == null || disciplines.equals("")) && (duration == null || duration.equals(""))) status = "3";
            else if (duration == null || duration.equals("")) {
                duration = termRepo.getTermById(id).getDuration();

                status = createTermObj(disciplines, duration, id);
            } else if (disciplines == null || disciplines.equals("")) {

                List<Discipline> disciplineList = termRepo.getTermDisciplines(id);
                List<DisciplineIncomingDto> disciplineDtos = DisciplineDtoMapper.INSTANCE.mapToDtoIncoming(disciplineList);

                TermIncomingDto termDto = new TermIncomingDto();
                termDto.setDuration(duration);
                termDto.setDisciplines(disciplineDtos);
                modifyTerm(termDto, id);
                status = "6";
            } else {
                status = createTermObj(disciplines, duration, id);
            }
        } else if (path.length == 2  && (!path[1].matches("\\d+"))) {
            status = "0";
        } else {
            status = "1";
        }
        return status;
    }

    @Override
    public String deleteTermCheck(String[] path) {
        String status;
        if (path.length < 1) status = "0";
        else if (path.length == 2  && path[1].matches("\\d+")) {
            int id = Integer.parseInt(path[1]);
            if (getTermById(id).getId() == 0) status = "1";
            else {
                deleteTerm(id);
                status = "2";
            }
        } else {
            status = "3";
        }
        return status;
    }

    private String createTermObj(String disciplines, String duration, int id) throws SQLException {
        String status;
        String[] disciplineIds = disciplines.split(",");

        boolean validIds = true;
        for (String disciplineId : disciplineIds) {
            if (!disciplineId.matches("\\d+")) {
                validIds = false;
                break;
            }
        }
        if (!validIds) status = "4";
        else {
            List<Discipline> disciplinesList = new ArrayList<>();

            for (String disciplineId : disciplineIds) {
                Discipline discipline = disciplineRepo.getDisciplineById(Integer.parseInt(disciplineId));
                if (discipline.getId() != 0) disciplinesList.add(discipline);
            }

            if (disciplinesList.isEmpty()) status = "5";
            else {
                List<DisciplineIncomingDto> disciplineDtos = DisciplineDtoMapper.INSTANCE.mapToDtoIncoming(disciplinesList);
                TermIncomingDto termDto = new TermIncomingDto();
                termDto.setDuration(duration);
                termDto.setDisciplines(disciplineDtos);
                modifyTerm(termDto, id);
                status = "6";
            }
        }
        return status;
    }
}

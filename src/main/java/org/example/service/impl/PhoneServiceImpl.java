package org.example.service.impl;

import org.example.controller.dto.PhoneIncomingDto;
import org.example.controller.dto.PhoneOutgoingDto;
import org.example.controller.mapper.PhoneDtoMapper;
import org.example.controller.mapper.StudentDtoMapper;
import org.example.model.Phone;
import org.example.model.Student;
import org.example.repository.impl.PhoneRepoImpl;
import org.example.repository.impl.StudentRepoImpl;
import org.example.service.PhoneService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhoneServiceImpl implements PhoneService {
    private final PhoneRepoImpl phoneRepo;
    private final StudentRepoImpl studentRepo = new StudentRepoImpl();

    public PhoneServiceImpl(PhoneRepoImpl phoneRepo) {
        this.phoneRepo = phoneRepo;
    }

    @Override
    public List<PhoneOutgoingDto> getStudentPhones(int id) {
        List<Phone> studentPhones = phoneRepo.getStudentPhones(id);
        return PhoneDtoMapper.INSTANCE.mapToDtoList(studentPhones);
    }

    @Override
    public void addStudentPhone(PhoneIncomingDto phoneDto) throws SQLException, IOException, ClassNotFoundException {
        Phone phone = PhoneDtoMapper.INSTANCE.mapToEntity(phoneDto);
        phoneRepo.addStudentPhone(phone);
    }

    @Override
    public void deleteStudentPhone(int id) {

    }

    @Override
    public String getStudentPhonesCheck(String[] path) {
        String status;

        int id;
        if (path.length > 1) {
            id = Integer.parseInt(path[1]);

            List<Phone> phones = phoneRepo.getStudentPhones(id);
            if (phones.size() == 0) {
                status = "1";
            } else {
                status = "2";
            }
        } else {
            status = "0";
        }
        return status;
    }

    @Override
    public String addStudentPhoneCheck(String[] path, String phoneNumber) throws SQLException, IOException, ClassNotFoundException {
        String status = "";

        int id;
        if (path.length > 1) {
            id = Integer.parseInt(path[1]);

            if (studentRepo.getStudentById(id).getId() == 0) status = "1"; //no student with this id
            if (phoneNumber == null) status = "2"; //no number provided
            else {
                PhoneIncomingDto phone = new PhoneIncomingDto();
                phone.setPhoneNumber(phoneNumber);
                phone.setStudentId(id);

                addStudentPhone(phone);
                status = "2";
            }
        } else {
            status = "0"; //no id provided
        }

        return status;
    }

    @Override
    public String deleteStudentPhoneCheck(String[] path) {
        return null;
    }
}

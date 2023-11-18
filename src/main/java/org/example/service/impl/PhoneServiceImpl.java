package org.example.service.impl;

import org.example.controller.dto.PhoneIncomingDto;
import org.example.controller.dto.PhoneOutgoingDto;
import org.example.controller.mapper.PhoneDtoMapper;
import org.example.model.Phone;
import org.example.repository.impl.PhoneRepoImpl;
import org.example.repository.impl.StudentRepoImpl;
import org.example.service.PhoneService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PhoneServiceImpl implements PhoneService {
    private final PhoneRepoImpl phoneRepo = new PhoneRepoImpl();
    private final StudentRepoImpl studentRepo = new StudentRepoImpl();

    @Override
    public List<PhoneOutgoingDto> getStudentPhones(int id) {
        List<Phone> studentPhones = phoneRepo.getStudentPhones(id);
        return PhoneDtoMapper.INSTANCE.mapToDtoList(studentPhones);
    }

    @Override
    public void addStudentPhone(PhoneIncomingDto phoneDto) {
        Phone phone = PhoneDtoMapper.INSTANCE.mapToEntity(phoneDto);
        phoneRepo.addStudentPhone(phone);
    }

    @Override
    public void updateStudentPhone(int id, PhoneIncomingDto phoneDto) {
        Phone phone = PhoneDtoMapper.INSTANCE.mapToEntity(phoneDto);
        phoneRepo.updateStudentPhone(id, phone);
    }

    @Override
    public void deleteStudentPhone(int id) {
        phoneRepo.deleteStudentPhone(id);
    }

    @Override
    public String getStudentPhonesCheck(String[] path) {
        String status;

        int id;
        if (path.length > 1 && path[1].matches("\\d+")) {
            id = Integer.parseInt(path[1]);

            if (studentRepo.getStudentById(id).getId() == 0) status = "1";
            else {
                List<Phone> phones = phoneRepo.getStudentPhones(id);
                if (phones.size() == 0) status = "2";
                else status = "3";
            }
        }
        else if (path.length > 1 && (!path[1].matches("\\d+"))) {
            status = "4";
        }
        else {
            status = "0";
        }
        return status;
    }

    @Override
    public String addStudentPhoneCheck(String[] path, String phoneNumber) {
        String status;

        int id;
        if (path.length > 1 && path[1].matches("\\d+")) {
            id = Integer.parseInt(path[1]);

            if (studentRepo.getStudentById(id).getId() == 0) status = "1";
            else if (phoneNumber == null) status = "2";
            else {
                PhoneIncomingDto phone = new PhoneIncomingDto();
                phone.setPhoneNumber(phoneNumber);
                phone.setStudentId(id);

                addStudentPhone(phone);
                status = "3";
            }
        }
        else if (path.length > 1 && (!path[1].matches("\\d+"))) {
            status = "4";
        }
        else {
            status = "0";
        }

        return status;
    }

    @Override
    public String updateStudentPhoneCheck(String[] path, String phoneNumber) {
        String status;
        int id;
        if (path.length > 1 && path[1].matches("\\d+")) {
            id = Integer.parseInt(path[1]);

            if (phoneRepo.getPhoneById(id).getId() == 0) status = "0";
            else if (phoneNumber == null) status = "1";
            else {
                PhoneIncomingDto phone = new PhoneIncomingDto();
                phone.setPhoneNumber(phoneNumber);

                updateStudentPhone(id, phone);
                status = "2";
            }
        }
        else if (path.length > 1 && (!path[1].matches("\\d+"))) {
            status = "4";
        }
        else {
            status = "3";
        }
        return status;
    }

    @Override
    public String deleteStudentPhoneCheck(String[] path) {
        String status;

        int id;
        if (path.length > 1 && path[1].matches("\\d+")) {
            id = Integer.parseInt(path[1]);

            if (phoneRepo.getPhoneById(id).getId() == 0) status = "1";
            else {
                deleteStudentPhone(id);
                status = "2";
            }
        }
        else if (path.length > 1 && (!path[1].matches("\\d+"))) {
            status = "3";
        }
        else {
            status = "0";
        }
        return status;
    }
}

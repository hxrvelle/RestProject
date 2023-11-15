package org.example.service;

import org.example.controller.dto.PhoneIncomingDto;
import org.example.controller.dto.PhoneOutgoingDto;
import org.example.model.Phone;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface PhoneService {
    List<PhoneOutgoingDto> getStudentPhones(int id);
    void addStudentPhone(PhoneIncomingDto phone) throws SQLException, IOException, ClassNotFoundException;
    void deleteStudentPhone(int id);

    String getStudentPhonesCheck(String[] path);
    String addStudentPhoneCheck(String[] path, String phoneNumber) throws SQLException, IOException, ClassNotFoundException;
    String deleteStudentPhoneCheck(String[] path);
}

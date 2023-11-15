package org.example.repository;

import org.example.model.Phone;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface PhoneRepo {
    List<Phone> getStudentPhones(int id);
    void addStudentPhone(Phone phone) throws SQLException, IOException, ClassNotFoundException;
    void deleteStudentPhone(int id);
}

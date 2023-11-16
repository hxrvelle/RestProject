package org.example.repository.impl;

import org.example.db.ConnectionManagerImpl;
import org.example.model.Phone;
import org.example.repository.PhoneRepo;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhoneRepoImpl implements PhoneRepo {
    private final ConnectionManagerImpl connectionManager = new ConnectionManagerImpl();
    private String query;

    @Override
    public Phone getPhoneById(int id) {
        Phone phone = new Phone();

        query = "SELECT * FROM students.phone WHERE id ='" + id + "';";
        try {
            ResultSet rs = connectionManager.connect(query);
            while (rs.next()) {
                phone.setId(rs.getInt("id"));
                phone.setPhoneNumber(rs.getString("phone"));
                phone.setStudentId(rs.getInt("id_student"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return phone;
    }

    @Override
    public List<Phone> getStudentPhones(int id) {
        List<Phone> studentPhones = new ArrayList<>();

        query = "SELECT * FROM students.phone WHERE id_student ='" + id + "';";
        try {
            ResultSet rs = connectionManager.connect(query);
            while (rs.next()) {
                Phone phone = new Phone();

                phone.setId(rs.getInt("id"));
                phone.setPhoneNumber(rs.getString("phone"));
                phone.setStudentId(rs.getInt("id_student"));
                studentPhones.add(phone);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentPhones;
    }

    @Override
    public void addStudentPhone(Phone phone) {
        query = "INSERT INTO `phone` (`id_student`, `phone`) VALUES ('" +
                phone.getStudentId() + "', '" + phone.getPhoneNumber() + "');";
        try {
            connectionManager.updateConnect(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateStudentPhone(int id, String phoneNumber) {
        query = "UPDATE `students`.`phone` SET `phone` = '" + phoneNumber + "' WHERE (`id` ='" + id + "');";
        try {
            connectionManager.updateConnect(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteStudentPhone(int id) {
        query = "DELETE FROM `students`.`phone` WHERE (`id` = '" + id + "');";
        try {
            connectionManager.voidConnect(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
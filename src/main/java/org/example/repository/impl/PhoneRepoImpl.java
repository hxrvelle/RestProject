package org.example.repository.impl;

import org.example.db.ConnectionManager;
import org.example.model.Phone;
import org.example.repository.PhoneRepo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PhoneRepoImpl implements PhoneRepo {
    private String query;

    @Override
    public Phone getPhoneById(int id) {
        Phone phone = new Phone();

        query = "SELECT * FROM phone WHERE id ='" + id + "';";
        try(
                Connection connection = ConnectionManager.connection();
                Statement statement = connection.createStatement();
                ResultSet rs = ConnectionManager.connect(statement, query)
        ) {
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

        query = "SELECT * FROM phone WHERE id_student ='" + id + "';";
        try(
                Connection connection = ConnectionManager.connection();
                Statement statement = connection.createStatement();
                ResultSet rs = ConnectionManager.connect(statement, query)
        ) {
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
        try (
                Connection connection = ConnectionManager.connection();
                Statement statement = connection.createStatement()
        ) {
            ConnectionManager.updateConnect(statement, query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateStudentPhone(int id, Phone phone) {
        query = "UPDATE `phone` SET `phone` = '" + phone.getPhoneNumber() + "' WHERE (`id` ='" + id + "');";
        try (
                Connection connection = ConnectionManager.connection();
                Statement statement = connection.createStatement()
        ) {
            ConnectionManager.updateConnect(statement, query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteStudentPhone(int id) {
        query = "DELETE FROM `phone` WHERE (`id` = '" + id + "');";
        try (
                Connection connection = ConnectionManager.connection();
                Statement statement = connection.createStatement()
        ) {
            ConnectionManager.voidConnect(statement, query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

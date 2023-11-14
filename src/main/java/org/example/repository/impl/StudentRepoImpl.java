package org.example.repository.impl;

import org.example.controller.mapper.StudentDtoMapper;
import org.example.db.ConnectionManagerImpl;
import org.example.controller.dto.StudentIncomingDto;
import org.example.controller.dto.StudentOutgoingDto;
import org.example.model.Student;
import org.example.repository.StudentRepo;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentRepoImpl implements StudentRepo {
    private final ConnectionManagerImpl connectionManager = new ConnectionManagerImpl();
    private String query;

    @Override
    public List<Student> getAllActiveStudents() {
        List<Student> students = new ArrayList<>();

        query = "SELECT * FROM student WHERE status = 1;";

        try {
            ResultSet rs = connectionManager.connect(query);
            while (rs.next()) {
                Student student = new Student();

                student.setId(rs.getInt("id"));
                student.setSurname(rs.getString("surname"));
                student.setName(rs.getString("name"));
                student.setGroup(rs.getString("group"));
                student.setDate(rs.getDate("date"));
                student.setStatus(1);

                students.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public Student getStudentById(int id) {
        Student student = new Student();

        query = "SELECT * FROM student WHERE id ='" + id + "';";

        try {
            ResultSet rs = connectionManager.connect(query);
            while (rs.next()) {
                student.setId(rs.getInt("id"));
                student.setSurname(rs.getString("surname"));
                student.setName(rs.getString("name"));
                student.setGroup(rs.getString("group"));
                student.setDate(rs.getDate("date"));
                student.setStatus(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return student;
    }

    @Override
    public void createStudent(Student student) {
        query = "INSERT INTO `student` (`surname`, `name`, `group`, `date`) VALUES ('" +
                student.getSurname() + "', '" +
                student.getName() + "', '" +
                student.getGroup() + "', '" +
                student.getDate() + "');";
        try {
            connectionManager.updateConnect(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modifyStudent(int id, Student student) {
        query = "UPDATE `students`.`student` SET `surname` ='" +
                student.getSurname() + "', `name` = '" +
                student.getName() + "', `group` ='" +
                student.getGroup() + "', `date` = '" +
                student.getDate() + "' WHERE (`id` ='" + id + "');";
        try {
            connectionManager.updateConnect(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteStudent(int id) {
        query = "UPDATE `student` SET `status` = '0' WHERE (`id` ='" + id + "');";
        try {
            connectionManager.voidConnect(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

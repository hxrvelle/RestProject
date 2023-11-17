package org.example.repository.impl;

import org.example.db.ConnectionManagerImpl;
import org.example.model.Phone;
import org.example.model.Student;
import org.example.repository.StudentRepo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentRepoImpl implements StudentRepo {
    private final ConnectionManagerImpl connectionManager = new ConnectionManagerImpl();
    private String query;

    @Override
    public List<Student> getAllActiveStudents() {
        List<Student> students = new ArrayList<>();
        query = "SELECT * FROM student LEFT JOIN phone ON phone.id_student = student.id WHERE student.status = 1;";
        try(
                Connection connection = connectionManager.connection();
                Statement statement = connectionManager.statement(connection);
                ResultSet rs = connectionManager.connect(statement, query)
        ) {
            while (rs.next()) {
                int studentId = rs.getInt("id");
                boolean studentExists = false;

                for (Student student : students) {
                    if (student.getId() == studentId) {
                        studentExists = true;

                        Phone phone = new Phone();
                        phone.setId(rs.getInt("phone.id"));
                        phone.setPhoneNumber(rs.getString("phone"));
                        student.getPhoneNumbers().add(phone);
                        break;
                    }
                }

                if (!studentExists) {
                    Student student = new Student();
                    student.setId(studentId);
                    student.setSurname(rs.getString("surname"));
                    student.setName(rs.getString("name"));
                    student.setGroup(rs.getString("group"));
                    student.setDate(rs.getDate("date"));
                    student.setStatus(1);

                    ArrayList<Phone> phoneNumbers = new ArrayList<>();
                    Phone phone = new Phone();
                    phone.setId(rs.getInt("phone.id"));
                    phone.setPhoneNumber(rs.getString("phone"));
                    phoneNumbers.add(phone);

                    student.setPhoneNumbers(phoneNumbers);
                    students.add(student);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public Student getStudentById(int id){
        Student student = new Student();
        ArrayList<Phone> phoneNumbers = new ArrayList<>();
        query = "SELECT * FROM student LEFT JOIN phone ON phone.id_student = student.id WHERE student.id ='" + id + "';";
        try(
                Connection connection = connectionManager.connection();
                Statement statement = connectionManager.statement(connection);
                ResultSet rs = connectionManager.connect(statement, query)
        ) {
            while (rs.next()) {
                student.setId(rs.getInt("id"));
                student.setSurname(rs.getString("surname"));
                student.setName(rs.getString("name"));
                student.setGroup(rs.getString("group"));
                student.setDate(rs.getDate("date"));
                student.setStatus(1);

                Phone phone = new Phone();
                phone.setId(rs.getInt("phone.id"));
                phone.setPhoneNumber(rs.getString("phone"));
                phoneNumbers.add(phone);

                student.setPhoneNumbers(phoneNumbers);
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
        try (
                Connection connection = connectionManager.connection();
                Statement statement = connectionManager.statement(connection)
        ) {
            connectionManager.updateConnect(statement, query);
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
        try(
                Connection connection = connectionManager.connection();
                Statement statement = connectionManager.statement(connection);
        ) {
            connectionManager.updateConnect(statement, query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteStudent(int id) {
        query = "UPDATE `student` SET `status` = '0' WHERE (`id` ='" + id + "');";
        try(
                Connection connection = connectionManager.connection();
                Statement statement = connectionManager.statement(connection);
        ) {
            connectionManager.voidConnect(statement, query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

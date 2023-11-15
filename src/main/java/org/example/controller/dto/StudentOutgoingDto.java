package org.example.controller.dto;

import org.example.model.Phone;

import java.sql.Date;
import java.util.ArrayList;

public class StudentOutgoingDto {
    private int id;
    private String surname;
    private String name;
    private String group;
    private Date date;
    private int status;
    private ArrayList<PhoneOutgoingDto> phoneNumbers;

    public StudentOutgoingDto() {
    }

    public StudentOutgoingDto(int id, String surname, String name, String group, Date date, int status, ArrayList<PhoneOutgoingDto> phoneNumbers) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.group = group;
        this.date = date;
        this.status = status;
        this.phoneNumbers = phoneNumbers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ArrayList<PhoneOutgoingDto> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(ArrayList<PhoneOutgoingDto> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    @Override
    public String toString() {
        return "StudentOutgoingDto{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", group='" + group + '\'' +
                ", date=" + date +
                ", status=" + status +
                ", phoneNumbers=" + phoneNumbers +
                '}';
    }
}

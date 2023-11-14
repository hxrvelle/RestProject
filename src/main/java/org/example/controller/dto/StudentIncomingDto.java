package org.example.controller.dto;

import java.sql.Date;

public class StudentIncomingDto {
    private String surname;
    private String name;
    private String group;
    private Date date;
    private int status;

    public StudentIncomingDto() {
    }

    public StudentIncomingDto(String surname, String name, String group, Date date, int status) {
        this.surname = surname;
        this.name = name;
        this.group = group;
        this.date = date;
        this.status = status;
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

    @Override
    public String toString() {
        return "StudentIncomingDTO{" +
                "surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", group='" + group + '\'' +
                ", date=" + date +
                ", status=" + status +
                '}';
    }
}

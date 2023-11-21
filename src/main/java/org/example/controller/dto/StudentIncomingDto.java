package org.example.controller.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Objects;

public class StudentIncomingDto {
    private String surname;
    private String name;
    private String group;
    private Date date;
    private int status;
    private ArrayList<PhoneIncomingDto> phoneNumbers;

    public StudentIncomingDto() {
    }

    public StudentIncomingDto(String surname, String name, String group, Date date, int status, ArrayList<PhoneIncomingDto> phoneNumbers) {
        this.surname = surname;
        this.name = name;
        this.group = group;
        this.date = date;
        this.status = status;
        this.phoneNumbers = phoneNumbers;
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

    public ArrayList<PhoneIncomingDto> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(ArrayList<PhoneIncomingDto> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentIncomingDto that = (StudentIncomingDto) o;

        if (status != that.status) return false;
        if (!Objects.equals(surname, that.surname)) return false;
        if (!Objects.equals(name, that.name)) return false;
        if (!Objects.equals(group, that.group)) return false;
        if (!Objects.equals(date, that.date)) return false;
        return Objects.equals(phoneNumbers, that.phoneNumbers);
    }

    @Override
    public int hashCode() {
        int result = surname != null ? surname.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (group != null ? group.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + status;
        result = 31 * result + (phoneNumbers != null ? phoneNumbers.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "StudentIncomingDTO{" +
                "surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", group='" + group + '\'' +
                ", date=" + date +
                ", status=" + status +
                ", phoneNumbers=" + phoneNumbers +
                '}';
    }
}

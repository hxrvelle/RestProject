package org.example.model;

import java.util.Objects;

public class Phone {
    private int id;
    private String phoneNumber;
    private int studentId;

    public Phone() {
    }

    public Phone(int id, String phoneNumber, int studentId) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.studentId = studentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Phone phone = (Phone) o;

        if (id != phone.id) return false;
        if (studentId != phone.studentId) return false;
        return Objects.equals(phoneNumber, phone.phoneNumber);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + studentId;
        return result;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", studentId=" + studentId +
                '}';
    }
}

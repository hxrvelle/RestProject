package org.example.controller.dto;

import java.util.Objects;

public class PhoneIncomingDto {
    int studentId;
    private String phoneNumber;

    public PhoneIncomingDto() {
    }

    public PhoneIncomingDto(int studentId, String phoneNumber) {
        this.studentId = studentId;
        this.phoneNumber = phoneNumber;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhoneIncomingDto that = (PhoneIncomingDto) o;

        if (studentId != that.studentId) return false;
        return Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        int result = studentId;
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PhoneIncomingDto{" +
                "studentId=" + studentId +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}

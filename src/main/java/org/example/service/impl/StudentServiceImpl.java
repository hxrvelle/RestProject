package org.example.service.impl;

import org.example.controller.dto.PhoneOutgoingDto;
import org.example.controller.dto.StudentIncomingDto;
import org.example.controller.dto.StudentOutgoingDto;
import org.example.controller.mapper.PhoneDtoMapper;
import org.example.controller.mapper.StudentDtoMapper;
import org.example.model.Phone;
import org.example.model.Student;
import org.example.repository.impl.StudentRepoImpl;
import org.example.service.StudentService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class StudentServiceImpl implements StudentService {
    private final StudentRepoImpl studentRepoImpl;

    public StudentServiceImpl(StudentRepoImpl studentRepoImpl) {
        this.studentRepoImpl = studentRepoImpl;
    }

    @Override
    public List<StudentOutgoingDto> getAllActiveStudents() {
        List<Student> students = studentRepoImpl.getAllActiveStudents();

        List<StudentOutgoingDto> studentDtos = new ArrayList<>();

        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);

            ArrayList<Phone> phoneNumbers = student.getPhoneNumbers();
            ArrayList<PhoneOutgoingDto> phoneDtos = (ArrayList<PhoneOutgoingDto>) PhoneDtoMapper.INSTANCE.mapToDtoList(phoneNumbers);

            StudentOutgoingDto studentDto = StudentDtoMapper.INSTANCE.mapToDto(student);
            studentDto.setPhoneNumbers(phoneDtos);

            studentDtos.add(studentDto);
        }

        return studentDtos;
    }

    @Override
    public StudentOutgoingDto getStudentById(int id) {
        Student student = studentRepoImpl.getStudentById(id);

        ArrayList<Phone> phoneNumbers = student.getPhoneNumbers();
        ArrayList<PhoneOutgoingDto> phoneDtos = (ArrayList<PhoneOutgoingDto>) PhoneDtoMapper.INSTANCE.mapToDtoList(phoneNumbers);

        StudentOutgoingDto studentDto = StudentDtoMapper.INSTANCE.mapToDto(student);
        studentDto.setPhoneNumbers(phoneDtos);

        return studentDto;
    }

    @Override
    public void createStudent(StudentIncomingDto studentDto) {
        Student student = StudentDtoMapper.INSTANCE.mapToEntity(studentDto);
        studentRepoImpl.createStudent(student);
    }

    @Override
    public void modifyStudent(int id, StudentIncomingDto studentDto) {
        Student student = StudentDtoMapper.INSTANCE.mapToEntity(studentDto);
        studentRepoImpl.modifyStudent(id, student);
    }

    @Override
    public void deleteStudent(int id) {
        studentRepoImpl.deleteStudent(id);
    }

    @Override
    public String getStudentsCheck(String[] path) {
        String status;

        int id;
        if (path.length > 1) {
            id = Integer.parseInt(path[1]);

            Student student = studentRepoImpl.getStudentById(id);

            if (student.getId() == 0) {
                status = "0";
            } else {
                status = "1";
            }
        } else {
            List<Student> students = studentRepoImpl.getAllActiveStudents();

            if (students.size() == 0) {
                status = "2";
            } else {
                status = "3";
            }
        }

        return status;
    }

    @Override
    public String createStudentCheck(String surname, String name, String group, String date) {
        String status;

        String[] dateParts = date.split("-");
        String year = dateParts[0];
        String month = dateParts[1];
        String day = dateParts[2];

        StudentIncomingDto student = new StudentIncomingDto();

        if (surname == null || name == null || group == null || date == null) {
            status = "0";
        } else if (year.length() != 4 || month.length() < 1 || month.length() > 2 || day.length() < 1 || day.length() > 2) {
            status = "1";
        } else {
            student.setSurname(surname);
            student.setName(name);
            student.setGroup(group);
            student.setDate(Date.valueOf(date));

            createStudent(student);
            status = "2";
        }

        return status;
    }

    @Override
    public String updateStudentCheckId(String[] path) {
        String status = "";
        if (path.length < 1) {
            status = "0";
        }
        return status;
    }

    @Override
    public String updateStudentCheck(String[] path, String surname, String name, String group, String date) {
        String status = "";

        int id = Integer.parseInt(path[1]);
        if (getStudentById(id).getId() == 0) {
            status = "0";
        } else {
            if (surname == null) surname = getStudentById(id).getSurname();
            if (name == null) name = getStudentById(id).getName();
            if (group == null) group = getStudentById(id).getGroup();
            if (date == null) date = String.valueOf(getStudentById(id).getDate());

            StudentIncomingDto student = new StudentIncomingDto();

            student.setSurname(surname);
            student.setName(name);
            student.setGroup(group);
            student.setDate(Date.valueOf(date));

            modifyStudent(id, student);
            status = "1";
        }
        return status;
    }

    @Override
    public String deleteStudentCheck(String[] path) {
        String status;

        if (path.length < 1) status = "0";
        else {
            int id = Integer.parseInt(path[1]);
            if (getStudentById(id).getId() == 0) status = "1";
            else {
                deleteStudent(id);
                status = "2";
            }
        }
        return status;
    }
}

package org.example.controller;

import com.google.gson.Gson;
import org.example.controller.dto.StudentIncomingDto;
import org.example.controller.dto.StudentOutgoingDto;
import org.example.repository.impl.StudentRepoImpl;
import org.example.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet(name = "StudentController", urlPatterns = "/students/*")
public class StudentController extends HttpServlet {
    Gson gson = new Gson();
    private final StudentRepoImpl studentRepo = new StudentRepoImpl();
    private final StudentServiceImpl service = new StudentServiceImpl(studentRepo);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String[] path = req.getPathInfo().split("/");
        int id;
        if (path.length > 1) {
            id = Integer.parseInt(path[1]);

            StudentOutgoingDto student = service.getStudentById(id);

            if (student.getId() == 0) {
                resp.setStatus(400);
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");

                resp.getWriter().write("No student with such ID");
            } else {
                resp.setStatus(200);
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");

                resp.getWriter().write(gson.toJson(student));
            }
        } else {
            List<StudentOutgoingDto> students = service.getAllActiveStudents();

            if (students.size() == 0) {
                resp.setStatus(400);
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");

                resp.getWriter().write("There's no existing students");
            } else {
                resp.setStatus(200);
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");

                resp.getWriter().write(gson.toJson(students));
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String surname = req.getParameter("surname");
        String name = req.getParameter("name");
        String group = req.getParameter("group");
        String date = req.getParameter("date");

        String[] dateParts = date.split("-");
        String year = dateParts[0];
        String month = dateParts[1];
        String day = dateParts[2];

        StudentIncomingDto student = new StudentIncomingDto();

        if (surname == null || name == null || group == null || date == null) {
            resp.setStatus(400);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");

            resp.getWriter().write("One or more parameter is missing. Required parameters: surname, name, group, date");
        } else if (year.length() != 4 || month.length() < 1 || month.length() > 2 || day.length() < 1 || day.length() > 2) {
            resp.setStatus(400);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");

            resp.getWriter().write("Wrong date format. Expected format: yyyy-mm-dd");
        } else {
            student.setSurname(surname);
            student.setName(name);
            student.setGroup(group);
            student.setDate(Date.valueOf(date));
        }

        service.createStudent(student);
    }
}

package org.example.controller;

import com.google.gson.Gson;
import org.example.repository.impl.StudentRepoImpl;
import org.example.service.impl.StudentServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "StudentController", urlPatterns = "/students/*")
public class StudentController extends HttpServlet {
    Gson gson = new Gson();
    private final StudentRepoImpl studentRepo = new StudentRepoImpl();
    private final StudentServiceImpl service = new StudentServiceImpl(studentRepo);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String[] path = req.getPathInfo().split("/");

        String status = service.getStudentsCheck(path);

        if (status.equals("0")) errorResponse(resp, 400, "No student with such ID");
        if (status.equals("1")) {
            successResponse(resp, 200);
            int id = Integer.parseInt(path[1]);
            resp.getWriter().write(gson.toJson(service.getStudentById(id)));
        }
        if (status.equals("2")) errorResponse(resp, 400, "There's no existing students");
        if (status.equals("3")) {
            successResponse(resp, 200);
            resp.getWriter().write(gson.toJson(service.getAllActiveStudents()));
        }
        if (status.equals("4")) errorResponse(resp, 400, "Invalid student ID. Should be a type of number");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String surname = req.getParameter("surname");
        String name = req.getParameter("name");
        String group = req.getParameter("group");
        String date = req.getParameter("date");

        String status = service.createStudentCheck(surname, name, group, date);

        if (status.equals("0")) errorResponse(resp, 400, "One or more parameter is missing. Required parameters: surname, name, group, date");
        if (status.equals("1")) errorResponse(resp, 400, "Wrong date format. Expected format: yyyy-mm-dd");
        if (status.equals("2")) successResponse(resp, 200);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String[] path = req.getPathInfo().split("/");
        String surname = req.getParameter("surname");
        String name = req.getParameter("name");
        String group = req.getParameter("group");
        String date = req.getParameter("date");

        String status = service.updateStudentCheck(path, surname, name, group, date);
        if (status.equals("0")) errorResponse(resp, 400, "No student ID provided");
        if (status.equals("1")) errorResponse(resp, 400, "No student with this ID");
        if (status.equals("2")) successResponse(resp, 200);
        if (status.equals("3")) errorResponse(resp, 400, "No parameters provided, no changes been made");
        if (status.equals("4")) errorResponse(resp, 400, "Invalid student ID. Should be a type of number");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String[] path = req.getPathInfo().split("/");

        String status = service.deleteStudentCheck(path);
        if (status.equals("0")) errorResponse(resp, 400, "No student ID provided");
        if (status.equals("1")) errorResponse(resp, 400, "No student with this ID");
        if (status.equals("2")) successResponse(resp, 200);
        if (status.equals("3")) errorResponse(resp, 400, "Invalid student ID. Should be a type of number");
    }

    private void errorResponse(HttpServletResponse resp, int status, String message) throws IOException {
        resp.setStatus(status);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        resp.getWriter().write(message);
    }

    private void successResponse(HttpServletResponse resp, int status) {
        resp.setStatus(status);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
    }
}

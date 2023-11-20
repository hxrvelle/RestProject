package org.example.controller;

import com.google.gson.Gson;
import org.example.controller.responseHandlers.StudentErrorResponses;
import org.example.controller.responseHandlers.general.SuccessResponse;
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
    private final StudentServiceImpl service = new StudentServiceImpl();
    private final StudentErrorResponses error = new StudentErrorResponses();
    private final SuccessResponse success = new SuccessResponse();

    public StudentController() {
        super();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String[] path = req.getPathInfo().split("/");

        String status = service.getStudentsCheck(path);

        if (status.equals("0")) error.noStudentId(resp);
        if (status.equals("1")) {
            success.successResponse(resp, 200);
            int id = Integer.parseInt(path[1]);
            resp.getWriter().write(gson.toJson(service.getStudentById(id)));
        }
        if (status.equals("2")) error.noStudents(resp);
        if (status.equals("3")) {
            success.successResponse(resp, 200);
            resp.getWriter().write(gson.toJson(service.getAllActiveStudents()));
        }
        if (status.equals("4")) error.invalidStudentId(resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String surname = req.getParameter("surname");
        String name = req.getParameter("name");
        String group = req.getParameter("group");
        String date = req.getParameter("date");

        String status = service.createStudentCheck(surname, name, group, date);

        if (status.equals("0")) error.parametersMissing(resp);
        if (status.equals("1")) error.dateFormat(resp);
        if (status.equals("2")) success.successResponse(resp, 201);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String[] path = req.getPathInfo().split("/");
        String surname = req.getParameter("surname");
        String name = req.getParameter("name");
        String group = req.getParameter("group");
        String date = req.getParameter("date");

        String status = service.updateStudentCheck(path, surname, name, group, date);
        if (status.equals("0")) error.noStudentId(resp);
        if (status.equals("1")) error.studentDoesntExist(resp);
        if (status.equals("2")) success.successResponse(resp, 200);
        if (status.equals("3")) error.noParameters(resp);
        if (status.equals("4")) error.invalidStudentId(resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String[] path = req.getPathInfo().split("/");

        String status = service.deleteStudentCheck(path);
        if (status.equals("0")) error.noStudentId(resp);
        if (status.equals("1")) error.studentDoesntExist(resp);
        if (status.equals("2")) success.successResponse(resp, 200);
        if (status.equals("3")) error.invalidStudentId(resp);
    }
}

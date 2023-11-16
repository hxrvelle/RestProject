package org.example.controller;

import com.google.gson.Gson;
import org.example.model.Discipline;
import org.example.repository.impl.DisciplineRepoImpl;
import org.example.service.impl.DisciplineServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DisciplineController", urlPatterns = "/disciplines/*")
public class DisciplineController extends HttpServlet {
    Gson gson = new Gson();
    private final DisciplineRepoImpl disciplineRepo = new DisciplineRepoImpl();
    private final DisciplineServiceImpl service = new DisciplineServiceImpl(disciplineRepo);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String[] path = req.getPathInfo().split("/");

        String status = service.getDisciplinesCheck(path);
        if (status.equals("0")) errorResponse(resp, 400, "No discipline with this ID");
        if (status.equals("1")) {
            int id = Integer.parseInt(path[1]);
            successResponse(resp, 200);
            resp.getWriter().write(gson.toJson(service.getDisciplineById(id)));
        }
        if (status.equals("2")) errorResponse(resp, 400, "There's no existing disciplines");
        if (status.equals("3")) {
            successResponse(resp, 200);
            resp.getWriter().write(gson.toJson(service.getAllActiveDisciplines()));
        }
        if (status.equals("4")) errorResponse(resp, 400, "No discipline with this ID");
        if (status.equals("5")) errorResponse(resp, 400, "No terms for this discipline");
        if (status.equals("6")) {
            int id = Integer.parseInt(path[1]);
            successResponse(resp, 200);
            resp.getWriter().write(gson.toJson(service.getDisciplineTerms(id)));
        }
        if (status.equals("7")) errorResponse(resp, 400, "No discipline ID provided");
        if (status.equals("8")) errorResponse(resp, 400, "Invalid discipline ID. Should be a type of number");
        if (status.equals("9")) errorResponse(resp, 400, "Invalid request");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String discipline = req.getParameter("discipline");

        String status = service.createDisciplineCheck(discipline);
        if (status.equals("0")) errorResponse(resp, 400, "No discipline name provided");
        if (status.equals("1")) successResponse(resp, 201);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] path = req.getPathInfo().split("/");
        String discipline = req.getParameter("discipline");

        String status = service.modifyDisciplineCheck(path, discipline);
        if (status.equals("0")) errorResponse(resp, 400, "No discipline ID provided");
        if (status.equals("1")) errorResponse(resp, 400, "No discipline with this ID");
        if (status.equals("2")) successResponse(resp, 200);
        if (status.equals("3")) errorResponse(resp, 400, "No discipline name provided");
        if (status.equals("4")) errorResponse(resp, 400, "Invalid discipline ID. Should be a type of number");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] path = req.getPathInfo().split("/");

        String status = service.deleteDisciplineCheck(path);
        if (status.equals("0")) errorResponse(resp, 400, "No discipline ID provided");
        if (status.equals("1")) errorResponse(resp, 400, "No discipline with this ID");
        if (status.equals("2")) successResponse(resp, 200);
        if (status.equals("3")) errorResponse(resp, 400, "Invalid discipline ID. Should be a type of number");
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

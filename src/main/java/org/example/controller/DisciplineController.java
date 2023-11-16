package org.example.controller;

import com.google.gson.Gson;
import org.example.controller.responseHandlers.DisciplineErrorResponses;
import org.example.controller.responseHandlers.general.SuccessResponse;
import org.example.repository.impl.DisciplineRepoImpl;
import org.example.service.impl.DisciplineServiceImpl;

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
    private final SuccessResponse success = new SuccessResponse();
    private final DisciplineErrorResponses error = new DisciplineErrorResponses();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String[] path = req.getPathInfo().split("/");

        String status = service.getDisciplinesCheck(path);
        if (status.equals("00")) error.diesciplineDoesntExist(resp);
        if (status.equals("0")) {
            int id = Integer.parseInt(path[1]);
            success.successResponse(resp, 200);
            resp.getWriter().write(gson.toJson(service.getDisciplineById(id)));
        }
        if (status.equals("1")) error.invalidDisciplineId(resp);
        if (status.equals("2")) error.noDisciplineId(resp);
        if (status.equals("3")) error.diesciplineDoesntExist(resp);
        if (status.equals("4")) error.noTerms(resp);
        if (status.equals("5")) {
            int id = Integer.parseInt(path[1]);
            success.successResponse(resp, 200);
            resp.getWriter().write(gson.toJson(service.getDisciplineTerms(id)));
        }
        if (status.equals("6")) error.invalidRequest(resp);
        if (status.equals("7")) error.invalidDisciplineId(resp);
        if (status.equals("8")) error.noDisciplines(resp);
        if (status.equals("9")) {
            success.successResponse(resp, 200);
            resp.getWriter().write(gson.toJson(service.getAllActiveDisciplines()));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String discipline = req.getParameter("discipline");

        String status = service.createDisciplineCheck(discipline);
        if (status.equals("0")) error.noDisciplineName(resp);
        if (status.equals("1")) success.successResponse(resp, 201);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String[] path = req.getPathInfo().split("/");
        String discipline = req.getParameter("discipline");

        String status = service.modifyDisciplineCheck(path, discipline);
        if (status.equals("0")) error.noDisciplineId(resp);
        if (status.equals("1")) error.diesciplineDoesntExist(resp);
        if (status.equals("2")) success.successResponse(resp, 200);
        if (status.equals("3")) error.noDisciplineName(resp);
        if (status.equals("4")) error.invalidDisciplineId(resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String[] path = req.getPathInfo().split("/");

        String status = service.deleteDisciplineCheck(path);
        if (status.equals("0")) error.noDisciplineId(resp);
        if (status.equals("1")) error.diesciplineDoesntExist(resp);
        if (status.equals("2")) success.successResponse(resp, 200);
        if (status.equals("3")) error.invalidDisciplineId(resp);
    }
}

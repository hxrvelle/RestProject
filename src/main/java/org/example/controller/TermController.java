package org.example.controller;

import com.google.gson.Gson;
import org.example.controller.responseHandlers.TermErrorResponses;
import org.example.controller.responseHandlers.general.SuccessResponse;
import org.example.service.impl.TermServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "TermController", urlPatterns = "/terms/*")
public class TermController extends HttpServlet {
    Gson gson = new Gson();
    private final TermServiceImpl service = new TermServiceImpl();
    private final SuccessResponse success = new SuccessResponse();
    private final TermErrorResponses error = new TermErrorResponses();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String[] path = req.getPathInfo().split("/");

        String status = service.getTermsCheck(path);
        if (status.equals("00")) error.termDoesntExist(resp);
        if (status.equals("0")) {
            int id = Integer.parseInt(path[1]);
            success.successResponse(resp, 200);
            resp.getWriter().write(gson.toJson(service.getTermById(id)));
        }
        if (status.equals("1")) error.invalidTermId(resp);
        if (status.equals("2")) error.noTermId(resp);
        if (status.equals("3")) error.termDoesntExist(resp);
        if (status.equals("4")) error.noDisciplines(resp);
        if (status.equals("5")) {
            int id = Integer.parseInt(path[1]);
            success.successResponse(resp, 200);
            resp.getWriter().write(gson.toJson(service.getTermDisciplines(id)));
        }
        if (status.equals("6")) error.invalidRequest(resp);
        if (status.equals("7")) error.invalidTermId(resp);
        if (status.equals("8")) error.noTerms(resp);
        if (status.equals("9")) {
            success.successResponse(resp, 200);
            resp.getWriter().write(gson.toJson(service.getAllActiveTerm()));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String disciplines = req.getParameter("disciplines");
        String duration = req.getParameter("duration");

        String status = "";
        try {
            status = service.createTermCheck(disciplines, duration);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (status.equals("0")) error.noDisciplinesProvided(resp);
        if (status.equals("1")) success.successResponse(resp, 201);
        if (status.equals("2")) error.invalidDisciplineId(resp);
        if (status.equals("3")) error.disciplineDoesntExist(resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String[] path = req.getPathInfo().split("/");
        String disciplines = req.getParameter("disciplines");
        String duration = req.getParameter("duration");

        String status = "";
        try {
            status = service.modifyTermCheck(path, disciplines, duration);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (status.equals("0")) error.invalidTermId(resp);
        if (status.equals("1")) error.noTermId(resp);
        if (status.equals("2")) error.termDoesntExist(resp);
        if (status.equals("3")) error.noChanges(resp);
        if (status.equals("4")) error.invalidDisciplineId(resp);
        if (status.equals("5")) error.disciplineDoesntExist(resp);
        if (status.equals("6")) success.successResponse(resp, 200);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String[] path = req.getPathInfo().split("/");

        String status = service.deleteTermCheck(path);
        if (status.equals("0")) error.noTermId(resp);
        if (status.equals("1")) error.termDoesntExist(resp);
        if (status.equals("2")) success.successResponse(resp, 200);
        if (status.equals("3")) error.invalidTermId(resp);
    }
}

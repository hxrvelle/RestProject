package org.example.controller;

import com.google.gson.Gson;
import org.example.controller.responseHandlers.TermErrorResponses;
import org.example.controller.responseHandlers.general.SuccessResponse;
import org.example.repository.impl.TermRepoImpl;
import org.example.service.impl.TermServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

        String status = service.createTermCheck(disciplines, duration);

        if (status.equals("0")) error.noDisciplines(resp);
        if (status.equals("1")) success.successResponse(resp, 201);
    }
}

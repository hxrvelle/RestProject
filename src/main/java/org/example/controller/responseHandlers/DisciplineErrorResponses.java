package org.example.controller.responseHandlers;

import org.example.controller.responseHandlers.general.ErrorResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DisciplineErrorResponses {
    ErrorResponse e = new ErrorResponse();

    public void diesciplineDoesntExist(HttpServletResponse resp) throws IOException {
        e.errorResponse(resp);
        resp.getWriter().write("Discipline with this ID doesn't exist");
    }

    public void noDisciplines(HttpServletResponse resp) throws IOException {
        e.errorResponse(resp);
        resp.getWriter().write("There's no existing disciplines");
    }

    public void noDisciplineId(HttpServletResponse resp) throws IOException {
        e.errorResponse(resp);
        resp.getWriter().write("No discipline ID provided");
    }

    public void noTerms(HttpServletResponse resp) throws IOException {
        e.errorResponse(resp);
        resp.getWriter().write("No terms for this discipline");
    }

    public void noDisciplineName(HttpServletResponse resp) throws IOException {
        e.errorResponse(resp);
        resp.getWriter().write("No discipline name provided");
    }

    public void invalidDisciplineId(HttpServletResponse resp) throws IOException {
        e.errorResponse(resp);
        resp.getWriter().write("Invalid discipline ID. Should be a type of number");
    }

    public void invalidRequest(HttpServletResponse resp) throws IOException {
        e.errorResponse(resp);
        resp.getWriter().write("Invalid request");
    }
}

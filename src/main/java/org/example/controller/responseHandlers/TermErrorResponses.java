package org.example.controller.responseHandlers;

import org.example.controller.responseHandlers.general.ErrorResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TermErrorResponses {
    ErrorResponse e = new ErrorResponse();

    public void termDoesntExist(HttpServletResponse resp) throws IOException {
        e.errorResponse(resp);
        resp.getWriter().write("Term with this ID doesn't exist");
    }

    public void noTerms(HttpServletResponse resp) throws IOException {
        e.errorResponse(resp);
        resp.getWriter().write("There's no existing terms");
    }

    public void noTermId(HttpServletResponse resp) throws IOException {
        e.errorResponse(resp);
        resp.getWriter().write("No term ID provided");
    }

    public void noDisciplines(HttpServletResponse resp) throws IOException {
        e.errorResponse(resp);
        resp.getWriter().write("No disciplines for this term");
    }

    public void noDisciplinesProvided(HttpServletResponse resp) throws IOException {
        e.errorResponse(resp);
        resp.getWriter().write("No disciplines provided");
    }

    public void invalidTermId(HttpServletResponse resp) throws IOException {
        e.errorResponse(resp);
        resp.getWriter().write("Invalid term ID. Should be a type of number");
    }

    public void invalidDisciplineId(HttpServletResponse resp) throws IOException {
        e.errorResponse(resp);
        resp.getWriter().write("Invalid discipline ID(s). Should be a type of number");
    }

    public void invalidRequest(HttpServletResponse resp) throws IOException {
        e.errorResponse(resp);
        resp.getWriter().write("Invalid request");
    }
}

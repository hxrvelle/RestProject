package org.example.controller.responseHandlers;

import org.example.controller.responseHandlers.general.ErrorResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StudentErrorResponses {
    ErrorResponse e = new ErrorResponse();

    public void noStudentId(HttpServletResponse resp) throws IOException {
        e.errorResponse(resp);
        resp.getWriter().write("No student ID provided");
    }

    public void studentDoesntExist(HttpServletResponse resp) throws IOException {
        e.errorResponse(resp);
        resp.getWriter().write("No student ID provided");
    }

    public void noStudents(HttpServletResponse resp) throws IOException {
        e.errorResponse(resp);
        resp.getWriter().write("There's no existing students");
    }

    public void invalidStudentId(HttpServletResponse resp) throws IOException {
        e.errorResponse(resp);
        resp.getWriter().write("Invalid student ID. Should be a type of number");
    }

    public void noParameters(HttpServletResponse resp) throws IOException {
        e.errorResponse(resp);
        resp.getWriter().write("No parameters provided, no changes been made");
    }

    public void parametersMissing(HttpServletResponse resp) throws IOException {
        e.errorResponse(resp);
        resp.getWriter().write("One or more parameter is missing. Required parameters: surname, name, group, date");
    }

    public void dateFormat(HttpServletResponse resp) throws IOException {
        e.errorResponse(resp);
        resp.getWriter().write("Wrong date format. Expected format: yyyy-mm-dd");
    }
}

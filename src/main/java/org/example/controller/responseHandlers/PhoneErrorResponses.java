package org.example.controller.responseHandlers;

import org.example.controller.responseHandlers.general.ErrorResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PhoneErrorResponses {
    ErrorResponse e = new ErrorResponse();

    public void noPhoneNumbers(HttpServletResponse resp) throws IOException {
        e.errorResponse(resp);
        resp.getWriter().write("No phone numbers for this student");
    }

    public void noPhone(HttpServletResponse resp) throws IOException {
        e.errorResponse(resp);
        resp.getWriter().write("No phone number provided");
    }

    public void noPhoneId(HttpServletResponse resp) throws IOException {
        e.errorResponse(resp);
        resp.getWriter().write("No phone number ID provided");
    }

    public void phoneDoesntExist(HttpServletResponse resp) throws IOException {
        e.errorResponse(resp);
        resp.getWriter().write("Phone number with this ID doesn't exist");
    }

    public void invalidPhoneId(HttpServletResponse resp) throws IOException {
        e.errorResponse(resp);
        resp.getWriter().write("Invalid phone ID. Should be a type of number");
    }
}

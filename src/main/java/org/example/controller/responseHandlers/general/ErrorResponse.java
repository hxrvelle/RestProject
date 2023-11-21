package org.example.controller.responseHandlers.general;

import javax.servlet.http.HttpServletResponse;

public class ErrorResponse {

    public void errorResponse(HttpServletResponse resp) {
        resp.setStatus(400);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
    }
}

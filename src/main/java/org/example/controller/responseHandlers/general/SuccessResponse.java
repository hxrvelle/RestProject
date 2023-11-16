package org.example.controller.responseHandlers.general;

import javax.servlet.http.HttpServletResponse;

public class SuccessResponse {
    public void successResponse(HttpServletResponse resp, int status) {
        resp.setStatus(status);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
    }
}

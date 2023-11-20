package org.example.controller;

import com.google.gson.Gson;
import org.example.controller.responseHandlers.PhoneErrorResponses;
import org.example.controller.responseHandlers.StudentErrorResponses;
import org.example.controller.responseHandlers.general.SuccessResponse;
import org.example.repository.impl.PhoneRepoImpl;
import org.example.service.impl.PhoneServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "PhoneController", urlPatterns = "/phones/*")
public class PhoneController extends HttpServlet {
    Gson gson = new Gson();
    private PhoneServiceImpl service = new PhoneServiceImpl();
    private SuccessResponse success = new SuccessResponse();
    private StudentErrorResponses studentError = new StudentErrorResponses();
    private PhoneErrorResponses error = new PhoneErrorResponses();
    public PhoneController() {
        super();
    }
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String[] path = req.getPathInfo().split("/");

        String status = service.getStudentPhonesCheck(path);
        if (status.equals("0")) studentError.noStudentId(resp);
        if (status.equals("1")) studentError.studentDoesntExist(resp);
        if (status.equals("2")) error.noPhoneNumbers(resp);
        if (status.equals("3")) {
            success.successResponse(resp, 200);
            int id = Integer.parseInt(path[1]);
            resp.getWriter().write(gson.toJson(service.getStudentPhones(id)));
        }
        if (status.equals("4")) studentError.invalidStudentId(resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String[] path = req.getPathInfo().split("/");
        String phoneNumber = req.getParameter("number");

        String status;
        status = service.addStudentPhoneCheck(path, phoneNumber);
        if (status.equals("0")) studentError.noStudentId(resp);
        if (status.equals("1")) studentError.studentDoesntExist(resp);
        if (status.equals("2")) error.noPhone(resp);
        if (status.equals("3")) success.successResponse(resp, 201);
        if (status.equals("4")) studentError.invalidStudentId(resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String[] path = req.getPathInfo().split("/");
        String phoneNumber = req.getParameter("number");

        String status = service.updateStudentPhoneCheck(path, phoneNumber);
        if (status.equals("0")) error.phoneDoesntExist(resp);
        if (status.equals("1")) error.noPhone(resp);
        if (status.equals("2")) success.successResponse(resp, 200);
        if (status.equals("3")) error.noPhoneId(resp);
        if (status.equals("4")) error.invalidPhoneId(resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String[] path = req.getPathInfo().split("/");

        String status = service.deleteStudentPhoneCheck(path);
        if (status.equals("0")) error.noPhoneId(resp);
        if (status.equals("1")) error.phoneDoesntExist(resp);
        if (status.equals("2")) success.successResponse(resp, 200);
        if (status.equals("3")) error.invalidPhoneId(resp);
    }
}

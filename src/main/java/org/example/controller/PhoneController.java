package org.example.controller;

import com.google.gson.Gson;
import org.example.repository.impl.PhoneRepoImpl;
import org.example.service.impl.PhoneServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "PhoneController", urlPatterns = "/phones/*")
public class PhoneController extends HttpServlet {
    Gson gson = new Gson();
    private final PhoneRepoImpl phoneRepo = new PhoneRepoImpl();
    private final PhoneServiceImpl service = new PhoneServiceImpl(phoneRepo);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String[] path = req.getPathInfo().split("/");

        String status = service.getStudentPhonesCheck(path);
        if (status.equals("0")) errorResponse(resp, 400, "No student ID provided");
        if (status.equals("1")) errorResponse(resp, 400, "No student with this ID");
        if (status.equals("2")) errorResponse(resp, 400, "No phone numbers for this student");
        if (status.equals("3")) {
            successResponse(resp, 200);
            int id = Integer.parseInt(path[1]);
            resp.getWriter().write(gson.toJson(service.getStudentPhones(id)));
        }
        if (status.equals("4")) errorResponse(resp, 400, "Invalid student ID. Should be a type of number");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String[] path = req.getPathInfo().split("/");
        String phoneNumber = req.getParameter("number");

        String status;
        try {
            status = service.addStudentPhoneCheck(path, phoneNumber);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        if (status.equals("0")) errorResponse(resp, 400, "No student ID provided");
        if (status.equals("1")) errorResponse(resp, 400, "No student with this ID");
        if (status.equals("2")) errorResponse(resp, 400, "No phone number provided");
        if (status.equals("3")) successResponse(resp, 201);
        if (status.equals("4")) errorResponse(resp, 400, "Invalid student ID. Should be a type of number");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String[] path = req.getPathInfo().split("/");
        String phoneNumber = req.getParameter("number");

        String status = service.updateStudentPhoneCheck(path, phoneNumber);
        if (status.equals("0")) errorResponse(resp, 400, "Phone number with this ID doesn't exist");
        if (status.equals("1")) errorResponse(resp, 400, "No phone number provided");
        if (status.equals("2")) successResponse(resp, 200);
        if (status.equals("3")) errorResponse(resp, 400, "No phone number ID provided");
        if (status.equals("4")) errorResponse(resp, 400, "Invalid phone ID. Should be a type of number");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] path = req.getPathInfo().split("/");

        String status = service.deleteStudentPhoneCheck(path);
        if (status.equals("0")) errorResponse(resp, 400, "No phone number ID provided");
        if (status.equals("1")) errorResponse(resp, 400, "Phone number with this ID doesn't exist");
        if (status.equals("2")) successResponse(resp, 200);
        if (status.equals("3")) errorResponse(resp, 400, "Invalid phone ID. Should be a type of number");
    }

    private void errorResponse(HttpServletResponse resp, int status, String message) throws IOException {
        resp.setStatus(status);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        resp.getWriter().write(message);
    }

    private void successResponse(HttpServletResponse resp, int status) {
        resp.setStatus(status);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
    }
}

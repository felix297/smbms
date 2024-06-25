package com.company.servlet;

import java.io.IOException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;

public class LogoutServlet extends HttpServlet {
    @Override
    public void doGet (HttpServletRequest request, HttpServletResponse response) {
        HttpSession httpSession = request.getSession();
        if (httpSession.getAttribute("sessionId") != null) {
            httpSession.removeAttribute("sessionId");
        }
        try {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
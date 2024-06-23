package com.company.servlet;

import java.io.IOException;
import com.company.service.UserServiceImpl;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;

public class UserVerifyServlet extends HttpServlet {
    @Override
    public void doGet (HttpServletRequest request, HttpServletResponse response) {
        String userCode = request.getParameter("userCode");
        String userPassword = request.getParameter("userPassword");
        UserServiceImpl service = new UserServiceImpl();
        if (service.userVerify(userCode, userPassword)) {
            try {
                HttpSession session = request.getSession();
                session.setAttribute("sessionId", session.getId());
                response.sendRedirect("jsp/frame.jsp");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void doPost (HttpServletRequest request, HttpServletResponse response) {
        doGet(request, response);
    }
}
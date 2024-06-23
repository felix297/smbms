package com.company.servlet;

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
        System.out.println(userCode + ":" + userPassword);
        UserServiceImpl service = new UserServiceImpl();
        try {
            if (service.userVerify(userCode, userPassword)) {
                HttpSession session = request.getSession();
                session.setAttribute("sessionId", session.getId());
                response.sendRedirect("jsp/frame.jsp");
            } else {
                request.setAttribute("error", "用户名或密码错误！");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost (HttpServletRequest request, HttpServletResponse response) {
        doGet(request, response);
    }
}
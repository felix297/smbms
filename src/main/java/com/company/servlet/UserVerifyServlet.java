package com.company.servlet;

import com.company.pojo.User;
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
        User user = service.userVerify(userCode, userPassword);
        try {
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("sessionId", user);
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
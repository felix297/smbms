package com.company.servlet;

import javax.servlet.ServletException;
import java.io.IOException;
import javax.servlet.http.HttpSession;
import com.company.pojo.User;
import com.company.service.UserServiceImpl;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PasswordModifyServlet extends HttpServlet {
    @Override
    public void doGet (HttpServletRequest request, HttpServletResponse response) {
        String oldpassword = request.getParameter("oldpassword");
        String newpassword = request.getParameter("newpassword");
        String rnewpassword = request.getParameter("rnewpassword");

        UserServiceImpl userService = new UserServiceImpl();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("sessionId");
        if (newpassword.equals(rnewpassword) && oldpassword != null && newpassword != null) {
            if (userService.pwdModify(user.getUserCode(), newpassword)) {
                request.setAttribute("message", "密码修改成功！请退出并使用新密码重新登录");
                session.removeAttribute("sessionId");
            } else {
                request.setAttribute("message", "密码修改失败！");
            }
        } else {
            request.setAttribute("message", "密码修改失败！");
        }

        try {
            request.getRequestDispatcher("pwdmodify.jsp").forward(request, response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost (HttpServletRequest request, HttpServletResponse response) {
        doGet(request, response);
    }
}
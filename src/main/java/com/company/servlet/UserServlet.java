package com.company.servlet;

import com.company.service.RoleServiceImpl;
import java.util.ArrayList;
import com.company.pojo.Role;
import java.io.PrintWriter;
import com.alibaba.fastjson2.JSONArray;
import com.mysql.cj.util.StringUtils;
import java.util.HashMap;
import javax.servlet.ServletException;
import java.io.IOException;
import javax.servlet.http.HttpSession;
import com.company.pojo.User;
import com.company.service.UserServiceImpl;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserServlet extends HttpServlet {
    @Override
    public void doGet (HttpServletRequest request, HttpServletResponse response) {
        String method = request.getParameter("method");
        if (method.equals("loginVerify")) {
            this.loginVerify(request, response);
        } else if (method.equals("passwordModify")) {
            this.passwordModify(request, response);
        } else if (method.equals("queryUser")) {
            this.queryUser(request, response);
        }
    }

    @Override
    public void doPost (HttpServletRequest request, HttpServletResponse response) {
        doGet(request, response);
    }

    public void queryUser (HttpServletRequest request, HttpServletResponse response) {
        String queryName = request.getParameter("queryName");
        String queryRole = request.getParameter("queryUserRole");






        request.setAttribute("totalCount", this.getTotalCount(queryName, queryRole));
        request.setAttribute("queryUserName", queryName);
        request.setAttribute("queryUserRole", queryRole);
        request.setAttribute("roleList", this.selectAllRole());

        this.dispatcher(request, response, "/jsp/user/userlist.jsp");
    }

    public ArrayList<Role> selectAllRole () {
        RoleServiceImpl roleService = new RoleServiceImpl();
        return roleService.selectAllRole();
    }

    public int getTotalCount(String queryName, String queryRole) {
        int totalCount = 0;
        int queryUserRole = 0;

        if(queryRole != null && !queryRole.equals("")){
            queryUserRole = Integer.parseInt(queryRole);
        }

        UserServiceImpl userService = new UserServiceImpl();
        totalCount = userService.getUserNumber(queryName, queryUserRole);

        return totalCount;
    }

    public void dispatcher (HttpServletRequest request, HttpServletResponse response, String path) {
        try {
            request.getRequestDispatcher(path).forward(request, response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    public void passwordModify (HttpServletRequest request, HttpServletResponse response) {
        String newpassword = request.getParameter("newpassword");
        UserServiceImpl userService = new UserServiceImpl();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("sessionId");
        if (userService.pwdModify(user.getUserCode(), newpassword)) {
            request.setAttribute("message", "密码修改成功！请退出并使用新密码重新登录");
            session.removeAttribute("sessionId");
        } else {
            request.setAttribute("message", "密码修改失败！");
        }

        try {
            request.getRequestDispatcher("/jsp/pwdmodify.jsp").forward(request, response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }

    }

    public void loginVerify (HttpServletRequest request, HttpServletResponse response) {
        String oldpassword = request.getParameter("oldpassword");
        User user = (User) request.getSession().getAttribute("sessionId");
        HashMap<String, String> res = new HashMap<>();

        if (user == null) {
            res.put("result", "sessionerror");
        } else if (StringUtils.isNullOrEmpty(oldpassword)) {
            res.put("result", "error");
        } else if (oldpassword.equals(user.getUserPassword())) {
            res.put("result", "true");
        } else {
            res.put("result", "false");
        }

        try {
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.write(JSONArray.toJSONString(res));
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
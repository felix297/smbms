package com.company.servlet;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import com.company.util.Constant;
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
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
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
        } else if (method.equals("getrolelist")) {
            returnJSONRes(response, this.selectAllRole());
        } else if (method.equals("ucexist")) {
            this.ucexist(request, response);
        } else if (method.equals("add")) {
            this.add(request, response);
        } else if (method.equals("selectByUserCode")) {
            this.selectByUserCode(request, response);
        } else if (method.equals("delUser")) {
            this.deleteByUserCode(request, response);
        } else if (method.equals("modifyUser")) {
            this.modifyUser(request, response);
        } else if (method.equals("commitModify")) {
            this.commitModify(request, response);
        }
    }

    @Override
    public void doPost (HttpServletRequest request, HttpServletResponse response) {
        doGet(request, response);
    }

    public void commitModify (HttpServletRequest request, HttpServletResponse response) {
        SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd");
        String userCode = request.getParameter("userCode");
        String userName = request.getParameter("userName");
        String gender = request.getParameter("gender");
        Date birthday = null;
        try {
            birthday = dateFormat.parse(request.getParameter("birthday"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String userRole = request.getParameter("userRole");

        User user = new User();
        user.setUserCode(userCode);
        user.setUserName(userName);
        user.setGender(gender);
        user.setBirthday(birthday);
        user.setPhone(phone);
        user.setAddress(address);
        user.setUserRole(userRole);

        UserServiceImpl userService = new UserServiceImpl();
        if (userService.insert(user)) {
            this.sendRedirect(request, response, request.getContextPath() + "/user?method=queryUser");
            System.out.println();
        } else {
            this.dispatcher(request, response, "/jsp/user/usermodify.jsp");
        }
    }

    public void modifyUser(HttpServletRequest request, HttpServletResponse response){
        String userCode = request.getParameter("userCode");
        UserServiceImpl userService = new UserServiceImpl();

        request.setAttribute("user", userService.getUserInfo(userCode));
        request.setAttribute("roleList", this.selectAllRole());
        this.dispatcher(request, response, "/jsp/user/usermodify.jsp");
    }

    public void deleteByUserCode(HttpServletRequest request, HttpServletResponse response) {
        String userCode = request.getParameter("userCode");
        HashMap<String, String> delResult = new HashMap<>();
        UserServiceImpl userService = new UserServiceImpl();
        if (userCode == null) {
            delResult.put("delResult", "notexist");
        } else {
            if (userService.deleteByUserCode(userCode)) {
                delResult.put("delResult", "true");
            } else {
                delResult.put("delResult", "false");
            }
        }

        this.returnJSONRes(response, delResult);
    }

    public void selectByUserCode(HttpServletRequest request, HttpServletResponse response) {
        String userCode = request.getParameter("userCode");
        UserServiceImpl userService = new UserServiceImpl();
        request.setAttribute("user", userService.getUserInfo(userCode));
        this.dispatcher(request, response, "/jsp/user/userview.jsp");
    }

    public void add (HttpServletRequest request, HttpServletResponse response) {
        SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd");
        String userCode = request.getParameter("userCode");
        String userName = request.getParameter("userName");
        String userPassword = request.getParameter("userPassword");
        String gender = request.getParameter("gender");
        Date birthday = null;
        try {
            birthday = dateFormat.parse(request.getParameter("birthday"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String userRole = request.getParameter("userRole");

        User user = new User();
        user.setUserCode(userCode);
        user.setUserName(userName);
        user.setGender(gender);
        user.setBirthday(birthday);
        user.setPhone(phone);
        user.setAddress(address);
        user.setUserRole(userRole);

        UserServiceImpl userService = new UserServiceImpl();
        if (userService.addUser(user)) {
            this.sendRedirect(request, response, request.getContextPath() + "/user?method=queryUser");
        } else {
            this.dispatcher(request, response, "/jsp/user/useradd.jsp");
        }
    }

    @Test
    public void test () {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("birthday")).thenReturn("1994-06-06");
        add(request, response);
    }

    public void ucexist(HttpServletRequest request, HttpServletResponse response) {
        String userCode = request.getParameter("userCode");
        UserServiceImpl userService = new UserServiceImpl();

        returnJSONRes(response, userService.selectByUserCode(userCode));
    }

    public void queryUser (HttpServletRequest request, HttpServletResponse response) {
        String queryName = request.getParameter("queryName");
        String queryRole = request.getParameter("queryUserRole");
        String pageIndex = request.getParameter("pageIndex");
        int totalCount = this.getTotalCount(queryName, queryRole);
        int currentPageNum = 1;
        if (pageIndex != null) {
            currentPageNum = Integer.parseInt(pageIndex);
        }
        request.setAttribute("totalCount", totalCount);
        request.setAttribute("currentPageNo", currentPageNum);
        request.setAttribute("totalPageCount", (int) Math.ceil((double) totalCount/Constant.getPageSize()));
        request.setAttribute("queryUserName", queryName);
        request.setAttribute("queryUserRole", queryRole);
        request.setAttribute("roleList", this.selectAllRole());
        request.setAttribute("userList", this.selectAllUser(queryName, queryRole, currentPageNum));

        this.dispatcher(request, response, "/jsp/user/userlist.jsp");
    }

    public ArrayList<User> selectAllUser (String queryName, String queryRole, int currentPageNum) {
        int queryUserRole = 0;

        if(queryRole != null && !queryRole.equals("")){
            queryUserRole = Integer.parseInt(queryRole);
        }

        UserServiceImpl userService = new UserServiceImpl();
        return userService.selectAllUser(queryName, queryUserRole, currentPageNum);
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

    public void sendRedirect(HttpServletRequest request, HttpServletResponse response, String location) {
        try {
            response.sendRedirect(location);
        } catch (IOException e) {
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

    public void returnJSONRes (HttpServletResponse response, Object obj) {
        response.setContentType("application/json");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.write(JSONArray.toJSONString(obj));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.flush();
            out.close();
        }
    }
}
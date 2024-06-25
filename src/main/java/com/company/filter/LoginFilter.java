package com.company.filter;

import javax.servlet.ServletException;
import java.io.IOException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.Filter;

public class LoginFilter implements Filter {
    @Override
    public void init (FilterConfig filterConfig) {}

    @Override
    public void doFilter (ServletRequest req, ServletResponse res, FilterChain chain) {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            if (request.getSession().getAttribute("sessionId") != null){
                chain.doFilter(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/error.jsp");
            }
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy () {
    }
}
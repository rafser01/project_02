package com.smartMess.filter;

import com.managedBean.SignUp;
import com.smartMess.pojos.Member;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RequestFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);

        try {
            if (session.getAttribute("user") == null) {

                res.sendRedirect(req.getContextPath() + "/faces/signUp.xhtml");

            } else {
                chain.doFilter(request, response);
            }
//            Member me = new Member();
//            me = (Member) session.getAttribute("user");
            

        } catch (NullPointerException e) {
            System.out.println("fail1");

            res.sendRedirect(req.getContextPath() + "/faces/signUp.xhtml");
        }

    }

    @Override
    public void destroy() {

    }

}

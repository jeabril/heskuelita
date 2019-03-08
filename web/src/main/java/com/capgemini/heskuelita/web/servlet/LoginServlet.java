package com.capgemini.heskuelita.web.servlet;
import com.capgemini.heskuelita.core.Beans.User;
import com.capgemini.heskuelita.service.ISecurityService;
import com.capgemini.heskuelita.service.impl.SecurityServiceImpl;

import java.io.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@WebServlet ("/login")
public class LoginServlet extends HttpServlet {

    private ISecurityService SecurityService;


    public LoginServlet()
    {
        super();
    }
    @Override
    public void init(ServletConfig config) throws ServletException{
        getServletContext context= config.getServletContext();

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);

        User user = new User();
        user.setUserName(req.getParameter("User"));
        user.setPassword(req.getParameter("Password"));


        try{
            this.SecurityService.login (user);
            HttpSession session=req.getSession();
            session.setAttribute("user",user);

            resp.sendRedirect("home.jsp");

        } catch (Exception e){
            resp.sendRedirect("err.jsp");
        }
    }
}

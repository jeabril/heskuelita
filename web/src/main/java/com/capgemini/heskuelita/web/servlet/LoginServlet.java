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


    private IUserSecurityService securityService;


    public LoginServlet () {

        super ();
    }

   @Override
    public void init (ServletConfig config) throws ServletException {

       SessionFactory manager = HibernateUtil.getSessionFactory();

        try {

            this.securityService = new UserSecurityServiceImpl(new UserDaoHibernate(manager));
        } catch (Exception e) {

            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserAnnotation user = new UserAnnotation();
        user.setName (req.getParameter ("user"));
        user.setPassword (req.getParameter ("password"));
        try {

            this.securityService.login (user);

            HttpSession session = req.getSession ();
            session.setAttribute ("user", user);

            resp.sendRedirect ("home.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect ("err.jsp");
        }
        finally {

        }

    }
}

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

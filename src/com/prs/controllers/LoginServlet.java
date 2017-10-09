package com.prs.controllers;

import com.prs.business.users.User;
import com.prs.business.users.UserDAO;
import com.prs.business.users.UserFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({ "/LoginServlet", "/login" })
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static UserDAO userDao;

    public LoginServlet() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String url = "/loginPage.jsp";

        String action = request.getParameter("action");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        userDao = UserFactory.getUserDAO();
        User user = userDao.getUserByUsernamePassword(userName, password);

        if (action != null && action.equalsIgnoreCase("login"))
        {
            url = "/loginPage.jsp";
            if (user != null) {

                // Stores user information into session for later use
                request.getSession().setAttribute("userid", user.getId());
                request.getSession().setAttribute("username", user.getUserName());
                request.getSession().setAttribute("user", user);

                // Welcome Message
                request.setAttribute("message", "Welcome, " + user.getUserName());

                // Forward to JSP
                url = "/home.jsp";

            } else
            {
                // If log in attempt fail if no user is found
                // Print to console: No user found
                System.out.println("No user found.");
            }

        }


        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}

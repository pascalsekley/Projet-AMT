/*
 -----------------------------------------------------------------------------------
 Project 	 : Projet AMT
 File     	 : RegistrationServlet.java
 Author(s)       : Pascal Sekley & Rodrigue Tchuensu 
 Date            : Start: 21.09.16 - End:  
 Purpose         : The goal of this file (Servlet) is to deal with the registration
                   of a user. He is redirected to a confirmation page and can
                   now log in.
 remark(s)       : n/a
 Compiler        : jdk 1.8.0_101
 -----------------------------------------------------------------------------------
 */
package com.mycompany.project.web;

import com.mycompany.project.model.User;
import com.mycompany.project.services.dao.IUserManagerDAO;
import static com.mycompany.project.web.LoginServlet.ATT_USER_SESSION;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <h1> Registration servlet </h1>
 * This servlet implements the POST request when a user whant to register.
 * @author Pascal Sekley & Rodrigue Tchuensu
 * @version 1.0
 * @since 2016-10-19
 */
public class RegistrationServlet extends HttpServlet {

    @EJB
    private IUserManagerDAO userManagerDAO;

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.getRequestDispatcher("/WEB-INF/pages/registration.jsp").forward(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get all parameters from the http request and crete a new user
        String name     = request.getParameter("name");
        String lastname = request.getParameter("lastname");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email    = request.getParameter("email");
        User newUser    = new User(name, lastname, username, email, password);

        // If the registration is successfully done, a confirmation page is displayed
        // If not, an error message is also displayed to the user
        if (userManagerDAO.register(newUser)) {

            request.getSession().setAttribute(ATT_USER_SESSION, newUser);
            response.sendRedirect(request.getContextPath() + "/welcome");

        } else {
            request.setAttribute("errorMessage", "Could not register new user. username already in use !");
            request.getRequestDispatcher("/WEB-INF/pages/registration.jsp").forward(request, response);
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

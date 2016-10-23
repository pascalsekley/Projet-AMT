/*
 -----------------------------------------------------------------------------------
 Project 	 : Projet AMT
 File     	 : LoginServlet.java
 Author(s)       : Pascal Sekley & Rodrigue Tchuensu 
 Date            : Start: 21.09.16 - End:  
 Purpose         : The goal of this file (Servlet) is to deal with the login
                   feature of the web application.
 remark(s)       : n/a
 Compiler        : jdk 1.8.0_101
 -----------------------------------------------------------------------------------
 */
package com.mycompany.project.web;

import com.mycompany.project.model.User;
import com.mycompany.project.services.dao.IUserManagerDAO;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <h1> Login servlet </h1>
 * This servlet implements the POST request when a user wnat to log in.
 * @author Pascal Sekley & Rodrigue Tchuensu
 * @version 1.0
 * @since 2016-10-19
 */

public class LoginServlet extends HttpServlet {
    public static final String ATT_USER_SESSION = "userSession";
    
    @EJB
    IUserManagerDAO userManager;

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

        
        request.getRequestDispatcher("/WEB-INF/pages/index.jsp").forward(request, response);
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
               
               String username = request.getParameter("username");
               String password = request.getParameter("password");
               User connectedUser;
               
        if ((connectedUser = userManager.authenticate(username, password)) != null){
            request.getSession().setAttribute(ATT_USER_SESSION, connectedUser);
            response.sendRedirect(request.getContextPath() + "/welcome");
            
        }else{
            
            request.setAttribute("errorMessage", "Login failed.");
            request.getRequestDispatcher("/WEB-INF/pages/index.jsp").forward(request, response);
        }

    }

}

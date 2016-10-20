/*
 -----------------------------------------------------------------------------------
 Project 	 : Projet AMT
 File     	 : RestrictedPagesServlet.java
 Author(s)       : Pascal Sekley & Rodrigue Tchuensu 
 Date            : Start: 21.09.16 - End:  
 Purpose         : The goal of this file (Servlet) is to deal with the restricted
                   pages that a user can access or not.
 remark(s)       : n/a
 Compiler        : jdk 1.8.0_101
 -----------------------------------------------------------------------------------
 */
package com.mycompany.project.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <h1> Restricted Pages Servlet servlet </h1>
 * This servlet dispatches the request according to the user status.
 * @author Pascal Sekley & Rodrigue Tchuensu
 * @version 1.0
 * @since 2016-10-19
 */
public class RestrictedPagesServlet extends HttpServlet {

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
        request.getRequestDispatcher("/WEB-INF/pages/restricted/restrictedAccess_1.jsp").forward(request, response);
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
        //processRequest(request, response);
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

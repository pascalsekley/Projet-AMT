/*
 -----------------------------------------------------------------------------------
 Project 	 : Projet AMT
 File     	 : RestrictionServlet.java
 Author(s)       : Pascal Sekley & Rodrigue Tchuensu 
 Date            : Start: 21.09.16 - End:  
 Purpose         : The goal of this file (Filter) is to filter all the requests
                   sent by a user and act accordingly. It protects some restricted
                   page from users that are not logged in. A user can no more access 
                   those pages once he log out.
 remark(s)       : n/a
 Compiler        : jdk 1.8.0_101
 -----------------------------------------------------------------------------------
 */
package com.mycompany.project.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <h1> Restriction Filter </h1>
 * This filter protects some pages that a user could see once he logs in.
 * @author Pascal Sekley & Rodrigue Tchuensu
 * @version 1.0
 * @since 2016-10-19
 */
public class RestrictionFilter implements Filter {
    
    private static final boolean debug = false;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    
    public RestrictionFilter() {
    }    

    /**
     * This method is in charge to filter the requests sent by the user
     * @param req The servlet request we are processing
     * @param res The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain)
            throws IOException, ServletException {
        
        /* Cast request and response objets*/

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        
        
        String path = request.getRequestURI().substring(request.getContextPath().length());
        if(path.equals("/")) {
            req.getRequestDispatcher("/WEB-INF/pages/index.jsp").forward(req, res);
            return;
        }
        
        if(path.startsWith("/inc") || path.startsWith("/api/") || 
           path.equals("/logout") || path.equals("/registration") || 
           path.equals("/login")){
            chain.doFilter(request, response);
            return;
        }
 
        /* Get the Seesion from the http request */
        HttpSession session = request.getSession();
        
       
         // The user is not connected if the user object doesn't exist in the session
         
        if (session.getAttribute("userSession") == null) {

            /* redirection to the home page */
            response.sendRedirect(request.getContextPath() + "/login");

        } else {

            /* Display the restricted page */
            chain.doFilter(request, response);

        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {        
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {        
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {                
                log("RestrictionFilter: Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("RestrictionFilter()");
        }
        StringBuffer sb = new StringBuffer("RestrictionFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
        
    }
    
  
    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }
    
    public void log(String msg) {
        filterConfig.getServletContext().log(msg);        
    }

}

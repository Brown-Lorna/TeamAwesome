/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.byui.fb;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Grant
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    private final String MASTER_USER = "admin";
    private final String MASTER_PASS = "adminPASS2";
    private String dataDirectory = System.getenv("OPENSHIFT_DATA_DIR");

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO: Do we need to do anything here?
        String correctName = "";
        String correctPass = "";

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        processRequest(request, response);
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
        // Get the database
        DataBaseHandler dbh = DataBaseHandler.getInstance();
        
        // Get the inputted username and password
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Get user information in the database
        User user = dbh.getUser(username);
        
        // Check if user exists and that the password was correct
        if (user != null && password.equals(user.getPassword())) {
            // Log user in, set the username, and the userType
            request.getSession().setAttribute("logged", true);
            request.getSession().setAttribute("username", user.getUsername());
            request.getSession().setAttribute("userType", user.getUserType());
        } else {
            // Tell admin.jsp that there was an error and give the message
            request.setAttribute("errorExists", true);
            request.setAttribute("error", "Username/Password is incorrect");
        }
        
        // Go to admin.jsp
        request.getRequestDispatcher("admin.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

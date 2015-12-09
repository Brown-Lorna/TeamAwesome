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
        // TODO: Create DataBaseHandler
        DataBaseHandler dbh = DataBaseHandler.getInstance();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        // TODO: Check username against Database
        User user = dbh.getUser(username);
        if (user != null) {

            // TODO: Check password against hashed password in Database
            if (username.equals(user.getPassword())) {

                // TODO: If all's good, create session and set session username and userType.
                request.getSession().setAttribute("username", user.getUsername());
                request.getSession().setAttribute("userType", user.getUserType());
            } else {
                request.setAttribute("error", "Username/Password is incorrect");
            }
        } else {
            request.setAttribute("error", "Username/Password is incorrect");
        }
        // TODO: Redirect to welcome.jsp or display incorrect credentials information
        request.getRequestDispatcher("index.jsp").forward(request, response);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.byui.fb;

import facebook4j.FacebookException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Grant
 */
@WebServlet(name = "ShareImage", urlPatterns = {"/ShareImage"})
public class ShareImage extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ShareImage</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ShareImage at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        // Get username, image name, and the message to be posted
        String username     = (String)request.getSession().getAttribute("username");
        String imageName    = (String)request.getAttribute("image");
        String message      = (String)request.getAttribute("message");
        
        // Is there a username on the session
        if (username != null) {
            // Get the user from the database
            DataBaseHandler dbh = new DataBaseHandler();
            boolean usernameExists = dbh.getUser(username);

            if (usernameExists) {
                // Get the image from the database
                FaceBookHandler fbh = FaceBookHandler.getInstance();
                Image image = dbh.getImage(imageName);
                
                try {
                    // Share the image on the FaceBook wall
                    fbh.shareImage(image.getName(), image.getBytes(), message);
                    request.setAttribute("imageShared", true);
                } catch (FacebookException ex) {
                    Logger.getLogger(ShareImage.class.getName()).log(Level.SEVERE, null, ex);
                    request.setAttribute("error", "Could not post your message to your FaceBook timeline.");
                }

                request.getRequestDispatcher("welcome.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Username does not exist.");
            }
        } else {
            request.setAttribute("error", "User not logged in.");
        }
        
        request.getRequestDispatcher("index.jsp").forward(request, response);
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
        processRequest(request, response);
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

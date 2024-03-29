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
        // Get image name, the message to be posted, and the current access token code
        int imageId    = Integer.parseInt((String)request.getSession().getAttribute("imageId"));
        String message = (String)request.getSession().getAttribute("message");
        String code    = (String)request.getParameter("code");

        // Get the user from the database
        DataBaseHandler dbh = DataBaseHandler.getInstance();

        // Get the image from the database
        FaceBookHandler fbh = FaceBookHandler.getInstance();
        Image image = dbh.getImage(imageId);

        // Share the image to FaceBook Timeline
        try {
            fbh.getAccessToken(code);
            fbh.shareImage(image.getName(), image.getBytes(), message);
            request.setAttribute("imageShared", true);
        } catch (FacebookException ex) {
            // Was there an error in trying to share to Facebook?
            request.setAttribute("errorExists", true);
            request.setAttribute("error", "Could not post your message to your FaceBook timeline.");
        }

        // Go to LoadImages (index.jsp)
        request.getRequestDispatcher("LoadImages?dest=index.jsp").forward(request, response);
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

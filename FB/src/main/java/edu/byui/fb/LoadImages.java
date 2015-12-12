/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.byui.fb;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Grant
 */
@WebServlet(name = "LoadImages", urlPatterns = {"/LoadImages"})
public class LoadImages extends HttpServlet {

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
        // Grab the database and get all the images from database
        DataBaseHandler dbh = DataBaseHandler.getInstance();
        List<Image> images = dbh.getImages();
        
        // Get attributes about an image being shared and errors
        Boolean imageShared  = (Boolean)request.getAttribute("imageShared");
        Boolean errorExists  = (Boolean)request.getAttribute("errorExists");
        Boolean imageDeleted = (Boolean)request.getAttribute("imageDeleted");
        Boolean deletedError = (Boolean)request.getAttribute("deletedError");
        Boolean imageAdded   = (Boolean)request.getAttribute("imageAdded");
        Boolean addedError   = (Boolean)request.getAttribute("addedError");
        
        // Check if the imageShared exists
        if (imageShared != null && imageShared) {
            request.setAttribute("imageShared", true);
        }
        
        // Check if the errorExists exists
        if (errorExists != null && errorExists) {
            request.setAttribute("errorExists", true);
            request.setAttribute("error", "Could not post your message to your FaceBook timeline.");
        }
        
        // Check if the imageDeleted exists
        if (imageDeleted != null && imageDeleted) {
            request.setAttribute("imageDeleted", true);
        }
        
        // Check if the deletedError exists
        if (deletedError != null && deletedError) {
            request.setAttribute("deletedError", true);
        }
        
        // Check if the imageAdded exists
        if (imageAdded != null && imageAdded) {
            request.setAttribute("imageAdded", true);
        }
        
        // Check if the addedError exists
        if (addedError != null && addedError) {
            request.setAttribute("addedError", true);
        }
        
        // Get the destination location
        String dest = request.getParameter("dest");
        
        if (dest == null) {
            dest = "index.jsp";
        }
        
        // Set the images list and tell index.jsp not to redirect back here.
        request.setAttribute("images", images);
        request.setAttribute("redirect", true);
        request.getRequestDispatcher(dest).forward(request, response);
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

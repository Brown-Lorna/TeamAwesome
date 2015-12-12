/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.byui.fb;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Grant
 */
@WebServlet(name = "AddImage", urlPatterns = {"/AddImage"})
@MultipartConfig
public class AddImage extends HttpServlet {

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
        DataBaseHandler dbh = DataBaseHandler.getInstance();
        boolean logged = (boolean) request.getSession().getAttribute("logged");
        String username = (String) request.getSession().getAttribute("username");
        User user = dbh.getUser(username);

        if (username != null && user != null && logged) {
            if (ServletFileUpload.isMultipartContent(request)) {
                try {
                    List<FileItem> multipart = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                    String title = "";
                    InputStream imageInputStream = null;

                    for (FileItem item : multipart) {
                        if (!item.isFormField()) {
                            if (item.getFieldName().equals("image")) {
                                imageInputStream = item.getInputStream();
                            }
                        } else if (item.isFormField()) {
                            if (item.getFieldName().equals("title")) {
                                title = item.getString();
                            }
                        }
                    }

                    if (title.equals("")) {
                        title = "No title";
                    }
                    Image image = new Image(title, imageInputStream);
                    dbh.addImage(image, user);
                } catch (FileUploadException ex) {
                    Logger.getLogger(AddImage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            request.setAttribute("errorExists", true);
            request.setAttribute("error", "Not logged in. Cannot upload an image.");
        }
        request.getRequestDispatcher("admin.jsp").forward(request, response);
    }

    private String getValue(Part part) {
        String contentDisp = part.getHeader("content-disposition");

        System.out.println(part.toString());
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length() - 1);
            }
        }
        return "";
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

    // TODO: findImage NOTE: By clicking submit (or whatever) you are saying: "I have the rights to use this image"
}

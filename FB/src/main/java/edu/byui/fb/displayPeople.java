/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.byui.fb;

import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Theriault
 */
@WebServlet(name = "displayPeople", urlPatterns = {"/displayPeople"})
public class displayPeople extends HttpServlet {

    JDBCClass db = new JDBCClass();

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
            out.println("<title>Servlet displayPeople</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet displayPeople at " + request.getContextPath() + "</h1>");
            ResultSet rs = db.getPeople();
            while (rs.next()) {
                try {
                    // Printing results to the console
                    System.out.println("First Name: " + rs.getString("first_name")
                            + "<br>Last Name- " + rs.getString("last_name")
                            + "<br>Birth Date: " + rs.getDate("birthday"));
                } catch (SQLException ex) {
                    Logger.getLogger(displayPeople.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                rs.getString("first_name");
            } catch (SQLException ex) {
                Logger.getLogger(displayPeople.class.getName()).log(Level.SEVERE, null, ex);
            }
            List<Person> people = new ArrayList<>();
            while (rs.next()) {
                Person person = new Person();
                person.setFirst_name(rs.getString("first_name"));
                person.setLast_name(rs.getString("last_name"));
                person.setId(rs.getInt("id"));
                person.setBirthday(rs.getDate("birthday"));
                
                people.push(person);
            }
            JDBCClass db = new JDBCClass();
            db.getPeople();
            out.println("</body>");
            out.println("</html>");
        } catch (SQLException ex) {
            Logger.getLogger(displayPeople.class.getName()).log(Level.SEVERE, null, ex);
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

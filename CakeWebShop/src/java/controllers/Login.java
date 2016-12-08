/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import models.UserMapper;

/**
 *
 * @author freyb
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

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

        UserMapper um = new UserMapper();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        boolean isAuthenticated = um.authenticateUser(email, password);
        if (isAuthenticated) {
            try {
                User user = um.getUserByEmail(email);
                HttpSession session = request.getSession();
                session.setAttribute("userObj", user);
                response.sendRedirect("index.jsp");
            } catch (Exception ex) {
                ex.printStackTrace();
            }            
        } else {
            //response.getWriter().print("du er ikke logget ind");
            response.sendRedirect("index.jsp");
        }
        String origin = request.getParameter("origin");
        if (origin != null) {
            if (origin.equals("logout")) {
                logout(request);
            }
        }

    }

    private void logout(HttpServletRequest request) {
        request.getSession().invalidate();
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

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

@WebServlet(name = "Login", urlPatterns = {"/AccountController"})
public class AccountController extends HttpServlet {

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

        String action = request.getParameter("action");
        UserMapper um = new UserMapper();
        if (action.equals("login")) {
            
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            boolean isAuthenticated = um.authenticateUser(email, password);
            if (isAuthenticated) {
                try {
                    User user = um.getUserByEmail(email);
                    HttpSession session = request.getSession();
                    session.setAttribute("userObj", user);
                    response.sendRedirect("home.jsp");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
               //User is redirected if login fails.
                response.sendRedirect("home.jsp");
            }
        }else if(action.equals("logout")){
            logout(request);
            response.sendRedirect("/CakeWebShop");
        }
         else if(action.equals("register")){
            String email = (String)request.getParameter("Email");
            String password = (String)request.getParameter("Password1");
            String firstname = (String)request.getParameter("FirstName");
            String lastname = (String)request.getParameter("LastName");
            String phonenumber = (String)request.getParameter("PhoneNumber");
            String address = (String)request.getParameter("Address");
            String zip = (String)request.getParameter("Zip");    
        
    
         User user = new User (firstname, lastname, email, phonenumber, address, zip, password);
         um.createUser(user);
         response.sendRedirect("home.jsp");
                
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

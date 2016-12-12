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
                //response.getWriter().print("du er ikke logget ind");
                response.sendRedirect("home.jsp");
            }
        }else if(action.equals("logout")){
            logout(request);
            response.sendRedirect("/CakeWebShop");
        }
         else if(action.equals("register")){
            String email = (String)request.getAttribute("Email");
            String password = (String)request.getAttribute("Password");
            String firstname = (String)request.getAttribute("FirstName");
            String larstname = (String)request.getAttribute("LastName");
            String phonenumber = (String)request.getAttribute("PhoneNumber");
            String address = (String)request.getAttribute("Address");
            String zip = (String)request.getAttribute("Zip");    
        }
    
         User user = new User (firstname, lastname, email, phonenumber, address, zip, password);

//        String origin = request.getParameter("origin");
//        if (origin != null) {
//            if (origin.equals("logout")) {
//                logout(request);
//            }
//        }

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

package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Cart;
import models.OrderLine;
import models.ShopItem;
import models.ShopItemMapper;

@WebServlet(name = "HomeController", urlPatterns = {"/HomeController"})
public class HomeController extends HttpServlet {

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
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        ShopItemMapper sim = new ShopItemMapper();
        
        if (session.getAttribute("firstVisit") == null) {
            session.setAttribute("firstVisit", 1);
        }       
       
        if (session.getAttribute("firstVisit").equals(1)) {
            session.setAttribute("firstVisit", 0); // make sure this only runs once/session           
            List<ShopItem> items = sim.getAllItems();
            
            //New Empty Cart.   
            Cart cart = new Cart(new ArrayList<OrderLine>());

            //Sets The ShopItems and the Cart objects on the session
            //So that guest also can add items to cart before they create a user.
            session.setAttribute("cakeList", items);
            session.setAttribute("cart", cart);

            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
        
        if (action.equals("home")) {          
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }

       response.sendRedirect("/index.jsp");

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
       
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

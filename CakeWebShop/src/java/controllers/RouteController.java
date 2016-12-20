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

@WebServlet(name = "RouteController", urlPatterns = {"/RouteController"})
public class RouteController extends HttpServlet {

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

            request.getRequestDispatcher("/home.jsp").include(request, response);
        }
        
        if (action.equals("home")) {          
            request.getRequestDispatcher("/home.jsp").forward(request, response);
        }

       response.sendRedirect("/home.jsp");

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
        String id = request.getParameter("id");
        String page = null;
        if (action.equals("register")) {
            page = "/Register.jsp";
        } else if (action.equals("edit")) {
            page = "/ShopItemController?id=" + id;
        } else if (action.equals("create")) {
            page = "/ShopItemController";
        }else if (action.equals("details")) {
            page = "/ShopItemController?id=" + id;
        }

        request.getRequestDispatcher(page).include(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

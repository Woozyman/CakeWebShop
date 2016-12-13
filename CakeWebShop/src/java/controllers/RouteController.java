package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.tagext.JspFragment;
import javax.swing.text.html.HTML;
import javax.websocket.Session;
import models.Cart;
import models.OrderLine;
import models.OrderLineMapper;
import models.OrderMapper;
import models.ShopItem;
import models.ShopItemMapper;

@WebServlet(name = "RouteController", urlPatterns = {"/RouteController", "/"})
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
        String mainPage = request.getContextPath();
        String action = request.getParameter("action");

        if (mainPage.equals("/CakeWebShop")) {
            
            ShopItemMapper sim = new ShopItemMapper();
            List<ShopItem> si = sim.getAllItems();  
            
            OrderLineMapper orm = new OrderLineMapper();
            List<OrderLine> orderLines = orm.getOrderLines(0);
            
            Cart cart = new Cart(si, orderLines);
           
            HttpSession session = request.getSession();
            //Sets The ShopItems and the Cart objects on the session
            //So that guest also can add items to cart before they create a user.
            session.setAttribute("cakeList", si);
            session.setAttribute("cart", cart);
        }

        request.getRequestDispatcher("/home.jsp").forward(request, response);
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
            page = "/ShopItemController?id="+id;           
        } else if (action.equals("create")) {
            page = "/ShopItemController";            
        }
            request.getRequestDispatcher(page).include(request, response);
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

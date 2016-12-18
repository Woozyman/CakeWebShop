package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Cart;
import models.Order;
import models.OrderLine;
import models.OrderLineMapper;
import models.OrderMapper;
import models.ShopItem;
import models.ShopItemMapper;
import models.User;
import models.UserMapper;

@WebServlet(name = "CartController", urlPatterns = {"/CartController"})
public class CartController extends HttpServlet {

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
        OrderLineMapper lineMapper = new OrderLineMapper();
        
        
        if (action.equals("showCart")) {
           Order order = (Order) session.getAttribute("order");
           Cart cart = (Cart) session.getAttribute("cart");
           int id = order.getOrderId();
         //  List<OrderLine> lineItems = lineMapper.getOrderLines(id);
           List<OrderLine> lineItems = cart.getOrderLines();
           session.setAttribute("orderLines", lineItems);
           List<ShopItem> shopItems = sim.mapShopItemsToOrderLines(lineItems);
           session.setAttribute("shopItems", shopItems);
           request.getRequestDispatcher("/cart.jsp").forward(request, response);
        }

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
        HttpSession session = request.getSession();
        UserMapper um = new UserMapper();
        OrderMapper orm = new OrderMapper();
        ShopItemMapper sim = new ShopItemMapper();
        OrderLineMapper lineMapper = new OrderLineMapper();
        Order order = null;

        if (action.equals("addToCart")) {

            User user = (User) session.getAttribute("userObj");

            int orderId = -1;

            Cart cart = (Cart) session.getAttribute("cart");
            int itemId = Integer.parseInt(request.getParameter("id"));
            int numOfItems = Integer.parseInt(request.getParameter("numOfItems"));
            int userid = um.getUserId(user.getEmail());
            double currentPrice = lineMapper.getCurrentPrice(itemId);
            
            if (cart.getOrderLines().isEmpty()) {
                order = new Order(userid, null, null, 1);
                //persist Order in Db to refer orderLines to.
                orm.createOrder(order);
                order.setOrderId(um.getUnpaidOrderId(user));
                session.setAttribute("order", order);

            } else {
                order = (Order) session.getAttribute("order");
                order.setOrderId(um.getUnpaidOrderId(user));
            }
            orderId = order.getOrderId();          
            
            OrderLine orderLine = new OrderLine(orderId, itemId, numOfItems, currentPrice);
            
            lineMapper.addOrderLine(orderLine);

            cart.addItemToCart(orderLine);

            session.setAttribute("cart", cart);

            response.sendRedirect("home.jsp");
        }

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

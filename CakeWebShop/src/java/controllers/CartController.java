package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
        User user = (User) session.getAttribute("userObj");
        if (action.equals("showCart")) {

            Order order = (Order) session.getAttribute("order");
            session.setAttribute("orderId", order.getOrderId());
            Cart cart = (Cart) session.getAttribute("cart");
            List<OrderLine> lineItems = cart.getOrderLines();
            session.setAttribute("orderLines", lineItems);

            List<ShopItem> shopItems = sim.mapShopItemsToOrderLines(lineItems);
            session.setAttribute("shopItems", shopItems);

            request.getRequestDispatcher("/cart.jsp").forward(request, response);

        } else if (action.equals("checkout")) {
            if (user != null) {
                request.getRequestDispatcher("/checkOut.jsp").forward(request, response);
            } else {
                //If registered user redirect to login!.
                //Else...
                PrintWriter out = response.getWriter();
                out.println("<script type=\"text/javascript\">");
                out.println("alert('You need to be a registred user\\nPlease login or register');");
                out.println("</script>");
                response.addHeader("Content-Type", "text/html; charset=UTF-8");
                request.getRequestDispatcher("/formRegistration.jsp").include(request, response);
            }
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
        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        UserMapper um = new UserMapper();
        OrderMapper orm = new OrderMapper();
        OrderLineMapper lineMapper = new OrderLineMapper();
        ShopItemMapper sim = new ShopItemMapper();
        Order order = null;
        OrderLine orderLine = null;
        Cart cart = (Cart) session.getAttribute("cart");
        User user = (User) session.getAttribute("userObj");
        if (action.equals("addToCart")) {

            int orderId = -1;

            int itemId = Integer.parseInt(request.getParameter("id"));
            int numOfItems = Integer.parseInt(request.getParameter("numOfItems"));

            double currentPrice = lineMapper.getCurrentPrice(itemId);

            if (cart.getOrderLines().isEmpty()) {
                if (user != null) {
                    int userid = um.getUserId(user.getEmail());
                    order = new Order(userid, null, null, 1);
                    //persist Order in Db to refer orderLines to.
                    orm.createOrder(order);
                    order.setOrderId(um.getUnpaidOrderId(user));
                    session.setAttribute("order", order);
                } else {
                    //userid  = 0 means not a registered user.
                    order = new Order(0, null, null, 1);
                    session.setAttribute("order", order);
                }

            } else {
                order = (Order) session.getAttribute("order");
                if (user != null) {
                    order.setOrderId(um.getUnpaidOrderId(user));
                }
            }

            if (user != null) {

                orderId = order.getOrderId();
                //if item is already in cart. add it to existing orderline
                if (lineMapper.itemAlreadyOnOrder(itemId)) {
                    lineMapper.updateOrderLine(itemId, numOfItems, orderId, true);
                } else {
                    orderLine = new OrderLine(orderId, itemId, numOfItems, currentPrice);

                    lineMapper.addOrderLine(orderLine);
                    cart.addItemToCart(orderLine);
                }
            } else {
                orderLine = new OrderLine(0, itemId, numOfItems, currentPrice);
                cart.addItemToCart(orderLine);
            }

            session.setAttribute("cart", cart);

            // request.getRequestDispatcher("/index.jsp").forward(request, response);
        } else if (action.equals("update")) {
            order = (Order) session.getAttribute("order");
            int numOfItems = Integer.parseInt(request.getParameter("numOfItems"));
            if (user != null) {
                lineMapper.updateOrderLine(id, numOfItems, order.getOrderId(), false);
            } else {
                List<OrderLine> lines = (List<OrderLine>) session.getAttribute("orderLines");

                for (OrderLine lineItem : lines) {
                    if (lineItem.getShopItemId() == id) {
                        lineItem.setNumberOfItems(numOfItems);
                        session.setAttribute("orderLines", lines);
                        break;
                    }
                }
            }

        } else if (action.equals("remove")) {
            order = (Order) session.getAttribute("order");
            if (user != null) {
                lineMapper.removeOrderLine(id, order.getOrderId());
                //Update cart on session
                cart = sim.getCart(order.getOrderId());
                session.setAttribute("cart", cart);
            } else {
                List<OrderLine> orderLines = (List<OrderLine>) session.getAttribute("orderLines");
                for (OrderLine lineItem : orderLines) {
                    if (lineItem.getShopItemId() == id) {
                        orderLines.remove(lineItem);
                        break;
                    }
                }
                Cart updatedCart = new Cart(orderLines);
                session.setAttribute("cart", updatedCart);

                List<ShopItem> shopItems = sim.mapShopItemsToOrderLines(orderLines);
                session.setAttribute("shopItems", shopItems);
                
                session.setAttribute("orderLines", orderLines);

            }

        }

        if (action.equals("remove") || action.equals("update")) {

            request.getRequestDispatcher("/cart.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
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

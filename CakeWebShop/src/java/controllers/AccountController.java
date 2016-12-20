 package controllers;

import dataaccess.PasswordStorage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Cart;
import models.Order;
import models.OrderLine;
import models.OrderMapper;
import models.ShopItemMapper;
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
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        UserMapper um = new UserMapper();
        ShopItemMapper sim = new ShopItemMapper();
        OrderMapper orm = new OrderMapper();
        if (action.equals("login")) {

            String email = request.getParameter("email");
            String password = request.getParameter("password");
            boolean isAuthenticated = false;
            try {
                isAuthenticated = um.authenticateUser(email, password);
            } catch (PasswordStorage.CannotPerformOperationException | PasswordStorage.InvalidHashException ex) {
                Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (isAuthenticated) {
                try {
                    User user = um.getUserByEmail(email);
                    session.setAttribute("userObj", user);

                    int unPaidOrderId = um.getUnpaidOrderId(user);
                    if (unPaidOrderId != -1) {
                        /*gets the unpaid order and sets in on the session to be updated later 
                        if user decides to checkout and pay.*/
                        Order order = orm.getOrder(unPaidOrderId);
                        session.setAttribute("order", order);
                        /*gets current Cart if any from session
                        And if it's not empty it merges with the old cart from db */
                        Cart currenCart = (Cart) session.getAttribute("cart");
                        Cart oldCart = sim.getCart(unPaidOrderId);
                        if (currenCart == null) {
                            session.setAttribute("cart", oldCart);
                        } else {
                            currenCart = mergeCarts(oldCart, currenCart);
                            session.setAttribute("cart", currenCart);
                        }
                    }

                    response.sendRedirect("home.jsp");
                } catch (IOException ex) {
                }
            } else {
                //User is redirected if login fails.
                response.sendRedirect("home.jsp");
            }
        } else if (action.equals("logout")) {
            logout(request);
            response.sendRedirect("/CakeWebShop");
        } else if (action.equals("register")) {
            String email = (String) request.getParameter("Email");
            String password = (String) request.getParameter("Password");
            String firstname = (String) request.getParameter("FirstName");
            String lastname = (String) request.getParameter("LastName");
            String phonenumber = (String) request.getParameter("PhoneNumber");
            String address = (String) request.getParameter("Address");
            String zip = (String) request.getParameter("Zip");
            
            User user = new User(firstname, lastname, email, phonenumber, address, zip, password);
            try {
                um.createUser(user);
                //Implement Mapping of order in cart when user registers with items in it... orderlines must be updated with userid and orderid.
            } catch (PasswordStorage.CannotPerformOperationException ex) {
                Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.sendRedirect("home.jsp");

        } else if (action.equals("pay")) {
            PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Payment Complete, order is in the oven.');");
            out.println("location='home.jsp';");
            out.println("</script>");
            out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            orm.completeOrder(orderId);
        }

    }

    private void logout(HttpServletRequest request) {
        request.getSession().invalidate();
    }

    private Cart mergeCarts(Cart oldCart, Cart newCart) {

        Cart mergedCart = oldCart;

        for (OrderLine lineItem : newCart.getOrderLines()) {
            mergedCart.addItemToCart(lineItem);
        }

        return mergedCart;
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

package controllers;

import dataaccess.PasswordStorage;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
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
        OrderLineMapper lineMapper = new OrderLineMapper();
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
                            currenCart = mergeCarts(currenCart, unPaidOrderId);
                            // lineMapper.addMultipleOrderLines(currenCart.getOrderLines());
                            session.setAttribute("cart", currenCart);
                        }
                    } else {
                        lineMapper = new OrderLineMapper();
                        Cart currenCart = (Cart) session.getAttribute("cart");
                        Order newOrder = new Order(um.getUserId(email), null, null, 1);
                        orm.createOrder(newOrder);
                        unPaidOrderId = um.getUnpaidOrderId(user);
                        newOrder.setOrderId(unPaidOrderId);
                        session.setAttribute("order", newOrder);
                        for (OrderLine line : currenCart.getOrderLines()) {
                            line.setOrderId(unPaidOrderId);
                            lineMapper.addOrderLine(line);
                        }
                        session.setAttribute("cart", currenCart);
                        Order sessionOrder = (Order) session.getAttribute("order");
                        try {
                            sessionOrder.setUserId(um.getUserId(email));
                            sessionOrder.setOrderId(unPaidOrderId);
                            session.setAttribute("order", sessionOrder);
                            session.setAttribute("orderId", sessionOrder.getOrderId());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    response.sendRedirect("index.jsp");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                //User is redirected if login fails.
                response.sendRedirect("index.jsp");
            }
        } else if (action.equals("logout")) {
            logout(request);
            response.sendRedirect("/CakeWebShop");
        } else if (action.equals("register")) {
            Map<String, String> errors = new HashMap<String, String>();

            String email = (String) request.getParameter("Email");
            if (email.length() < 10) {
                errors.put("Email", "Please write a valid Email Address");
            }
            String password = (String) request.getParameter("Password");
            if (password.length() < 5) {
                errors.put("Password", "Please write a valid password");
            }
            String password2 = (String) request.getParameter("Password2");
            if (!password2.equals(password)) {
                errors.put("Password2", "Password does not match");
            }
            String firstname = (String) request.getParameter("FirstName");
            if (firstname.length() < 3) {
                errors.put("FirstName", "Please write a valid first name");
            }
            String lastname = (String) request.getParameter("LastName");
            if (lastname.length() < 3) {
                errors.put("LastName", "Please write a valid last name");
            }
            String phonenumber = (String) request.getParameter("PhoneNumber");
            if (phonenumber.length() < 8) {
                errors.put("PhoneNumber", "Please write a valid Phone number");
            }
            String address = (String) request.getParameter("Address");
            if (address.length() < 20) {
                errors.put("Address", "Please write a valid first name");
            }
            String zip = (String) request.getParameter("Zip");
            if (zip.length() < 4 && zip.length() > 6) {
                errors.put("Zip", "Please write a valid first name");
            }
            
            if (errors.isEmpty()) {
            // No errors, redirect to Amtrak.
            // response.sendRedirect("FormResult.jsp?"+firstname);
           
            User user = new User(firstname, lastname, email, phonenumber, address, zip, password);
            
             try {
                um.createUser(user);
                //Implement Mapping of order in cart when user registers with items in it... orderlines must be updated with userid and orderid.
            } catch (PasswordStorage.CannotPerformOperationException ex) {
                Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.sendRedirect("index.jsp");

        } else {
            // Put errors in request scope and forward back to JSP.
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("/formRegistration.jsp").forward(request, response);
        }

           
           

        } else if (action.equals("pay")) {
            PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Payment Complete, order is in the oven.');");
            out.println("location='index.jsp';");
            out.println("</script>");
            out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
            int orderId = 0;
            try {
                orderId = Integer.parseInt(request.getParameter("orderid"));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            try {
                orm.completeOrder(orderId);
            } catch (ParseException ex) {
                Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Resetting Shopping Cart
            session.setAttribute("orderLines", new ArrayList<OrderLine>());
            session.setAttribute("cart", new Cart((ArrayList<OrderLine>) session.getAttribute("orderLines")));

        }

    }

    private void logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        request.getRequestDispatcher("/HomeController");
    }

    private Cart mergeCarts(Cart currentcart, int orderId) {

        OrderLineMapper lineMapper = new OrderLineMapper();
        //Update orderLine in database
        for (OrderLine lineItem : currentcart.getOrderLines()) {
            lineItem.setOrderId(orderId);
            if (lineMapper.itemAlreadyOnOrder(lineItem.getShopItemId())) {
                lineMapper.updateOrderLine(lineItem.getShopItemId(), lineItem.getNumberOfItems(), orderId, true);
            } else {
                lineMapper.addOrderLine(lineItem);
            }
        }

        Cart mergedCart = new Cart(lineMapper.getOrderLines(orderId));

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

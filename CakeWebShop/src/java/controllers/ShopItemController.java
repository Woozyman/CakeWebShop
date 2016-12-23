package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.OrderLineMapper;
import models.OrderMapper;
import models.ShopItem;
import models.ShopItemMapper;
import models.Time;

@WebServlet(name = "ShopItemController", urlPatterns = {"/ShopItemController"})
public class ShopItemController extends HttpServlet {

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
        ShopItem item = new ShopItem();
        String action = request.getParameter("action");
        String page = null;
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            ShopItemMapper sim = new ShopItemMapper();
            item = sim.getItem(id);

        } catch (NumberFormatException e) {
            e.getMessage();
        } finally {
            if (action.equals("details")) {
                request.setAttribute("item", item);
                page = "cakeDetails.jsp";
            } else if (action.equals("create")) {
                page = "upload.jsp";
            }
        }

        request.getRequestDispatcher(page).forward(request, response);
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
        int id = -1;
        if (request.getParameter("id") != null) {
            id = Integer.parseInt(request.getParameter("id"));
        }
       
        ShopItemMapper sim = new ShopItemMapper();
        ShopItem item = sim.getItem(id);
        if (action.equals(("edit"))) {

            // ShopItem item = sim.getItem(id);
            request.setAttribute("shopItem", item);

            request.getRequestDispatcher("/formEditShopItem.jsp").forward(request, response);

        } else if (action.equals("create")) {

            //  ShopItem item = new ShopItem();
            item.setItemName((String) request.getAttribute("itemName"));
            item.setItemDescription((String) request.getAttribute("itemDescription"));
            item.setItemPicture((String) request.getAttribute("itemPicture"));
            item.setItemPrice((Double) request.getAttribute("itemPrice"));

            sim.addItem(item);

            response.sendRedirect("index.jsp");
        } else if (action.equals("updateitem")) {

            String name = (String) request.getParameter("itemName");
            String desc = (String) request.getParameter("itemDescription");
            String pic = (String) request.getParameter("itemPicture");
            Double price = Double.parseDouble(request.getParameter("itemPrice"));
            String date = (String) request.getParameter("discontinuedDate");

            item.setItemName(name);
            item.setItemDescription(desc);
            item.setItemPicture(pic);
            item.setItemPrice(price);
            if (date != null) {
                item.setDiscontinuedDate(date);
            }
            sim.updateItem(item, id);

            response.sendRedirect("index.jsp");
        } else if (action.equals("markasbaked")) {
            OrderLineMapper lineMapper = new OrderLineMapper();
            OrderMapper orm = new OrderMapper();
            int orderlineid = Integer.parseInt(request.getParameter("orderlineid"));
            int orderId = Integer.parseInt(request.getParameter("orderid"));
            try {
                lineMapper.markOrderLineCompleted(orderlineid);
                
                if (orm.isOrderCompleted(orderId)) {
                    Time time = new Time();
                    orm.setOrderCompletedDate(orderId, time.getTimeNow());
                }

            } catch (SQLException e) {
                e.getMessage();
            } catch (ParseException ex) {
                Logger.getLogger(ShopItemController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            request.getRequestDispatcher("index.jsp").include(request, response);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

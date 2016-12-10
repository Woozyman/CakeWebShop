/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.ShopItem;
import models.ShopItemMapper;

/**
 *
 * @author freyb
 */
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
        String page = null;
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

            response.sendRedirect("/home.jsp");
        } else if (action.equals("updateitem")) {

            String name = (String) request.getParameter("itemName");
            String desc = (String) request.getParameter("itemDescription");
            String pic = (String) request.getParameter("itemPicture");
            Double price = Double.parseDouble(request.getParameter("itemPrice"));
            Date date = (Date) request.getAttribute("discontinuedDate");   

            item.setItemName(name);
            item.setItemDescription(desc);
            item.setItemPicture(pic);
            item.setItemPrice(price);
            if (date != null) {
                 item.setDiscontinuedDate(date);
            }                 
            sim.updateItem(item, id);
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

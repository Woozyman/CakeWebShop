/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import models.ShopItem;
import models.ShopItemMapper;

/**
 *
 * @author freyb
 */
@WebServlet(name = "RouteController", urlPatterns = {"/", "/CakeWebShop/RouteController"})
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

            HttpSession session = request.getSession();

            session.setAttribute("cakeList", si);
        }

        request.getRequestDispatcher("/mainbody.jsp").include(request, response);
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
            response.getWriter().print("Test");
        String action = request.getParameter("action");
        String page = null;
        if (action.equals("register")) {
            page = "/Register.jsp";
        } else if (action.equals("edit")) {
            page = "/ShopItemController";           
        } else if (action.equals("create")) {
            page = "/ShopItemController";            
        }
            request.getRequestDispatcher(page).forward(request, response);
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

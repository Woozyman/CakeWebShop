/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import dataaccess.DB;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author freyb
 */
public class OrderMapper {

    private static DB db;

    public OrderMapper() {
        this.db = new DB();
    }

    public static void createOrder(Order order) {
        try {
            int userId = order.getUserId();
            int orderStatus = order.getOrderStatus();
            Date orderDate = order.getOrderDate();
            Date orderDeliveryDate = order.getOrderDeliveryDate();

            String query = "INSERT INTO orders (userId, orderStatus, orderDate, orderDeliveryDate)"
                    + "VALUES(?,?,?,?)";

            PreparedStatement ps = db.getConnection().prepareStatement(query);
            ps.setInt(1, userId);
            ps.setInt(2, orderStatus);
            ps.setDate(3, orderDate);
            ps.setDate(4, orderDeliveryDate);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Order order = new Order();

        order.setUserId(2);
        order.setOrderStatus(0);
        
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date orderDate = new Date(formatter.parse("05-12-2016").getTime());
            order.setOrderDate(orderDate);
            Date orderDeliveryDate = new Date(formatter.parse("07-12-2016").getTime());
            order.setOrderDeliveryDate(orderDeliveryDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        createOrder(order);
    }

}

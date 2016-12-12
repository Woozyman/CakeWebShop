/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import dataaccess.DB_local;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author freyb
 */
public class OrderMapper {

    private static DB_local db;

    public OrderMapper() {
        this.db = new DB_local();
    }

    public static void createOrder(Order order) {
        try {
            int userId = order.getUserId();
            int orderStatus = order.getOrderStatus();
            Date orderDate = order.getOrderDate();
            Date orderDeliveryDate = order.getOrderDeliveryDate();

            String query = "INSERT INTO orders (userId, orderStatus, orderDate, orderDeliveryDate)"
                    + "VALUES(?,?,?,?)"; //The integers below corresponds to these '?' parameters.

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

    public static void updateOrderStatus(Order order, int status) {

        order.setOrderStatus(status);

        try {
            String query = "SET orderStatus=1 WHERE orderId = ?";

            PreparedStatement ps = db.getConnection().prepareStatement(query);
            ps.setInt(1, order.getOrderId());
            
            ps.executeUpdate();
           

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean existingOrder(int orderId) {
        try {
            String query = "SELECT orderId FROM orders WHERE orderId = ?";

            PreparedStatement ps = db.getConnection().prepareStatement(query);
            
            ps.setInt(1, orderId);
            
            ResultSet rs = ps.executeQuery();
          
            
            if (!rs.next()) {
                System.out.println("No  Elements in resultset!");
                return false;
            }else{
                System.out.println("OrderId: " + orderId + " Found!");
                return true;
            }
                     
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
//        Order order = new Order();
//
//        order.setUserId(2);
//        order.setOrderStatus(1);
//
//        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
//        try {
//            Date orderDate = new Date(formatter.parse("05-12-2016").getTime());
//            order.setOrderDate(orderDate);
//            Date orderDeliveryDate = new Date(formatter.parse("07-12-2016").getTime());
//            order.setOrderDeliveryDate(orderDeliveryDate);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        createOrder(order);

        System.out.println(existingOrder(1));

    }

}

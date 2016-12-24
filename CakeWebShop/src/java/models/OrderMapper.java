package models;

import dataaccess.DB_local;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public class OrderMapper {

    private static DB_local db;

    public OrderMapper() {
        OrderMapper.db = new DB_local();
    }

    public void createOrder(Order order) {
        try {
            int userId = order.getUserId();
            Date orderDate = order.getOrderDate();
            Date orderDeliveryDate = order.getOrderDeliveryDate();
            int orderInShoppingCart = order.getOrderInShoppingCart();

            String query = "INSERT INTO orders (userId, orderDate, orderDeliveryDate, orderInShoppingCart)"
                    + "VALUES(?,?,?,?)"; //The integers below corresponds to these '?' parameters.

            PreparedStatement ps = db.getConnection().prepareStatement(query);
            ps.setInt(1, userId);
            ps.setDate(2, orderDate);
            ps.setDate(3, orderDeliveryDate);
            ps.setInt(4, orderInShoppingCart);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.getMessage();
        }
        DB_local.closeConnection();
    }

    public void completeOrder(int orderId) throws ParseException {
        Time time = new Time();
        
        try {
            String query = "UPDATE orders SET orderInShoppingCart=0, orderDate = ?  WHERE orderid = ?";

            PreparedStatement ps = db.getConnection().prepareStatement(query);
            ps.setInt(2, orderId);
            ps.setDate(1, time.getSqlDateNow(time.getTimeNow()));

            ps.executeUpdate();

        } catch (SQLException e) {
            e.getMessage();
        }
    }
    
    public void setOrderDeliveryDate(int orderId, String date) throws ParseException{
         Time time = new Time();
        
        try {
            String query = "UPDATE orders SET orderDeliveryDate=? WHERE orderid = ?";

            PreparedStatement ps = db.getConnection().prepareStatement(query);           
            ps.setDate(1, time.getSqlDateNow(time.getTime(date)));

            ps.executeUpdate();

        } catch (SQLException e) {
            e.getMessage();
        }
    }
    
     public void setOrderCompletedDate(int orderId, String date) throws ParseException{
         Time time = new Time();
        
        try {
            String query = "UPDATE orders SET orderCakeCompletedDate=? WHERE orderid = ?";

            PreparedStatement ps = db.getConnection().prepareStatement(query);           
            ps.setDate(1, time.getSqlDateNow(time.getTime(date)));

            ps.executeUpdate();

        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public boolean existingOrder(int orderId) {
        try {
            String query = "SELECT orderId FROM orders WHERE orderId = ?";

            PreparedStatement ps = db.getConnection().prepareStatement(query);

            ps.setInt(1, orderId);

            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                System.out.println("No  Elements in resultset!");
                return false;
            } else {
                System.out.println("OrderId: " + orderId + " Found!");
                return true;
            }

        } catch (SQLException e) {
        }
        return false;
    }

    public Order getOrder(int id) {

        Order result = new Order();
        try {
            String query = "SELECT * FROM orders WHERE orderid = ?";
            PreparedStatement ps = db.getConnection().prepareStatement(query);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            rs.next();
            int orderId = rs.getInt("orderid");
            int userId = rs.getInt("userid");
            Date orderDate = rs.getDate("orderDate");
            Date orderCakeCompletedDate = rs.getDate("orderCakeCompletedDate");
            Date orderDeliveryDate = rs.getDate("orderDeliveryDate");
            int orderInShoppingCart = rs.getInt("orderInShoppingCart");
           
                result.setOrderId(orderId);
                result.setUserId(userId);
                result.setOrderDate(orderDate);
                result.setOrderCakeCompletedDate(orderCakeCompletedDate);
                result.setOrderDeliveryDate(orderDeliveryDate);
                result.setOrderInShoppingCart(orderInShoppingCart);
          
        } catch (SQLException e) {
            e.getMessage();
        }
        return result;
    }
    
    public boolean isOrderCompleted(int orderId) throws SQLException{
        //If query return a result... order is incomplete.
        String query = "SELECT * FROM orderlines WHERE orderid = ? AND orderLineProduced = 0";
        
        PreparedStatement ps = db.getConnection().prepareStatement(query); 
        ps.setInt(1, orderId);
        
        ResultSet rs = ps.executeQuery();
        
        if(rs.next())  {
            return false;
        }
        return true;
    }

}

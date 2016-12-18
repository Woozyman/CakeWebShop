package models;

import dataaccess.DB_local;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderLineMapper {

    private DB_local db;

    public OrderLineMapper() {
        this.db = new DB_local();
    }

    public void addOrderLine(OrderLine orderLine) {       

            try {
                String query = "INSERT into orderlines (orderid, shopItemid, numberOfItems, itemPrice) VALUES (?,?,?,?)";

                PreparedStatement ps = db.getConnection().prepareStatement(query);
                ps.setInt(1, orderLine.getOrderId());
                ps.setInt(2, orderLine.getShopItemId());
                ps.setInt(3, orderLine.getNumberOfItems());
                ps.setDouble(4, orderLine.getItemPrice());

                ps.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }

            db.closeConnection();        

    }

    public List<OrderLine> getOrderLines(int orderId) {
        List<OrderLine> orderLines = new ArrayList();

        try {
            String query = "SELECT orderid, shopItemid, numberOfItems, itemPrice FROM orderlines WHERE orderid = ?";
            PreparedStatement ps = db.getConnection().prepareStatement(query);
            ps.setInt(1, orderId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int orderIdNum = rs.getInt("orderid");
                int shopItemId = rs.getInt("shopItemid");
                int numberOfItems = rs.getInt("numberOfItems");
                double itemPrice = rs.getDouble("itemPrice");

                OrderLine orderLine = new OrderLine(orderIdNum, shopItemId, numberOfItems, itemPrice);
                orderLines.add(orderLine);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.closeConnection();
        return orderLines;
    }

    public double getCurrentPrice(int shopItemId) {

        ShopItemMapper sim = new ShopItemMapper();

        return sim.getItem(shopItemId).getItemPrice();
    }

    public void removeOrderLine(int lineId) {

        String query = "DELETE from orderLines WHERE orderLineid = ?";

        try {
            PreparedStatement ps = db.getConnection().prepareStatement(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        db.closeConnection();
    }

    public boolean itemAlreadyOnOrder(int itemId) {

        String query = "SELECT shopItemid FROM orderlines WHERE shopItemid = ?";

        try {
            PreparedStatement ps = db.getConnection().prepareStatement(query);
            ps.setInt(1, itemId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        db.closeConnection();
        return false;
    }

    public void updateOrderLine(int itemId, int numOfItems,int orderId) {
        
        String query = "UPDATE orderlines SET numberOfItems = ? WHERE shopItemid = ?";
        int itemsToAdd = getItemCount(itemId, orderId);
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(query);
            ps.setInt(1, numOfItems + itemsToAdd);
            ps.setInt(2, itemId);
            
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.closeConnection();
    }
    
    public int getItemCount(int itemId, int orderId){
        String query = "SELECT numberOfItems FROM orderlines WHERE shopItemid = ? and orderid = ?";
        int result = -1;
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(query);
            ps.setInt(1, itemId);
            ps.setInt(2, orderId);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                result = rs.getInt("numberOfItems");
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}

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
    
    public void addOrderLine(OrderLine orderLine, int orderId){
        
        try {
            String query = "INSERT into orderlines orderid, shopItemid, numberOfItems, itemPrice"
                + "VALUES (?,?,?,?)";
            
            PreparedStatement ps = db.getConnection().prepareStatement(query);
            ps.setInt(1, orderId);
            ps.setInt(2, orderLine.getShopItemId());
            ps.setInt(3, orderLine.getNumberOfItems());
            ps.setDouble(4, orderLine.getItemPrice());
            
            ps.executeUpdate();
           
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<OrderLine> getOrderLines(int orderId) {
        List<OrderLine> orderLines = new ArrayList();

        try {
            String query = "SELECT orderid, shopItemid, numberOfItems, itemPrice FROM orderlines"
                    + "WHERE orderid = ? ";
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

        return orderLines;
    }
}

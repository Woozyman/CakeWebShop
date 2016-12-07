/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import dataaccess.DB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author freyb
 */
public class OrderLineMapper {

    private DB db;

    public OrderLineMapper() {
        this.db = new DB();
    }
    
    public void addOrderLine(OrderLine orderLine, int orderId){
        
        try {
            String query = "INSERT into orderlines orderid, shopItemid, itemPrice"
                + "VALUES (?,?,?)";
            
            PreparedStatement ps = db.getConnection().prepareStatement(query);
            ps.setInt(1, orderId);
            ps.setInt(2, orderLine.getShopItemId());
            ps.setDouble(3, orderLine.getItemPrice());
            
            ps.executeUpdate();
           
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<OrderLine> getOrderLines(int orderId) {
        List<OrderLine> orderLines = new ArrayList();

        try {
            String query = "SELECT orderid, shopItemid, itemPrice FROM orderlines"
                    + "WHERE orderid = ? ";
            PreparedStatement ps = db.getConnection().prepareStatement(query);
            ps.setInt(1, orderId);

            ResultSet rs = ps.executeQuery();
           

            while (rs.next()) {
                int orderIdNum = rs.getInt("orderid");
                int shopItemId = rs.getInt("shopItemid");
                double itemPrice = rs.getDouble("itemPrice");

                OrderLine orderLine = new OrderLine(orderIdNum, shopItemId, itemPrice);
                orderLines.add(orderLine);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderLines;
    }

}

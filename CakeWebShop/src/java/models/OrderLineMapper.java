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

    public List<OrderLine> getOrderLines(int orderId) {
        List<OrderLine> orderLines = new ArrayList();

        try {
            String query = "SELECT orderid, shopItemid, itemPrice FROM orderlines"
                    + "WHERE orderid = ? ";
            PreparedStatement ps = db.getConnection().prepareStatement(query);
            ps.setInt(1, orderId);

            ResultSet rs = ps.executeQuery();
            db.closeConnection();

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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import dataaccess.DB;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author freyb
 */
public class ShopItemMapper {

    private DB db;

    public ShopItemMapper() {
        this.db = new DB();
    }

    public void addItem(ShopItem item) {
        try {
            String name = item.getItemName();
            String picture = item.getItemPicture();
            double price = item.getItemPrice();

            String query = "INSERT INTO shopitems(name, picture, price)"
                    + "VALUES(?,?,?)";

            PreparedStatement ps = db.getConnection().prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, picture);
            ps.setDouble(3, price);

            ps.execute();
            db.closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setDiscontinuedDate(ShopItem item, String date) {
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date discontinuedDate = new Date(formatter.parse(date).getTime());
            item.setDiscontinuedDate(discontinuedDate);

            try {
                String query = "SET discontinueDate=? WHERE itemid = ? ";

                PreparedStatement ps = db.getConnection().prepareStatement(query);
                ps.setDate(1, discontinuedDate);
                ps.setInt(2, item.getItemId());

                int result = ps.executeUpdate();
                db.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void setDiscontinuedDateNull(ShopItem item) {
        try {
            String query = "SET discontinueDate=null WHERE itemid = ? ";

            PreparedStatement ps = db.getConnection().prepareStatement(query);
            ps.setInt(1, item.getItemId());

            int result = ps.executeUpdate();
            db.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ShopItem getItem(int id) {
        ShopItem item = null;

        String query = "SELECT itemName, itemPicture, itemPrice, itemDescription, discontinuedDate FROM shopitems "
                + "WHERE itemid = ? ";

        try {
            PreparedStatement ps = db.getConnection().prepareStatement(query);
            ps.setInt(1, id);

            boolean result = ps.execute(); //Returns true if result is a ResultSet and false if no result
            db.closeConnection();

            if (result) {
                ResultSet rs = ps.getResultSet();
                item = new ShopItem(rs.getString("itemName"), rs.getString("itemPicture"),
                        rs.getString("itemDescription"), rs.getDouble("itemPrice"), rs.getDate("discontinuedDate"));
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    public List<ShopItem> getAllItems() {
        List<ShopItem> shopItems = new ArrayList();

        try {
            String query = "SELECT itemName, itemPicture, itemDescription, itemPrice, discontinuedDate FROM shopitems";
            PreparedStatement ps = db.getConnection().prepareStatement(query);

            ResultSet rs = ps.executeQuery();
            db.closeConnection();

            while (rs.next()) {
                String name = rs.getString("itemName");
                String picture = rs.getString("itemPicture");
                String description = rs.getString("itemDescription");
                double price = rs.getDouble("itemPrice");
                Date discontinued = rs.getDate("discontinuedDate");

                ShopItem item = new ShopItem(name, picture, description, price, discontinued);
                shopItems.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return shopItems;
    }

}

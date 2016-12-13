package models;

import dataaccess.DB_local;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShopItemMapper {

    private DB_local db;
    private Cart cart;

    public ShopItemMapper() {
        this.db = new DB_local();
        this.cart = new Cart();
    }

    public void addItem(ShopItem item) {
        try {
            String name = item.getItemName();
            String picture = item.getItemPicture();
            double price = item.getItemPrice();

            String query = "INSERT INTO shopItems(name, picture, price)"
                    + "VALUES(?,?,?)";

            PreparedStatement ps = db.getConnection().prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, picture);
            ps.setDouble(3, price);

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateItem(ShopItem item, int id) {
        String name = item.getItemName();
        String descrip = item.getItemDescription();
        String pic = item.getItemPicture();
        Double price = item.getItemPrice();
        Date disDate = item.getDiscontinuedDate();

        try {
            String query = "UPDATE shopItems SET itemName=?, itemDescription=?, itemPicture=?, "
                    + "itemPrice=?, discontinuedDate=? WHERE itemid=? ";

            PreparedStatement ps = db.getConnection().prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, descrip);
            ps.setString(3, pic);
            ps.setDouble(4, price);
            ps.setDate(5, disDate);
            ps.setInt(6, id);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setDiscontinuedDate(ShopItem item, String date) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date discontinuedDate = new Date(formatter.parse(date).getTime());
            item.setDiscontinuedDate(date);

            try {
                String query = "SET discontinueDate=? WHERE itemid = ? ";

                PreparedStatement ps = db.getConnection().prepareStatement(query);
                ps.setDate(1, discontinuedDate);
                ps.setInt(2, item.getItemId());

                int result = ps.executeUpdate();

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

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ShopItem getItem(int id) {
        ShopItem item = null;

        String query = "SELECT itemid, itemName, itemPicture, itemPrice, itemDescription, discontinuedDate FROM shopItems "
                + "WHERE itemid = ? ";

        try {
            PreparedStatement ps = db.getConnection().prepareStatement(query);
            ps.setInt(1, id);

            boolean result = ps.execute(); //Returns true if result is a ResultSet and false if no result

            if (result) {
                ResultSet rs = ps.getResultSet();
                rs.next();
                item = new ShopItem(rs.getInt("itemid"), rs.getString("itemName"), rs.getString("itemPicture"),
                        rs.getString("itemDescription"), rs.getDouble("itemPrice"), rs.getString("discontinuedDate"));
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
            String query = "SELECT itemid, itemName, itemPicture, itemDescription, itemPrice, discontinuedDate FROM shopItems";
            PreparedStatement ps = db.getConnection().prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int itemid = rs.getInt("itemid");
                String name = rs.getString("itemName");
                String picture = rs.getString("itemPicture");
                String description = rs.getString("itemDescription");
                double price = rs.getDouble("itemPrice");
                String discontinued = null;
                try {
                    discontinued = rs.getDate("discontinuedDate").toString();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                } finally {

                }
                ShopItem item = new ShopItem(itemid, name, picture, description, price, discontinued);
                shopItems.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return shopItems;
    }

    
    
    
}

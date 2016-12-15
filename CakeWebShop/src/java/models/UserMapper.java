package models;

import dataaccess.DB_local;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserMapper {

    private DB_local db;

    public UserMapper() {
        this.db = new DB_local();
    }

    public void createUser(User user) {
        try {
            String firstname = user.getFirstname();
            String lastname = user.getLastname();
            String email = user.getEmail();
            String phone = user.getPhone();
            String address = user.getAddress();
            String zip = user.getZip();
            String password = user.getPassword();
            String salt = user.getSalt();

            String query = "INSERT INTO users (firstname, lastname, email, phone, address, zip, password, salt)"
                    + "VALUES (?,?,?,?,?,?,?,?)";

            PreparedStatement ps = db.getConnection().prepareStatement(query);
            ps.setString(1, firstname);
            ps.setString(2, lastname);
            ps.setString(3, email);
            ps.setString(4, phone);
            ps.setString(5, address);
            ps.setString(6, zip);
            ps.setString(7, password);
            ps.setString(8, salt);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserByEmail(String email) {
        User user = null;
        try {
            String query = "SELECT firstname, lastname, email, phone, address, zip, password FROM users WHERE email = ?";
            PreparedStatement ps = DB_local.getConnection().prepareStatement(query);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String emailAddr = rs.getString("email");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                String zip = rs.getString("zip");
                String password = rs.getString("password");

                user = new User(firstname, lastname, emailAddr, phone, address, zip, password);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }

    public int getUserId(String email) {
        int result = 0;
        try {
            String query = "SELECT userid FROM users WHERE email = ?";

            PreparedStatement ps = db.getConnection().prepareStatement(query);
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            rs.next();
            while (rs.next()) {
                result = rs.getInt("userid");
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserMapper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList();
        try {
            String query = "SELECT firstname, lastname, email, phone, address, zip, password FROM users";
            PreparedStatement ps = DB_local.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                String zip = rs.getString("zip");
                String password = rs.getString("password");
                User user = new User(firstname, lastname, email, phone, address, zip, password);
                users.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return users;
    }

    public boolean authenticateUser(String email, String password) {
        try {
            String query = "SELECT email, password, salt FROM users WHERE email = ?";
            PreparedStatement ps = DB_local.getConnection().prepareStatement(query);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                
                //HER SKAL LAVES TJEK PÅ SALTET PASSWORD
                //Hashes(rs.getString("password")+rs.getString("salt"))
                //smides i en variabel som skal være .equals(password)+salt
                // hvis det giver mening
                if (rs.getString("password").equals(password)) {
                    return true;
                }
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return false;
    }

    public int getUnpaidOrderId(User user) {
        int itemsInCart = -1;
        int orderId = -1;
        try {
            //Find Order not yet paid.
            String query = "SELECT orderInShoppingCart, orderid FROM orders WHERE orderInShoppingCart = 1 and userid = ?";

            PreparedStatement ps = db.getConnection().prepareStatement(query);
            ps.setInt(1, this.getUserId(user.getEmail()));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                itemsInCart = rs.getInt("orderInShoppingCart");
                orderId = rs.getInt("orderid");

            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserMapper.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (itemsInCart == 1) {
            return orderId;
        }

        return -1;
    }

    public Cart getCart(int userId, int orderId) {

        List<ShopItem> itemsInCart = new ArrayList();
        List<OrderLine> orderLines = new ArrayList();
        ShopItemMapper sim = new ShopItemMapper();
        OrderLineMapper orm = new OrderLineMapper();
        
        //Get all orderlines to populate cart.
        orderLines = orm.getOrderLines(orderId);       
        
        //Insert Items in Cart
        for(OrderLine lineItem : orderLines){            
            itemsInCart.add(sim.getItem(lineItem.getShopItemId()));
        }        
           // Skal måske Slettes.... 
//        String query = "SELECT shopItemid, numberOfItems FROM orderLines WHERE orderId = ? ";
//        int id;
//        int numItems;
//        try {
//
//            PreparedStatement ps = db.getConnection().prepareStatement(query);
//            ps.setInt(1, orderId);
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//                id = rs.getInt("shopItemid");
//                numItems = rs.getInt("numberOfItems");
//
//                ShopItem item = sim.getItem(id);
//                itemsInCart.add(item);
//
//            }
//
//        } catch (SQLException ex) {
//            Logger.getLogger(ShopItemMapper.class.getName()).log(Level.SEVERE, null, ex);
//        }

        return new Cart(itemsInCart, orderLines);
    }

}

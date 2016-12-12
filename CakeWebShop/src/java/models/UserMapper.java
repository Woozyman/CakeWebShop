package models;

import dataaccess.DB_local;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

public class UserMapper {

    private static DB_local db;

    public UserMapper() {
        this.db = new DB_local();
    }

    public static void createUser(User user) {
        try {
            String firstname = user.getFirstname();
            String lastname = user.getLastname();
            String email = user.getEmail();
            String phone = user.getPhone();
            String address = user.getAddress();
            String zip = user.getZip();
            String password = user.getPassword();

            String query = "INSERT INTO users (firstname, lastname, email, phone, address, zip, password)"
                    + "VALUES (?,?,?,?,?,?,?)";

            PreparedStatement ps = db.getConnection().prepareStatement(query);
            ps.setString(1, firstname);
            ps.setString(2, lastname);
            ps.setString(3, email);
            ps.setString(4, phone);
            ps.setString(5, address);
            ps.setString(6, zip);
            ps.setString(7, password);

            ps.executeUpdate();
          

        } catch (SQLException e) {
           e.printStackTrace();
        }
    }

    public static User getUserByEmail(String email) {
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

    public static List<User> getAllUsers() {
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

    public static boolean authenticateUser(String email, String password) {
        try {
            String query = "SELECT email, password FROM users WHERE email = ?";
            PreparedStatement ps = DB_local.getConnection().prepareStatement(query);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
        
            if (rs.next()) {
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

    public static void main(String[] args) { // right click and select "run file" to insert data
       
      
    }
}

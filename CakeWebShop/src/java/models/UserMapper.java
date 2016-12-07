/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import dataaccess.DB;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author Michael
 */
public class UserMapper {

    private static DB db;

    public UserMapper() {
        this.db = new DB();
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
            db.closeConnection();

        } catch (SQLException e) {
           e.printStackTrace();
        }
    }

    public static User getUserById(int id) {
        User user = null;
        try {
            String query = "SELECT userid, firstname, lastname, email, phone, address, zip, password FROM users WHERE userid = ?";
            PreparedStatement ps = DB.getConnection().prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            db.closeConnection();
            if (rs.next()) {
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                String zip = rs.getString("zip");
                String password = rs.getString("password");

                user = new User(firstname, lastname, email, phone, address, zip, password);
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
            PreparedStatement ps = DB.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            db.closeConnection();
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
            PreparedStatement ps = DB.getConnection().prepareStatement(query);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
          //  db.closeConnection();
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
        User user = new User();

//        user.setFirstname("Frey");
//        user.setLastname("Clante");
//        user.setEmail("fclante@gmail.com");
//        user.setPhone("50565150");
//        user.setAddress("Amagerfælledvej 47");
//        user.setZip("2300");
//        user.setPassword("pass123");
//
//        createUser(user);
        user = getUserById(1);

        System.out.println("Printing single User: ");
        System.out.println(user.getFirstname());
        System.out.println("");

        List<User> users = getAllUsers();
        System.out.println("");
        System.out.println("Printing all Users: ");
        System.out.println("");

        users.forEach(item -> {
            System.out.println(item.getFirstname());
            System.out.println(item.getLastname());
            System.out.println(item.getEmail());
            System.out.println("");
        });
        
        System.out.println("Attempting to authenticate User");
        System.out.println("");
        
        boolean result = authenticateUser(user.getEmail(), user.getPassword());
        
        if (result) {
            System.out.println("User Authenticated!");
        }
    }
}

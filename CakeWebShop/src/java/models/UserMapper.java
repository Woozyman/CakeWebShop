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
    
    public UserMapper(){
        this.db = new DB();
    }
            
    public static void createUser(User user){
        try {           
            String firstname = user.getFirstname();
            String lastname = user.getLastname();
            String email = user.getEmail();
            String phone = user.getPhone();
            String address = user.getAddress();
            String zip = user.getZip();
            String password = user.getPassword();         
            
            String query = "INSERT INTO users (firstname, lastname, email, phone, address, zip, password)" + 
                            "VALUES (?,?,?,?,?,?,?)";
            
            PreparedStatement ps = db.getConnection().prepareStatement(query); 
            ps.setString(1, firstname);
            ps.setString(2, lastname);
            ps.setString(3, email);
            ps.setString(4, phone);
            ps.setString(5, address);
            ps.setString(6, zip);
            ps.setString(7, password);
            
            int result = ps.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public User getUserById(int id){
        return null;
    }
    
    public List<User> getAllUsers(){
        return null;
    }
    
    public static void main(String[] args){
        User user = new User();
        
        user.setFirstname("Jens");
        user.setLastname("Kolby");
        user.setEmail("tjkolby@hotmail.com");
        user.setPhone("56869563");
        user.setAddress("NoNameStreet 45");
        user.setZip("2365");
        user.setPassword("password123");
        
        createUser(user);
    }
}

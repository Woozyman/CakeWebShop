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
            
            ps.executeUpdate();
            
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
    
    public static void main(String[] args){ // right click and select "run file" to insert data
        User user = new User();
        
        user.setFirstname("Frey");
        user.setLastname("Clante");
        user.setEmail("fclante@gmail.com");
        user.setPhone("50565150");
        user.setAddress("Amagerfælledvej 47");
        user.setZip("2300");
        user.setPassword("pass123");
        
        createUser(user);
    }
}

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

/**
 *
 * @author Michael
 */
public class UserMapper {
    private DB db = new DB();
    
    public UserMapper(){
        this.db = new DB();
    }
            
    public void createUser(User user){
        try {           
            String firstname = user.getFirstname();
            String lastname = user.getLastname();
            String email = user.getEmail();
            String phone = user.getPhone();
            String address = user.getAddress();
            String zip = user.getZip();
            String password = user.getPassword();         
            
            String query = "INSERT INTO users (firstname, lastname, email, phone, address, zip, password" + 
                            "VALUES (?,?,?,?,?,?)";
            
            PreparedStatement ps = db.getConnection().prepareStatement(query); 
            ps.setString(1, firstname);
            ps.setString(2, lastname);
            ps.setString(3, email);
            ps.setString(4, phone);
            ps.setString(5, address);
            ps.setString(6, zip);
            ps.setString(7, password);
            
            ps.executeUpdate();
            
        } catch (Exception e) {
        }
    }
    
    public User getUserById(int id){
        return null;
    }
    
    public List<User> getAllUsers(){
        return null;
    }
}

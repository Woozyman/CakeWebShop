/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import dataaccess.DB;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author freyb
 */
public class ShopItemMapper {
    private DB db;
    
    public ShopItemMapper(){
        this.db = new DB();
    }
    
    public void addItem(ShopItem item){
        
    }
    
    public boolean removeItem(int id){
        return false;
    }
    
    //TODO Implement the rest
    public ShopItem getItem(int id){
        ShopItem item = new ShopItem();
        
        String query = "";      
        
        try {
             PreparedStatement ps = db.getConnection().prepareStatement(query);
             
            boolean result = ps.execute();
            db.closeConnection();
        } catch (SQLException e) {
            
        }
        
        return item;
    }
    
    //TODO Implement this
    public List<ShopItem> getAllItems(){
        List<ShopItem> items = new ArrayList();
        
        String query = "";
        
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(query);
            
            ps.executeQuery();
            db.closeConnection();
                    
        } catch (SQLException e) {
        }
        
        return items;
    }
    
}

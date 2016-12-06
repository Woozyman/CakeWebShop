/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import dataaccess.DB;

/**
 *
 * @author freyb
 */
public class OrderLineMapper {
    private DB db;
    
    public OrderLineMapper(){
        this.db = new DB();
    }
    
}

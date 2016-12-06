/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;

/**
 *
 * @author freyb
 */
public class Order {
    
    private int userId;
    private int orderStatus;
    private Date orderDate;
    private Date orderDeliveryDate;
    
    public Order(){}
    
    public Order(int userId, int orderStatus, Date orderDate, Date orderDeliveryDate){
        this.userId = userId;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
        this.orderDeliveryDate = orderDeliveryDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getOrderDeliveryDate() {
        return orderDeliveryDate;
    }

    public void setOrderDeliveryDate(Date orderDeliveryDate) {
        this.orderDeliveryDate = orderDeliveryDate;
    }
    
    
    
}

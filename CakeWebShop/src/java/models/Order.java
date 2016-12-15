package models;

import java.sql.Date;

public class Order {

    private int orderId;
    private int userId;
    private Date orderDate;
    private Date orderCakeCompletedDate;
    private Date orderDeliveryDate;
    private int orderInShoppingCart;

    public Order() {
    }

    public Order(int userId, Date orderDate, Date orderDeliveryDate, int inCart) {
        this.userId = userId;
        this.orderDate = orderDate;
        this.orderDeliveryDate = orderDeliveryDate;
        this.orderInShoppingCart = inCart;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int id) {
        this.orderId = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public int getOrderInShoppingCart() {
        return orderInShoppingCart;
    }

    public void setOrderInShoppingCart(int orderInShoppingCart) {
        this.orderInShoppingCart = orderInShoppingCart;
    }

    public Date getOrderCakeCompletedDate() {
        return orderCakeCompletedDate;
    }

    public void setOrderCakeCompletedDate(Date orderCakeCompletedDate) {
        this.orderCakeCompletedDate = orderCakeCompletedDate;
    }

}

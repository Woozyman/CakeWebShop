package models;

public class OrderLine {

    private int orderLineId;    //primary key , not in the constructor because mysql assigns id automatically    
    private int orderId;        //foreign key
    private int numberOfItems;
    private int shopItemId;     //foreign key    
    private double itemPrice;
    private int orderLineProduced = 0;

    public int getOrderLineProduced() {
        return orderLineProduced;
    }

    public void setOrderLineProduced(int orderLineProduced) {
        this.orderLineProduced = orderLineProduced;
    }
    
    public OrderLine(){}
    
    public OrderLine(int orderId, int itemId, int numberOfItems, double itemPrice){
       this.orderId = orderId;
       this.shopItemId = itemId;
       this.numberOfItems = numberOfItems;
       this.itemPrice = itemPrice;
    }
    //for baker to mark orders as baked.
    public OrderLine(int orderLineId, int orderId, int itemId, int numberOfItems, double itemPrice){
       this.orderLineId = orderLineId;
       this.orderId = orderId;
       this.shopItemId = itemId;
       this.numberOfItems = numberOfItems;
       this.itemPrice = itemPrice;
    }

    public int getOrderLineId() {
        return orderLineId;
    }  
    
    public void setOrderLineId(int orderLineId) {
        this.orderLineId = orderLineId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }
    
    public void setNumberOfItems(int numberOfItems){
        this.numberOfItems = numberOfItems;
    }
    
    public int getShopItemId() {
        return shopItemId;
    }

    public void setShopItemId(int shopItemId) {
        this.shopItemId = shopItemId;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

}

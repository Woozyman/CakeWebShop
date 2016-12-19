package models;

public class OrderLine {

    private int orderLineId;    //primary key , not in the constructor because mysql assigns id automatically
    private int orderId;        //foreign key
    private int numberOfItems;
    private int shopItemId;     //foreign key    
    private double itemPrice;
    
    public OrderLine(){}
    
    public OrderLine(int orderId, int itemId, int numberOfItems, double itemPrice){
       this.orderId = orderId;
       this.shopItemId = itemId;
       this.numberOfItems = numberOfItems;
       this.itemPrice = itemPrice;
    }

    public int getOrderLineId() {
        return orderLineId;
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

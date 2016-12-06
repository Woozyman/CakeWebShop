/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author freyb
 */
public class ShopItem {
    
    private int itemId;
    private String itemName;
    private String itemPicture;
    private double itemPrice;
    
    public ShopItem(){}
    
    public ShopItem(String name, String picture, double price){
        this.itemName = name;
        this.itemPicture = picture;
        this.itemPrice = price;
    }

    public int getItemId() {
        return itemId;
    }
   
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPicture() {
        return itemPicture;
    }

    public void setItemPicture(String itemPicture) {
        this.itemPicture = itemPicture;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }
    
    
    
}

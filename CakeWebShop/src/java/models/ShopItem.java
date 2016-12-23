package models;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ShopItem {

    private int itemId;
    private String itemName;
    private String itemPicture;
    private String itemDescription;
    private double itemPrice;
    private String discontinuedDate = null;

    public ShopItem() {
    }

    public ShopItem(int itemid, String name, String picture, String description, double price, String discontinued) {
        this.itemId = itemid;
        this.itemName = name;
        this.itemPicture = picture;
        this.itemDescription = description;
        this.itemPrice = price;
        this.discontinuedDate = discontinued;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public Date getDiscontinuedDate() {

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date discontinuedDate = null;
        try {
            discontinuedDate = new Date(formatter.parse(this.discontinuedDate).getTime());

        } catch (ParseException e) {
            e.getMessage();
        }

        return discontinuedDate;
    }

    public void setDiscontinuedDate(String discontinuedDate) {
        this.discontinuedDate = discontinuedDate;
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

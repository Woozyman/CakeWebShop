/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private List<ShopItem> items;
    private List<OrderLine> orderLines;
    
    public Cart(){}
    
    public Cart(List<ShopItem> items, List<OrderLine> lines) {
        this.items = items;
        this.orderLines = lines;
    }

    public void addItemToCart(ShopItem item) {
        items.add(item);
    }

    public void removeItemFromCart(ShopItem item) {
        items.remove(item);
    }

    public void emptyCart() {
        items.clear();
    }

    public int getItemsCount() {
        return items.size();
    }   
    
    public List<OrderLine> getOrderLines(){
        return this.orderLines;
    }
    
    public List<ShopItem> getShopItems(){
        return this.items;
    }

}

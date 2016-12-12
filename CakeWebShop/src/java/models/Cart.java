/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author freyb
 */
public class Cart {

    private List<ShopItem> items;

    public Cart() {
        this.items = new ArrayList();
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

}

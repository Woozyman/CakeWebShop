/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private List<OrderLine> orderLines;

    public Cart() {
    }

    public Cart(List<OrderLine> lines) {
        this.orderLines = lines;
    }

    public void addItemToCart(OrderLine item) {
        orderLines.add(item);
    }

    public void removeItemFromCart(OrderLine item) {
        orderLines.remove(item);
    }

    public void emptyCart() {
        orderLines.clear();
    }

    public int getItemsCount() {
        return orderLines.size();
    }

    public List<OrderLine> getOrderLines() {
        return this.orderLines;
    }

}

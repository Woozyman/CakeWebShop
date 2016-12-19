package models;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private List<OrderLine> orderLines;

    public Cart() {
        orderLines = new ArrayList();
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

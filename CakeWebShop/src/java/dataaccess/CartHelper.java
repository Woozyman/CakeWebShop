/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.List;
import javax.servlet.http.HttpSession;
import models.Cart;
import models.OrderLine;

/**
 *
 * @author freyb
 */
public class CartHelper {    
   
    
     public int getItemCountFromSessionCart(int itemId, HttpSession session){
       
        Cart cart = (Cart) session.getAttribute("cart");
        
        List<OrderLine> orderLines = cart.getOrderLines();
        
        for(OrderLine lineItem : orderLines){
            if(lineItem.getShopItemId() == itemId){
                return lineItem.getNumberOfItems();
            }
        }
        
        return 0;
    }
    
}
package com.example.dominik.prontoshop.common;


import com.example.dominik.prontoshop.model.Customer;
import com.example.dominik.prontoshop.model.LineItem;

import java.util.List;

public interface ShoppingCartContract {

    void addItemToCart(LineItem item);
    void removeItemFromCart(LineItem item);
    void clearAllItemsFromCart();
    List<LineItem> getShoppingCart();
    void setCustomer(Customer customer);
    void updateItemQty(LineItem item, int qty);
    Customer getSelectedCustomer();
    void completeCheckout();

}

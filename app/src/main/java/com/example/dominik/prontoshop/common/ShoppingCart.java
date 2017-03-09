package com.example.dominik.prontoshop.common;

import android.content.SharedPreferences;

import com.example.dominik.prontoshop.model.Customer;
import com.example.dominik.prontoshop.model.LineItem;

import java.util.List;

public class ShoppingCart implements ShoppingCartContract{

    private List<LineItem> shoppingCart;
    private Customer selectedCustomer;
    private final SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private static final String LOG_TAG = ShoppingCart.class.getSimpleName();
    private static boolean DEBUG = true;

    public ShoppingCart(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        initShoppingCart();
    }

    private void initShoppingCart() {

    }


    @Override
    public void addItemToCart(LineItem item) {

    }

    @Override
    public void removeItemFromCart(LineItem item) {

    }

    @Override
    public void clearAllItemsFromCart() {

    }

    @Override
    public List<LineItem> getShoppingCart() {
        return null;
    }

    @Override
    public void setCustomer(Customer customer) {

    }

    @Override
    public void updateItemQty(LineItem item, int qty) {

    }

    @Override
    public Customer getSelectedCustomer() {
        return null;
    }

    @Override
    public void completeCheckout() {

    }
}

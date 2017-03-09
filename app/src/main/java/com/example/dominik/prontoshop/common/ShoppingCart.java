package com.example.dominik.prontoshop.common;

import android.content.SharedPreferences;
import android.util.Log;

import com.example.dominik.prontoshop.model.Customer;
import com.example.dominik.prontoshop.model.LineItem;
import com.example.dominik.prontoshop.util.Constants;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart implements ShoppingCartContract {

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

        shoppingCart = new ArrayList<>();
        selectedCustomer = new Customer();
        Gson gson = new Gson();

        //check if there are item saved to the Shared Preferences

    }

    public void saveCartToPreferences(){
        if(shoppingCart != null){
            Gson gson = new Gson();
            String serializedItems = gson.toJson(shoppingCart);
            if(DEBUG){
                Log.d(LOG_TAG, "Saving Serialized Cart Items: " + serializedItems);
            }
            String serializedCustomer = gson.toJson(selectedCustomer);
            if(DEBUG){
                Log.d(LOG_TAG, "Saving Customer: " + serializedCustomer);
            }
            editor.putString(Constants.SERIALIZED_CART_ITEMS, serializedItems).commit();
            editor.putString(Constants.SERIALIZED_CUSTOMER, serializedCustomer).commit();
            editor.putBoolean(Constants.OPEN_CART_EXISTS, true).commit();
        }
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

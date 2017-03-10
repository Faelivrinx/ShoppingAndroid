package com.example.dominik.prontoshop.common;

import android.content.SharedPreferences;
import android.util.Log;

import com.example.dominik.prontoshop.core.ProntoShopApplication;
import com.example.dominik.prontoshop.model.Customer;
import com.example.dominik.prontoshop.model.LineItem;
import com.example.dominik.prontoshop.util.Constants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.otto.Bus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ShoppingCart implements ShoppingCartContract {

    private List<LineItem> shoppingCart;
    private Customer selectedCustomer;
    private final SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private static final String LOG_TAG = ShoppingCart.class.getSimpleName();
    private static boolean DEBUG = true;

    @Inject
    Bus bus;

    public ShoppingCart(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        ProntoShopApplication.getInstance().getAppComponent().inject(this);
        initShoppingCart();
    }

    private void initShoppingCart() {

        shoppingCart = new ArrayList<>();
        selectedCustomer = new Customer();
        Gson gson = new Gson();

        //check if there are item saved to the Shared Preferences
        if(sharedPreferences.getBoolean(Constants.OPEN_CART_EXISTS, false)){

            String serializedItems = sharedPreferences.getString(Constants.SERIALIZED_CART_ITEMS, "");
            if(DEBUG){
                Log.d(LOG_TAG, "Serialized Cart Items: " + serializedItems);
            }
            String serializedCustomer = sharedPreferences.getString(Constants.SERIALIZED_CUSTOMER, "");
            if(DEBUG){
                Log.d(LOG_TAG, "Serialized Customer: " + serializedCustomer);
            }

            if(serializedItems.equals("")){
                shoppingCart = gson.<ArrayList<LineItem>>fromJson(serializedItems,
                                    new TypeToken<ArrayList<LineItem>>(){}.getType());
            }

            if(serializedCustomer.equals("")){
                selectedCustomer = gson.fromJson(serializedCustomer, Customer.class);
            }
        }

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
        boolean isItemInCart = false;
        int itemPosition = 0;

        for(LineItem tempItem: shoppingCart){
                if(tempItem.getId() == item.getId()){
                    itemPosition = shoppingCart.indexOf(tempItem);
                    isItemInCart = true;
                    LineItem selectedItem = shoppingCart.get(itemPosition);
                    selectedItem.setQuantity(tempItem.getQuantity() + item.getQuantity());
                    shoppingCart.set(itemPosition, selectedItem);
                    break;
                }
        }
        if(!isItemInCart){
            shoppingCart.add(item);
        }
    }

    @Override
    public void removeItemFromCart(LineItem item) {
        shoppingCart.remove(item);
    }

    @Override
    public void clearAllItemsFromCart() {
        shoppingCart.clear();
    }

    @Override
    public List<LineItem> getShoppingCart() {
        return null;
    }

    @Override
    public void setCustomer(Customer customer) {
        selectedCustomer = customer;
    }

    @Override
    public void updateItemQty(LineItem item, int qty) {
        boolean itemAlreadyInCart = false;
        int itemPosition = 0;

        for(LineItem tempItem: shoppingCart){
            if(tempItem.getId() == item.getId()){
                itemPosition = shoppingCart.indexOf(tempItem);
                LineItem itemInCart = shoppingCart.get(itemPosition);
                itemInCart.setQuantity(qty);
                shoppingCart.set(itemPosition, itemInCart);
            }
        }

        if(!itemAlreadyInCart){
            item.setQuantity(qty);
            shoppingCart.add(item);
        }
    }

    @Override
    public Customer getSelectedCustomer() {
        return selectedCustomer;
    }

    @Override
    public void completeCheckout() {
        shoppingCart.clear();
    }
}

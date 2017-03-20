package com.example.dominik.prontoshop.core;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.example.dominik.prontoshop.common.MainActivity;
import com.example.dominik.prontoshop.core.listeners.OnDatabaseOperationCompleteListener;
import com.example.dominik.prontoshop.data.SampleCustomerData;
import com.example.dominik.prontoshop.data.SampleProductData;
import com.example.dominik.prontoshop.model.Customer;
import com.example.dominik.prontoshop.model.Product;
import com.example.dominik.prontoshop.ui.customerlist.CustomerListSQLiteManager;
import com.example.dominik.prontoshop.ui.productlist.ProductListSQLiteManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dominik on 18.03.2017.
 */
public class AddInitialDataService extends IntentService {

    public AddInitialDataService() {
        super("AddInitialDataService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        //Add sample Customers to database
        List<Customer> customers = SampleCustomerData.getSampleCustomers();
        CustomerListSQLiteManager customerRepository = new CustomerListSQLiteManager(getApplicationContext());
        for (Customer customer: customers){
            customerRepository.addCustomer(customer, new OnDatabaseOperationCompleteListener() {

                        @Override
                        public void OnDatabaseOperationFailed(String error) {
                            Log.d("First Run", "Error: " + error);
                        }

                        @Override
                        public void OnDatabaseOperationSuccesed(String message) {
                            Log.d("First Run", "Success: " + message);
                        }
                    });
        }
        //Add initial products
        List<Product> products = SampleProductData.getSampleProducts();
        ProductListSQLiteManager productSQLiteRepository = new ProductListSQLiteManager(getApplicationContext());
        for (Product product : products) {
            productSQLiteRepository.addProduct(product, new OnDatabaseOperationCompleteListener() {
                @Override
                public void OnDatabaseOperationFailed(String error) {
                    Log.d("First Run", "Error: " + error);
                }

                @Override
                public void OnDatabaseOperationSuccesed(String message) {
                    Log.d("First Run", "Success: " + message);
                }
            });
        }

        //Add sample categories
        List<String> categories = new ArrayList<>();
        categories.add("Electronics");
        categories.add("Computers");
        categories.add("Toys");
        categories.add("Garden");
        categories.add("Kitchen");
        categories.add("Clothing");
        categories.add("Health");

        for (String category: categories){
            productSQLiteRepository.createOrGetCategory(category, new OnDatabaseOperationCompleteListener() {
                @Override
                public void OnDatabaseOperationFailed(String error) {
                    Log.d("First Run", "Error: " + error);
                }

                @Override
                public void OnDatabaseOperationSuccesed(String message) {
                    Log.d("First Run", "Success: " + message);
                }
            });
        }

        Intent restartIntent = new Intent(this, MainActivity.class);
        restartIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(restartIntent);


    }

}

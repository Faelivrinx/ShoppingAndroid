package com.example.dominik.prontoshop.ui.customerlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.dominik.prontoshop.core.listeners.OnDatabaseOperationCompleteListener;
import com.example.dominik.prontoshop.data.DatabaseHelper;
import com.example.dominik.prontoshop.model.Customer;
import com.example.dominik.prontoshop.util.Constants;

import java.util.ArrayList;
import java.util.List;


public class CustomerListSQLiteManager implements CustomerListContract.Repository {

    private DatabaseHelper databaseHelper;
    private Context context;
    private SQLiteDatabase database;

    public CustomerListSQLiteManager(Context context) {
        this.context = context;
        databaseHelper = DatabaseHelper.newInstance(context);
        database = databaseHelper.getWritableDatabase();
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + Constants.CUSTOMER_TABLE;

        if (database != null) {
            Cursor cursor = database.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    Customer customer = Customer.getCustomerFromCursor(cursor);
                    customers.add(customer);
                    cursor.moveToNext();
                }
            }
        }
        return customers;
    }

    @Override
    public Customer getCustomer(long id) {
        Customer customer = null;
        String selectQuery = "SELECT * FROM " + Constants.CUSTOMER_TABLE + " WHERE " + Constants.COLUMN_ID + " = '" + id + "'";

        if (database != null) {
            Cursor cursor = database.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                customer = Customer.getCustomerFromCursor(cursor);
            } else {
                customer = null;
            }
        }

        return customer;
    }

    @Override
    public void addCustomer(Customer customer, OnDatabaseOperationCompleteListener listener) {
        if (database != null) {
            ContentValues values = new ContentValues();
            values.put(Constants.COLUMN_NAME, customer.getCustomerName());
            values.put(Constants.COLUMN_EMAIL, customer.getEmailAddress());
            values.put(Constants.COLUMN_PHONE, customer.getPhoneNumber());
            values.put(Constants.COLUMN_IMAGE_PATH, customer.getProfileImagePath());
            values.put(Constants.COLUMN_STREET1, customer.getStreetAddress());
            values.put(Constants.COLUMN_STREET2, customer.getStreetAddress2());
            values.put(Constants.COLUMN_CITY, customer.getCity());
            values.put(Constants.COLUMN_STATE, customer.getState());
            values.put(Constants.COLUMN_ZIP, customer.getPostalCode());
            values.put(Constants.COLUMN_NOTE, customer.getNote());
            values.put(Constants.COLUMN_DATE_CREATED, customer.getDateAdded());
            values.put(Constants.COLUMN_LAST_UPDATED, customer.getDateOfLastTransaction());

            try {
                database.insertOrThrow(Constants.CUSTOMER_TABLE, null, values);
                listener.OnDatabaseOperationSuccesed("Added Customer");
            } catch (SQLException e) {
                listener.OnDatabaseOperationFailed("Unable to add Customer");
                e.printStackTrace();
            }
        }

    }

    @Override
    public void deleteCustomer(Customer customer, OnDatabaseOperationCompleteListener listener) {
        if (database != null) {
            int result = database.delete(Constants.CUSTOMER_TABLE, Constants.COLUMN_ID + " = " + customer.getId(), null);

            if (result > 0) {
                listener.OnDatabaseOperationSuccesed("Customer Deleted");
            } else {
                listener.OnDatabaseOperationFailed("Unable to delete Customer of id" + customer.getId());
            }
        }
    }

    @Override
    public void updateCustomer(Customer customer, OnDatabaseOperationCompleteListener listener) {
        if (database != null) {
            ContentValues values = new ContentValues();
            values.put(Constants.COLUMN_NAME, customer.getCustomerName());
            values.put(Constants.COLUMN_EMAIL, customer.getEmailAddress());
            values.put(Constants.COLUMN_PHONE, customer.getPhoneNumber());
            values.put(Constants.COLUMN_IMAGE_PATH, customer.getProfileImagePath());
            values.put(Constants.COLUMN_STREET1, customer.getStreetAddress());
            values.put(Constants.COLUMN_STREET2, customer.getStreetAddress2());
            values.put(Constants.COLUMN_CITY, customer.getCity());
            values.put(Constants.COLUMN_STATE, customer.getState());
            values.put(Constants.COLUMN_ZIP, customer.getPostalCode());
            values.put(Constants.COLUMN_NOTE, customer.getNote());
            values.put(Constants.COLUMN_DATE_CREATED, customer.getDateAdded());
            values.put(Constants.COLUMN_LAST_UPDATED, customer.getDateOfLastTransaction());

            int result = database.update(Constants.CUSTOMER_TABLE, values, Constants.COLUMN_ID + " = " + customer.getId(), null);
            if (result == 0) {
                listener.OnDatabaseOperationSuccesed("Updated Customer");
            } else {
                listener.OnDatabaseOperationFailed("Unable to update Customer of id " + customer.getId());
            }
        }
    }
}

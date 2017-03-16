package com.example.dominik.prontoshop.ui.productlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.dominik.prontoshop.core.listeners.OnDatabaseOperationCompleteListener;
import com.example.dominik.prontoshop.data.DatabaseHelper;
import com.example.dominik.prontoshop.model.Category;
import com.example.dominik.prontoshop.model.Product;
import com.example.dominik.prontoshop.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class ProductListSQLiteManager implements ProductListContract.Repository{
    private DatabaseHelper dbHelper;
    private final Context mContext;
    private SQLiteDatabase database;

    public ProductListSQLiteManager(Context context) {
        mContext = context;
        dbHelper = DatabaseHelper.newInstance(context);
        database = dbHelper.getWritableDatabase();
    }

    @Override
    public List<Product> getAllProducts() {

        List<Product> products = new ArrayList<>();

        //SQL command to select all products
        String selectQuery = "Select * FROM " + Constants.PRODUCT_TABLE;

        //make sure database not null
        if(database != null){
            //get cursor for all products in database
            Cursor cursor = database.rawQuery(selectQuery, null);
            if(cursor.moveToFirst()){
                while (!cursor.isAfterLast()){
                    products.add(Product.getProductFromCursor(cursor));
                    cursor.moveToNext();
                }

            }
        }

        return products;
    }

    @Override
    public Product getProductById(long id) {
        //get curosor  representing the PRoduct

        Cursor cursor = database.rawQuery("Select * FROM "+ Constants.PRODUCT_TABLE +
                " WHERE "+ Constants.COLUMN_ID + " = '" + id + "'", null);

        Product product = null;
        if(cursor.moveToFirst()){
            product = Product.getProductFromCursor(cursor);
        } else{
            product = null;
        }
        return product;
    }

    @Override
    public void deleteProduct(Product product, OnDatabaseOperationCompleteListener listener) {
        if(database != null){
            int result = database.delete(Constants.PRODUCT_TABLE, Constants.COLUMN_ID + " = " + product.getId(), null);

            if(result > 0){
                listener.OnDatabaseOperationSuccesed("Product Deleted");
            } else {
                listener.OnDatabaseOperationFailed("Unable to delete product");
            }
        }
    }

    @Override
    public void addProduct(Product product, OnDatabaseOperationCompleteListener listener) {
        //prepare information of product
        ContentValues values = new ContentValues();
        values.put(Constants.COLUMN_NAME, product.getProductName());
        values.put(Constants.COLUMN_DESCRIPTION, product.getDescription());
        values.put(Constants.COLUMN_PRICE, product.getSalePrice());
        values.put(Constants.COLUMN_PURCHASE_PRICE, product.getPurchasePrice());
        values.put(Constants.COLUMN_IMAGE_PATH, product.getImagePath());
        values.put(Constants.COLUMN_CATEGORY_NAME, product.getCategoryName());
        values.put(Constants.COLUMN_DATE_CREATED, System.currentTimeMillis());
        values.put(Constants.COLUMN_LAST_UPDATED, System.currentTimeMillis());

        try {
            database.insertOrThrow(Constants.PRODUCT_TABLE, null, values);
            listener.OnDatabaseOperationSuccesed("Product Added");
        } catch (SQLException e) {
            listener.OnDatabaseOperationFailed(e.getMessage());
        }

    }

    @Override
    public void updateProduct(Product product, OnDatabaseOperationCompleteListener listener) {
        ContentValues values = new ContentValues();
        values.put(Constants.COLUMN_NAME, product.getProductName());
        values.put(Constants.COLUMN_DESCRIPTION, product.getDescription());
        values.put(Constants.COLUMN_PRICE, product.getSalePrice());
        values.put(Constants.COLUMN_PURCHASE_PRICE, product.getPurchasePrice());
        values.put(Constants.COLUMN_IMAGE_PATH, product.getImagePath());
        values.put(Constants.COLUMN_CATEGORY_NAME, product.getCategoryName());
        values.put(Constants.COLUMN_DATE_CREATED,System.currentTimeMillis());
        values.put(Constants.COLUMN_LAST_UPDATED, System.currentTimeMillis());

        int result = database.update(Constants.PRODUCT_TABLE, values, Constants.COLUMN_ID + " = "+product.getId(), null);
        if(result == 1){
            listener.OnDatabaseOperationSuccesed("Product Updated");
        } else{
            listener.OnDatabaseOperationFailed("Product update failed");
        }
    }

    @Override
    public List<Category> getAllCategories() {
        return null;
    }
}
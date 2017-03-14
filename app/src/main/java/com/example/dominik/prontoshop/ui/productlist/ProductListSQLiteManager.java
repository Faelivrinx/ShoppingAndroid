package com.example.dominik.prontoshop.ui.productlist;

import android.content.Context;
import android.database.Cursor;
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
        return null;
    }

    @Override
    public void deleteProduct(Product product, OnDatabaseOperationCompleteListener listener) {

    }

    @Override
    public void addProduct(Product product, OnDatabaseOperationCompleteListener listener) {

    }

    @Override
    public void updateProduct(Product product, OnDatabaseOperationCompleteListener listener) {

    }

    @Override
    public List<Category> getAllCategories() {
        return null;
    }
}

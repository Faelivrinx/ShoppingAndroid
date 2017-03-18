package com.example.dominik.prontoshop.ui.transaction;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.dominik.prontoshop.common.ShoppingCart;
import com.example.dominik.prontoshop.core.ProntoShopApplication;
import com.example.dominik.prontoshop.core.listeners.OnDatabaseOperationCompleteListener;
import com.example.dominik.prontoshop.data.DatabaseHelper;
import com.example.dominik.prontoshop.model.LineItem;
import com.example.dominik.prontoshop.model.SalesTransaction;
import com.example.dominik.prontoshop.util.Constants;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Dominik on 18.03.2017.
 */

public class TransactionSQLiteManager implements TransactionContract.Repository {
    private static final boolean DEBUG = false;
    private static final String LOG_TAG = TransactionSQLiteManager.class.getSimpleName();
    private DatabaseHelper databaseHelper;
    private Context context;
    private SQLiteDatabase database;

    @Inject
    ShoppingCart shoppingCart;

    public TransactionSQLiteManager(Context context) {
        this.context = context;
        databaseHelper = DatabaseHelper.newInstance(context);
        database = databaseHelper.getWritableDatabase();
        ProntoShopApplication.getInstance().getAppComponent().inject(this);
    }

    @Override
    public List<SalesTransaction> getAllTransactions() {
        List<SalesTransaction> transactions = new ArrayList<>();
        if (database != null) {
            String selectQuery = "SELECT * FROM " + Constants.TRANSACTION_TABLE;
            Cursor cursor = database.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                if (!cursor.isAfterLast()) {
                    SalesTransaction transaction = SalesTransaction.getSalesTransactionFromCursor(cursor);
                    transactions.add(transaction);
                    cursor.moveToNext();
                }
                cursor.close();
            }
        }
        return transactions;
    }

    @Override
    public void updateTransaction(SalesTransaction salesTransaction, OnDatabaseOperationCompleteListener listener) {
        if (database != null) {
            ContentValues values = new ContentValues();
            values.put(Constants.COLUMN_CUSTOMER_ID, salesTransaction.getCustomerId());
            values.put(Constants.COLUMN_LINE_ITEMS, salesTransaction.getJsonLineItem());
            values.put(Constants.COLUMN_PAYMENT_STATUS, convertBoolenToInt(salesTransaction.isPaid()));
            values.put(Constants.COLUMN_PAYMENT_TYPE, salesTransaction.getPaymentType());
            values.put(Constants.COLUMN_TAX_AMOUNT, salesTransaction.getTaxAmount());
            values.put(Constants.COLUMN_SUB_TOTAL_AMOUNT, salesTransaction.getSubTotalAmount());
            values.put(Constants.COLUMN_DATE_CREATED, System.currentTimeMillis());
            values.put(Constants.COLUMN_LAST_UPDATED, System.currentTimeMillis());

            int result = database.update(Constants.TRANSACTION_TABLE, values, Constants.COLUMN_ID + " = " + salesTransaction.getId(), null);
            if (result == 1) {
                listener.OnDatabaseOperationSuccesed("Transaction updated!");
            } else {
                listener.OnDatabaseOperationFailed("Unable to update transaction");
            }
        }


    }

    private int convertBoolenToInt(boolean paid) {
        if (paid) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public SalesTransaction getTransactionById(long id) {
        SalesTransaction transaction = null;
        if (database != null) {
            String selectQuery = "SELECT * FROM " + Constants.TRANSACTION_TABLE + " WHERE " + Constants.COLUMN_ID + " = '" + id + "'";
            Cursor cursor = database.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                transaction = SalesTransaction.getSalesTransactionFromCursor(cursor);

            } else {
                transaction = null;
            }
        }
        return transaction;
    }

    @Override
    public List<LineItem> getAllLineItems() {
        return shoppingCart.getShoppingCart();
    }

    @Override
    public void saveTransaction(SalesTransaction
                                        salesTransaction, OnDatabaseOperationCompleteListener listener) {
        if (database != null) {
            ContentValues values = new ContentValues();
            values.put(Constants.COLUMN_CUSTOMER_ID, salesTransaction.getCustomerId());
            values.put(Constants.COLUMN_LINE_ITEMS, salesTransaction.getJsonLineItem());
            values.put(Constants.COLUMN_PAYMENT_STATUS, salesTransaction.isPaid());
            values.put(Constants.COLUMN_PAYMENT_TYPE, salesTransaction.getPaymentType());
            values.put(Constants.COLUMN_TAX_AMOUNT, salesTransaction.getTaxAmount());
            values.put(Constants.COLUMN_SUB_TOTAL_AMOUNT, salesTransaction.getSubTotalAmount());
            values.put(Constants.COLUMN_DATE_CREATED, System.currentTimeMillis());
            values.put(Constants.COLUMN_LAST_UPDATED, System.currentTimeMillis());

            try {
                database.insertOrThrow(Constants.TRANSACTION_TABLE, null, values);
                listener.OnDatabaseOperationSuccesed("Transaction saved!");
                if (DEBUG) {
                    Log.d(LOG_TAG, "Transactinon saved");
                }
            } catch (SQLException e) {
                listener.OnDatabaseOperationFailed(e.getCause() + " " + e.getMessage());
            }
        }
    }

    @Override
    public void deleteTransaction(long id, OnDatabaseOperationCompleteListener listener) {
        if (database != null) {
            int result = database.delete(Constants.TRANSACTION_TABLE, Constants.COLUMN_ID + " = " + id, null);
            if (result > 1) {
                listener.OnDatabaseOperationSuccesed("Transaction Deleted");
            } else {
                listener.OnDatabaseOperationFailed("Unable to delete transaction");
            }
        }
    }
}

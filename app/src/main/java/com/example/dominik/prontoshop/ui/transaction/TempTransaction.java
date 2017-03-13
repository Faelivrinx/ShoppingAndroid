package com.example.dominik.prontoshop.ui.transaction;

import com.example.dominik.prontoshop.core.listeners.OnDatabaseOperationCompleteListener;
import com.example.dominik.prontoshop.model.SalesTransaction;

import java.util.List;

public class TempTransaction implements TransactionContract.Repository {
    @Override
    public List<SalesTransaction> getAllTransactions() {
        return null;
    }

    @Override
    public void updateTransaction(SalesTransaction salesTransaction, OnDatabaseOperationCompleteListener listener) {

    }

    @Override
    public SalesTransaction getTransactionById(long id) {
        return null;
    }

    @Override
    public void deleteTransaction(long id, OnDatabaseOperationCompleteListener listener) {

    }
}

package com.example.dominik.prontoshop.ui.transaction;

import com.example.dominik.prontoshop.core.listeners.OnDatabaseOperationCompleteListener;
import com.example.dominik.prontoshop.model.Transaction;

import java.util.List;

public class TempTransaction implements TransactionContract.Repository {
    @Override
    public List<Transaction> getAllTransactions() {
        return null;
    }

    @Override
    public void updateTransaction(Transaction transaction, OnDatabaseOperationCompleteListener listener) {

    }

    @Override
    public Transaction getTransactionById(long id) {
        return null;
    }

    @Override
    public void deleteTransaction(long id, OnDatabaseOperationCompleteListener listener) {

    }
}

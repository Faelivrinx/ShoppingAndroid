package com.example.dominik.prontoshop.ui.transaction;


import com.example.dominik.prontoshop.core.listeners.OnDatabaseOperationCompleteListener;
import com.example.dominik.prontoshop.model.Customer;
import com.example.dominik.prontoshop.model.Transaction;

import java.util.List;

public interface TransactionContract {

    public interface View {
        void showTransaction(List<Transaction> transactions);
        void showEmptyText();
        void hideEmptyText();
        void confirmDeletePrompt(Transaction transaction);
        void showMessage(String message);
    }

    public interface Actions {
        void loadTransactions();
        void onDeleteItemButtonClicked(Transaction transaction);
        void deleteTransaction(Transaction transaction);
        void editTransaction(Transaction transaction);
        Customer getCustomerById(long id);
    }

    public interface Repository {
        List<Transaction>getAllTransactions();
        void updateTransaction(Transaction transaction, OnDatabaseOperationCompleteListener listener);
        Transaction getTransactionById(long id);
        void deleteTransaction(long id, OnDatabaseOperationCompleteListener listener);
    }
}

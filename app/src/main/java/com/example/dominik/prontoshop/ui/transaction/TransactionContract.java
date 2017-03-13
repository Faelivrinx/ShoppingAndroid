package com.example.dominik.prontoshop.ui.transaction;


import com.example.dominik.prontoshop.core.listeners.OnDatabaseOperationCompleteListener;
import com.example.dominik.prontoshop.model.Customer;
import com.example.dominik.prontoshop.model.SalesTransaction;

import java.util.List;

public interface TransactionContract {

    public interface View {
        void showTransaction(List<SalesTransaction> salesTransactions);
        void showEmptyText();
        void hideEmptyText();
        void confirmDeletePrompt(SalesTransaction salesTransaction);
        void showMessage(String message);
    }

    public interface Actions {
        void loadTransactions();
        void onDeleteItemButtonClicked(SalesTransaction salesTransaction);
        void deleteTransaction(SalesTransaction salesTransaction);
        void editTransaction(SalesTransaction salesTransaction);
        Customer getCustomerById(long id);
    }

    public interface Repository {
        List<SalesTransaction>getAllTransactions();
        void updateTransaction(SalesTransaction salesTransaction, OnDatabaseOperationCompleteListener listener);
        SalesTransaction getTransactionById(long id);
        void deleteTransaction(long id, OnDatabaseOperationCompleteListener listener);
    }
}

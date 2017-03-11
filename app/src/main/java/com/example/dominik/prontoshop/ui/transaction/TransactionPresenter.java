package com.example.dominik.prontoshop.ui.transaction;

import com.example.dominik.prontoshop.core.ProntoShopApplication;
import com.example.dominik.prontoshop.core.listeners.OnDatabaseOperationCompleteListener;
import com.example.dominik.prontoshop.model.Customer;
import com.example.dominik.prontoshop.model.Transaction;
import com.example.dominik.prontoshop.ui.customerlist.CustomerListContract;

import java.util.List;

import javax.inject.Inject;

public class TransactionPresenter implements TransactionContract.Actions, OnDatabaseOperationCompleteListener {

    private final TransactionContract.View mView;
    @Inject
    TransactionContract.Repository mRepository;
    @Inject
    CustomerListContract.Repository mCustomerContract;

    public TransactionPresenter(TransactionContract.View mView) {
        this.mView = mView;

    }


    @Override
    public void loadTransactions() {
        List<Transaction> transactions = mRepository.getAllTransactions();
        if (transactions != null & transactions.size() > 0) {
            mView.hideEmptyText();
            mView.showTransaction(transactions);
        } else {
            mView.showEmptyText();
        }
    }

    @Override
    public void onDeleteItemButtonClicked(Transaction transaction) {
        mView.confirmDeletePrompt(transaction);
    }

    @Override
    public void deleteTransaction(Transaction transaction) {
        mRepository.deleteTransaction(transaction.getId(), this);
    }

    @Override
    public void editTransaction(Transaction transaction) {
        mRepository.updateTransaction(transaction, this);
    }

    @Override
    public Customer getCustomerById(long id) {
        return mCustomerContract.getCustomer(id);
    }

    @Override
    public void OnDatabaseOperationFailed(String error) {
        mView.showMessage("Error: " + error);
    }

    @Override
    public void OnDatabaseOperationSuccesed(String message) {
        mView.showMessage(message);
    }
}

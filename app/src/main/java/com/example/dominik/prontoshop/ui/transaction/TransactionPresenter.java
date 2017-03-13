package com.example.dominik.prontoshop.ui.transaction;

import com.example.dominik.prontoshop.core.ProntoShopApplication;
import com.example.dominik.prontoshop.core.listeners.OnDatabaseOperationCompleteListener;
import com.example.dominik.prontoshop.model.Customer;
import com.example.dominik.prontoshop.model.SalesTransaction;
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
        ProntoShopApplication.getInstance().getAppComponent().inject(this);
    }


    @Override
    public void loadTransactions() {
        List<SalesTransaction> salesTransactions = mRepository.getAllTransactions();
        if (salesTransactions != null & salesTransactions.size() > 0) {
            mView.hideEmptyText();
            mView.showTransaction(salesTransactions);
        } else {
            mView.showEmptyText();
        }
    }

    @Override
    public void onDeleteItemButtonClicked(SalesTransaction salesTransaction) {
        mView.confirmDeletePrompt(salesTransaction);
    }

    @Override
    public void deleteTransaction(SalesTransaction salesTransaction) {
        mRepository.deleteTransaction(salesTransaction.getId(), this);
    }

    @Override
    public void editTransaction(SalesTransaction salesTransaction) {
        mRepository.updateTransaction(salesTransaction, this);
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

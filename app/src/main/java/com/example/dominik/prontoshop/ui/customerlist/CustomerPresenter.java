package com.example.dominik.prontoshop.ui.customerlist;


import com.example.dominik.prontoshop.common.ShoppingCart;
import com.example.dominik.prontoshop.core.ProntoShopApplication;
import com.example.dominik.prontoshop.core.listeners.OnDatabaseOperationCompleteListener;
import com.example.dominik.prontoshop.model.Customer;
import com.squareup.otto.Bus;

import java.util.List;

import javax.inject.Inject;

public class CustomerPresenter implements CustomerListContract.Actions, OnDatabaseOperationCompleteListener{

    private final CustomerListContract.View mView;

    @Inject
    CustomerListContract.Repository mRepository;

    @Inject
    ShoppingCart mCart;

    @Inject
    Bus mBus;


    public CustomerPresenter(CustomerListContract.View mView){
        this.mView = mView;
        ProntoShopApplication.getInstance().getAppComponent().inject(this);
    }

    @Override
    public void loadCustomers() {
        List<Customer>availableCustomers = mRepository.getAllCustomers();
        if(availableCustomers != null && availableCustomers.size() > 0) {
            mView.hideEmptyText();
            mView.showCustomers(availableCustomers);
        } else {
            mView.showEmptyText();
        }
    }

    @Override
    public Customer getCustomer(long id) {
        return mRepository.getCustomer(id);
    }

    @Override
    public void onCustomerSelected(Customer customer) {
        mCart.setCustomer(customer);
    }

    @Override
    public void onAddCustomerButtonClicked(Customer customer) {
        mView.showAddCustomerForm();
    }

    @Override
    public void addCustomer(Customer customer) {
        mRepository.addCustomer(customer, this);
    }

    @Override
    public void onEditCustomerButtonClicked(Customer customer) {
        mView.showEditCustomerForm(customer);
    }

    @Override
    public void updateCustomer(Customer customer) {
        mRepository.updateCustomer(customer, this);
    }

    @Override
    public void onDeleteCustomerButtonClicked(Customer customer) {
        mView.showDeleteCustomerPrompt(customer);
    }

    @Override
    public void deleteCustomer(Customer customer) {
        mRepository.deleteCustomer(customer, this);
    }

    @Override
    public void OnDatabaseOperationFailed(String error) {
        mView.showMessage("Error: "+ error);
    }

    @Override
    public void OnDatabaseOperationSuccesed(String message) {
        mView.showMessage(message);
    }
}

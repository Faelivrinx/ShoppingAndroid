package com.example.dominik.prontoshop.ui.customerlist;

import com.example.dominik.prontoshop.core.listeners.OnDatabaseOperationCompleteListener;
import com.example.dominik.prontoshop.data.SampleCustomerData;
import com.example.dominik.prontoshop.model.Customer;

import java.util.List;


public class CustomerInMemoryRepository implements CustomerListContract.Repository {

    public CustomerInMemoryRepository() {
    }

    @Override
    public List<Customer> getAllCustomers() {
        return SampleCustomerData.getSampleCustomers();
    }

    @Override
    public Customer getCustomer(long id) {
        return null;
    }

    @Override
    public void addCustomer(Customer customer, OnDatabaseOperationCompleteListener listener) {

    }

    @Override
    public void deleteCustomer(Customer customer, OnDatabaseOperationCompleteListener listener) {

    }

    @Override
    public void updateCustomer(Customer customer, OnDatabaseOperationCompleteListener listener) {

    }
}

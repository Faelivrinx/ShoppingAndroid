package com.example.dominik.prontoshop.core.listeners;

import com.example.dominik.prontoshop.model.Customer;

public interface OnCustomerSelectedListener {

    void onSelectCustomer(Customer customer);
    void onLongClickedCustomer(Customer customer);
}

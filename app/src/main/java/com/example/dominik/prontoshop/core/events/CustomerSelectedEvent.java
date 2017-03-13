package com.example.dominik.prontoshop.core.events;

import com.example.dominik.prontoshop.model.Customer;



public class CustomerSelectedEvent {

    private final Customer selectedCustomer;
    private final boolean clearCustomer;


    public CustomerSelectedEvent(Customer selectedCustomer, boolean clearCustomer) {
        this.selectedCustomer = selectedCustomer;
        this.clearCustomer = clearCustomer;

    }

    public Customer getSelectedCustomer() {
        return selectedCustomer;
    }
    public boolean isClearCustomer() {
        return clearCustomer;
    }

}

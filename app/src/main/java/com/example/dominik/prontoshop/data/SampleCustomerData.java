package com.example.dominik.prontoshop.data;

import com.example.dominik.prontoshop.core.listeners.OnDatabaseOperationCompleteListener;
import com.example.dominik.prontoshop.model.Customer;
import com.example.dominik.prontoshop.ui.customerlist.CustomerListContract;

import java.util.ArrayList;
import java.util.List;

public class SampleCustomerData {

    public static List<Customer> getSampleCustomers() {
        List<Customer> customers = new ArrayList<>();

        for (int i = 1; i < 20; i++) {
            Customer customer = new Customer();
            customer.setCustomerName("Customer " + i);
            customer.setEmailAddress("xxx@xxx.o2.pl");
            customer.setProfileImagePath("https://dl.dropboxusercontent.com/u/15447938/attendanceapp/guest"+i+".JPG");
            customers.add(customer);
        }
        return customers;
    }


}

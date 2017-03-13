package com.example.dominik.prontoshop.data;

import com.example.dominik.prontoshop.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class SampleCustomerData {

    public static List<Customer> getSampleCustomers() {
        List<Customer> customers = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Customer customer = new Customer();
            customer.setCustomerName("Customer " + i);
            customer.setEmailAddress("xxx@xxx.o2.pl");
            customer.setProfileImagePath("http://www.gravatar.com/avatar/" + i + "?d=identicon");
            customers.add(customer);
        }
        return customers;
    }
}

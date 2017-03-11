package com.example.dominik.prontoshop.ui.customerlist;

import com.example.dominik.prontoshop.model.Customer;
import com.example.dominik.prontoshop.model.Product;

import java.util.List;

public interface CustomerListContract {

    public interface View {
        void showCustomers(List<Customer> customers);

        void showAddCustomerForm();

        void showEditCustomerForm(Customer customer);

        void showDeleteCustomerPrompt(Customer customer);

        void showGoogleSearch(Customer customer);

        void showEmptyText();

        void hideEmptyText();

        void showMessage(String message);
    }

    public interface Actions {

        void loadCustomers();

        Customer getCustomer(long id);

        void onCustomerSelected();

        void onAddCustomerButtonClicked(Customer customer);

        void addCustomer(Customer customer);

        void onEditCustomerButtonClicked(Customer customer);

        void updateCustomer(Customer customer);

        void onDeleteCustomerButtonClicked(Customer customer);

        void deleteCustomer(Customer customer);


    }

    public interface Repository {

        List<Customer> getAllCustomers();

        Customer getCustomer(long id);

        void addCustomer(Customer customer);

        void deleteCustomer(Customer customer);

        void updateCustomer(Customer customer);
    }
}

package com.example.dominik.prontoshop.ui.checkout;

import com.example.dominik.prontoshop.core.listeners.OnDatabaseOperationCompleteListener;
import com.example.dominik.prontoshop.model.LineItem;
import com.example.dominik.prontoshop.model.SalesTransaction;

import java.util.List;

public interface CheckoutContract {

    public interface View {
        void showLineItems(List<LineItem> items);

        void showEmptyText();

        void hideText();

        void showCartTotals(double tax, double subTotal, double total);

        void showConfirmCheckout();

        void showCofirmClearCart();

        void showMessage(String message);

    }

    public interface Actions {

        void loadLineItems();

        void onCheckoutButtonClicked();

        void onDeleteItemButtonClicked(LineItem item);

        void checkout();

        void onClearButtonClicked();

        void clearShoppingCart();

        void setPaymentType(String paymentType);

        void markAsPaid(boolean paid);

        void onItemQtyChanged(LineItem item, int qty);
    }

    public interface Repository {
        List<LineItem> getAllLineItems();

        void saveTransaction(SalesTransaction salesTransaction, OnDatabaseOperationCompleteListener listener);

        void updateTransaction(SalesTransaction salesTransaction, OnDatabaseOperationCompleteListener listener);

    }
}

package com.example.dominik.prontoshop.ui.checkout;

import com.example.dominik.prontoshop.common.ShoppingCart;
import com.example.dominik.prontoshop.core.listeners.OnDatabaseOperationCompleteListener;
import com.example.dominik.prontoshop.model.LineItem;
import com.example.dominik.prontoshop.model.Transaction;
import com.squareup.otto.Bus;

import java.util.List;

import javax.inject.Inject;


public class CheckoutPresenter implements CheckoutContract.Actions, OnDatabaseOperationCompleteListener {

    private final CheckoutContract.View mView;

    @Inject
    ShoppingCart mCart;

    @Inject
    Bus mBus;

    @Inject
    CheckoutContract.Repository mRepository;

    private double subTotal = 0.0;
    private double total;
    private double tax;
    private double taxRate;
    private String selectedPaymentType = "";
    private boolean paid = false;

    public CheckoutPresenter(CheckoutContract.View mView) {
        this.mView = mView;
    }


    @Override
    public void loadLineItems() {
        List<LineItem> availableLineitems = mRepository.getAllLineItems();
        calculateTotals(availableLineitems);
        if(availableLineitems != null && availableLineitems.size() > 0){
            mView.hideText();
            mView.showLineItems(availableLineitems);
        } else {
            mView.showEmptyText();
        }
    }

    private void calculateTotals(List<LineItem> availableLineitems) {
        subTotal = 0.0;
        total = 0.0;
        tax = 0.0;

        for(LineItem item : availableLineitems){
            subTotal += item.getSumPrice();
        }

        tax = subTotal * taxRate;
        total = tax + subTotal;
        mView.showCartTotals(tax, subTotal, total);
    }

    @Override
    public void onCheckoutButtonClicked() {
        mView.showConfirmCheckout();
    }

    @Override
    public void onDeleteItemButtonClicked(LineItem item) {
        mCart.removeItemFromCart(item);
        loadLineItems();
    }

    @Override
    public void checkout() {
        if(mCart.getShoppingCart() == null || mCart.getShoppingCart().size() == 0){
            mView.showMessage("Cart is Empty");
            return;
        }

        if(mCart.getSelectedCustomer() == null && mCart.getSelectedCustomer().getId() == 0){
            mView.showMessage("No customer is selected");
            return;
        }

        Transaction transaction = new Transaction();
        transaction.setCustomerId(mCart.getSelectedCustomer().getId());
        transaction.setLineItems(mCart.getShoppingCart());
        transaction.setTaxAmount(tax);
        transaction.setSubTotalAmount(subTotal);
        transaction.setTotalAmount(total);
        transaction.setPaymentType(selectedPaymentType);
        transaction.setPaid(paid);
        mRepository.saveTransaction(transaction, this);
    }

    @Override
    public void onClearButtonClicked() {
        mView.showCofirmClearCart();
    }

    @Override
    public void clearShoppingCart() {
        mCart.clearAllItemsFromCart();
        loadLineItems();
    }

    @Override
    public void setPaymentType(String paymentType) {
        selectedPaymentType = paymentType;
    }

    @Override
    public void markAsPaid(boolean paid) {
        this.paid = paid;
    }

    @Override
    public void onItemQtyChanged(LineItem item, int qty) {
        mCart.updateItemQty(item, qty);

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

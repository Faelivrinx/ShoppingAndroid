package com.example.dominik.prontoshop.core.listeners;

import com.example.dominik.prontoshop.model.LineItem;

public interface CartActionsListener {
    void onItemDeleted(LineItem item);
    void onItemQtyChanged(LineItem item, int qty);
}


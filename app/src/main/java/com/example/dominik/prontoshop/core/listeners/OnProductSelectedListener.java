package com.example.dominik.prontoshop.core.listeners;

import com.example.dominik.prontoshop.model.Product;

public interface OnProductSelectedListener {
    void onSelectProduct(Product selectedProduct);
    void onLongClickProduct(Product clickedProduct);
}

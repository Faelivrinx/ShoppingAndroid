package com.example.dominik.prontoshop.data;

import com.example.dominik.prontoshop.core.listeners.OnDatabaseOperationCompleteListener;
import com.example.dominik.prontoshop.model.Category;
import com.example.dominik.prontoshop.model.Product;
import com.example.dominik.prontoshop.ui.productlist.ProductListContract;

import java.util.ArrayList;
import java.util.List;

public class SampleProductData {

    public static List<Product> getSampleProducts() {
        List<Product> products = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Product product = new Product();
            product.setProductName("Product " + i);
            product.setCategoryName("Electronic");
            product.setDescription("Some description will be here");
            product.setImagePath(null);
            product.setSalePrice(207.0);
            products.add(product);
        }

        return products;
    }

}

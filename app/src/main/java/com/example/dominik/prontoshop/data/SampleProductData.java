package com.example.dominik.prontoshop.data;

import com.example.dominik.prontoshop.model.Product;

import java.util.ArrayList;
import java.util.List;

public class SampleProductData {

    public static List<Product> getSampleProducts() {
        List<Product> products = new ArrayList<>();

        for (int i = 0; i < 0; i++) {
            Product product = new Product();
            product.setProductName("Product " + i);
            product.setCategoryName("Electronic");
            product.setDescription("Some description will be here");
            product.setImagePath("http://www.gravatar.com/avatar/" + i + "?d=identicon");
            product.setSalePrice(207.0);
            products.add(product);
        }

        return products;
    }
}

package com.example.dominik.prontoshop.ui.productlist;

import com.example.dominik.prontoshop.core.listeners.OnDatabaseOperationCompleteListener;
import com.example.dominik.prontoshop.data.SampleProductData;
import com.example.dominik.prontoshop.model.Category;
import com.example.dominik.prontoshop.model.Product;

import java.util.List;

public class ProductInMemoryRepository implements ProductListContract.Repository {

    public ProductInMemoryRepository() {
    }

    @Override
    public List<Product> getAllProducts() {
        return SampleProductData.getSampleProducts();
    }

    @Override
    public Product getProductById(long id) {
        return null;
    }

    @Override
    public void deleteProduct(Product product, OnDatabaseOperationCompleteListener listener) {

    }

    @Override
    public void addProduct(Product product, OnDatabaseOperationCompleteListener listener) {

    }

    @Override
    public void updateProduct(Product product, OnDatabaseOperationCompleteListener listener) {

    }

    @Override
    public List<Category> getAllCategories() {
        return null;
    }
}

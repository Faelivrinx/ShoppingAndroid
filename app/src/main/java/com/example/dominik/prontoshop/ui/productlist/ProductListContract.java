package com.example.dominik.prontoshop.ui.productlist;


import com.example.dominik.prontoshop.model.Category;
import com.example.dominik.prontoshop.model.Product;

import java.util.List;

public interface ProductListContract {

    public interface View {
        void showProducts(List<Product> products);

        void showAddProductForm();

        void showEditProductForm(Product product);

        void showDeleteProductPrompt(Product product);

        void showGoogleSearch(Product product);

        void showEmptyText();

        void hideEmptyText();

        void showMessage(String message);
    }

    public interface Actions {
        void loadProducts();

        Product getProduct(long id);

        void onAddProductButtonClicked();

        void onAddToCartButtonClicked(Product product);

        void addProduct(Product product);

        void onDeleteProductButtonClicked(Product product);

        void deleteProduct(Product product);

        void onEditProductButtonClicked(Product product);

        void updateProduct(Product product);

        void onGoogleSearchButtonClicked(Product product);

    }

    public interface Repository {
        List<Product> getAllProducts();

        Product getProductById(long id);

        void deleteProduct(Product product);

        void addProduct(Product product);

        void updateProduct(Product product);

        List<Category> getAllCategories();
    }
}

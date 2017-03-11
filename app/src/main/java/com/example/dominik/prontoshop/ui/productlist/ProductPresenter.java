package com.example.dominik.prontoshop.ui.productlist;

import com.example.dominik.prontoshop.common.ShoppingCart;
import com.example.dominik.prontoshop.core.ProntoShopApplication;
import com.example.dominik.prontoshop.core.listeners.OnDatabaseOperationCompleteListener;
import com.example.dominik.prontoshop.model.LineItem;
import com.example.dominik.prontoshop.model.Product;
import com.squareup.otto.Bus;

import java.util.List;

import javax.inject.Inject;

public class ProductPresenter implements ProductListContract.Actions, OnDatabaseOperationCompleteListener {

    private final ProductListContract.View mView;

    @Inject
    ProductListContract.Repository mRepository;

    @Inject
    ShoppingCart mCart;

    @Inject
    Bus mBus;


    public ProductPresenter(ProductListContract.View mView) {
        this.mView = mView;
        ProntoShopApplication.getInstance().getAppComponent().inject(this);
    }

    @Override
    public void loadProducts() {
        List<Product> availableProducts = mRepository.getAllProducts();
        if (availableProducts != null && availableProducts.size() > 0) {
            mView.hideEmptyText();
            mView.showProducts(availableProducts);
        } else {
            mView.showEmptyText();
        }
    }

    @Override
    public Product getProduct(long id) {
        return mRepository.getProductById(id);
    }

    @Override
    public void onAddProductButtonClicked() {
        mView.showAddProductForm();
    }

    @Override
    public void onAddToCartButtonClicked(Product product) {
        //create LineItem from the passed in product
        LineItem item = new LineItem(product, 1);
        mCart.addItemToCart(item);
    }

    @Override
    public void addProduct(Product product) {
        mRepository.addProduct(product, this);
    }

    @Override
    public void onDeleteProductButtonClicked(Product product) {
        mView.showDeleteProductPrompt(product);
    }

    @Override
    public void deleteProduct(Product product) {
        mRepository.deleteProduct(product, this);
    }

    @Override
    public void onEditProductButtonClicked(Product product) {
        mView.showEditProductForm(product);
    }

    @Override
    public void updateProduct(Product product) {
        mRepository.updateProduct(product, this);
    }

    @Override
    public void onGoogleSearchButtonClicked(Product product) {
        mView.showGoogleSearch(product);
    }

    @Override
    public void OnDatabaseOperationFailed(String error) {
        mView.showMessage("Error: "+error);
    }

    @Override
    public void OnDatabaseOperationSuccesed(String message) {
        mView.showMessage(message);
    }
}

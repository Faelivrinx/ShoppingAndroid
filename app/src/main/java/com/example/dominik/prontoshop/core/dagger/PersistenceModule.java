package com.example.dominik.prontoshop.core.dagger;


import android.content.Context;

import com.example.dominik.prontoshop.model.Product;
import com.example.dominik.prontoshop.ui.customerlist.CustomerInMemoryRepository;
import com.example.dominik.prontoshop.ui.customerlist.CustomerListContract;
import com.example.dominik.prontoshop.ui.productlist.ProductInMemoryRepository;
import com.example.dominik.prontoshop.ui.productlist.ProductListContract;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PersistenceModule {

    @Provides
    @Singleton
    public ProductListContract.Repository providesProductRepository(Context context) {
        return new ProductInMemoryRepository();
    }

    @Provides
    @Singleton
    public CustomerListContract.Repository providesCustomerRepository(Context context) {
        return new CustomerInMemoryRepository();
    }
}

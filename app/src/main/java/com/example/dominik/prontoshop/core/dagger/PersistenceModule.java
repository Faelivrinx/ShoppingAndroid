package com.example.dominik.prontoshop.core.dagger;


import android.content.Context;

import com.example.dominik.prontoshop.ui.customerlist.CustomerInMemoryRepository;
import com.example.dominik.prontoshop.ui.customerlist.CustomerListContract;
import com.example.dominik.prontoshop.ui.customerlist.CustomerListSQLiteManager;
import com.example.dominik.prontoshop.ui.productlist.ProductInMemoryRepository;
import com.example.dominik.prontoshop.ui.productlist.ProductListContract;
import com.example.dominik.prontoshop.ui.productlist.ProductListSQLiteManager;
import com.example.dominik.prontoshop.ui.transaction.TransactionContract;
import com.example.dominik.prontoshop.ui.transaction.TransactionSQLiteManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PersistenceModule {

    @Provides
    @Singleton
    public ProductListContract.Repository providesProductRepository(Context context) {
        return new ProductListSQLiteManager(context);
    }

    @Provides
    @Singleton
    public CustomerListContract.Repository providesCustomerRepository(Context context) {
        return new CustomerListSQLiteManager(context);
    }

    @Provides
    public TransactionContract.Repository providesTransactionRepository(Context context){
        return new TransactionSQLiteManager(context);
    }
}

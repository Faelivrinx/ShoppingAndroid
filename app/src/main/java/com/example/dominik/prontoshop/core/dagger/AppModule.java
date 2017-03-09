package com.example.dominik.prontoshop.core.dagger;


import android.content.Context;

import com.example.dominik.prontoshop.core.ProntoShopApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private final ProntoShopApplication app;

    public AppModule(ProntoShopApplication app) {
        this.app = app;
    }

    @Provides
    @Singleton
    public ProntoShopApplication provideApp() {
        return app;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return app;
    }

}

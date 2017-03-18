package com.example.dominik.prontoshop.core;

import android.app.Application;

import com.example.dominik.prontoshop.core.dagger.AppComponent;
import com.example.dominik.prontoshop.core.dagger.AppModule;
import com.example.dominik.prontoshop.core.dagger.DaggerAppComponent;
import com.squareup.otto.Bus;

/**
 * Created by Dominik on 2017-03-09.
 */

public class ProntoShopApplication extends Application {
    private static AppComponent appComponent;
    private static ProntoShopApplication instance = new ProntoShopApplication();

    private Bus bus;

    @Override
    public void onCreate() {
        super.onCreate();
        instance.bus = new Bus();
        getAppComponent();
    }

    public Bus getBus(){
        return bus;
    }

    public static ProntoShopApplication getInstance() {
        return instance;
    }


    public AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(this))
                    .build();
        }
        return appComponent;
    }
}

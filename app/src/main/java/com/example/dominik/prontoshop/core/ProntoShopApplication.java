package com.example.dominik.prontoshop.core;

import android.app.Application;

import com.example.dominik.prontoshop.core.dagger.AppComponent;
import com.example.dominik.prontoshop.core.dagger.AppModule;
import com.example.dominik.prontoshop.core.dagger.DaggerAppComponent;

/**
 * Created by Dominik on 2017-03-09.
 */

public class ProntoShopApplication extends Application{
    private static AppComponent appComponent;
    private static ProntoShopApplication instance = new ProntoShopApplication();

    @Override
    public void onCreate() {
        super.onCreate();
        getAppComponent();
    }

    private void getAppComponent() {
        if(appComponent == null){
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(this))
                    .build();
        }
    }
}

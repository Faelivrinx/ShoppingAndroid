package com.example.dominik.prontoshop.core.dagger;

import com.example.dominik.prontoshop.common.MainActivity;
import com.example.dominik.prontoshop.common.ShoppingCart;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                AppModule.class,
                ShoppingCartModule.class,
                BusModule.class
        }
)
public interface AppComponent {
    void inject(MainActivity activity);
    void inject(ShoppingCart cart);
}

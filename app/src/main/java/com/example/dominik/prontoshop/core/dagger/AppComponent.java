package com.example.dominik.prontoshop.core.dagger;

import com.example.dominik.prontoshop.common.MainActivity;
import com.example.dominik.prontoshop.common.ShoppingCart;
import com.example.dominik.prontoshop.ui.customerlist.CustomerPresenter;
import com.example.dominik.prontoshop.ui.productlist.ProductPresenter;

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
    void inject(ProductPresenter presenter);
    void inject(CustomerPresenter presenter);
}

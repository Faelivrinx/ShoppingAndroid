package com.example.dominik.prontoshop.common;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;

import com.example.dominik.prontoshop.R;
import com.example.dominik.prontoshop.core.ProntoShopApplication;
import com.example.dominik.prontoshop.data.DatabaseHelper;
import com.example.dominik.prontoshop.ui.checkout.CheckoutFragment;
import com.example.dominik.prontoshop.ui.customerlist.CustomerListFragment;
import com.example.dominik.prontoshop.ui.productlist.ProductListFragment;
import com.squareup.otto.Bus;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.activity_main_viewPager)
    ViewPager mViewPager;

    @Bind(R.id.activity_main_tabLayout)
    TabLayout mTabLayout;

    private Bus mBus;
    @Inject ShoppingCart mCart;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        mBus = ProntoShopApplication.getInstance().getBus();


        setupViewPager();

    }

    private void setupViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }


}

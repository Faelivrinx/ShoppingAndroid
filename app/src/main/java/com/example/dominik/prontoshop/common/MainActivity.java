package com.example.dominik.prontoshop.common;


import android.os.Bundle;

import android.support.design.widget.TabLayout;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


import com.example.dominik.prontoshop.R;
import com.example.dominik.prontoshop.core.ProntoShopApplication;


import com.squareup.otto.Bus;

import javax.inject.Inject;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.activity_main_viewPager)
    ViewPager mViewPager;

    @Bind(R.id.activity_main_tabLayout)
    TabLayout mTabLayout;

    private Bus mBus;

    @Inject
    ShoppingCart mCart;


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

    @Override
    protected void onPause() {
        super.onPause();
        ProntoShopApplication.getInstance().getAppComponent().inject(this);
        mCart.saveCartToPreferences();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            ProntoShopApplication.getInstance().getAppComponent().inject(this);
            mCart.saveCartToPreferences();
            mBus.unregister(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.example.dominik.prontoshop.common;


import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import android.support.design.widget.TabLayout;

import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.example.dominik.prontoshop.R;
import com.example.dominik.prontoshop.core.ProntoShopApplication;


import com.example.dominik.prontoshop.core.events.UpdateToolbarEvent;
import com.example.dominik.prontoshop.data.DatabaseHelper;
import com.example.dominik.prontoshop.util.Constants;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import org.w3c.dom.Text;

import javax.inject.Inject;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.custom_toolbar_cusomerName)
    TextView mNameTextView;

    @Bind(R.id.custom_toolbar_total_amount)
    TextView mTotalAmount;

    @Bind(R.id.custom_toolbar_number_of_items)
    TextView mNumberOfItems;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.activity_main_viewPager)
    ViewPager mViewPager;

    @Bind(R.id.activity_main_tabLayout)
    TabLayout mTabLayout;

    private Bus mBus;
    private Activity activity;

    @Inject
    ShoppingCart mCart;

    private AccountHeader mHeader = null;

    private Drawer mDrawer;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar =  (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        activity = this;
        mBus = ProntoShopApplication.getInstance().getBus();
        ProntoShopApplication.getInstance().getAppComponent().inject(this);

        mHeader =new  AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .withSavedInstance(savedInstanceState)
                .build();

        mDrawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(mHeader)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Cart").withIcon(FontAwesome.Icon.faw_shopping_cart).withIdentifier(Constants.CART),
                        new PrimaryDrawerItem().withName("Report").withIcon(FontAwesome.Icon.faw_bar_chart).withIdentifier(Constants.REPORT),
                        new PrimaryDrawerItem().withName("Settings").withIcon(FontAwesome.Icon.faw_cog).withIdentifier(Constants.SETTINGS),
                        new PrimaryDrawerItem().withName("Transactions").withIcon(FontAwesome.Icon.faw_credit_card).withIdentifier(Constants.TRANSACTIONS)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if(drawerItem != null && drawerItem instanceof Nameable){
                            String name = ((Nameable) drawerItem).getName().getText(MainActivity.this);
                            mNameTextView.setText(name);
                            onTouchDrawer(drawerItem.getIdentifier());
                        }
                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .build();

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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            mBus.unregister(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void onTouchDrawer(long identifier) {
        switch ((int) identifier){
            case Constants.CART:
                //
                break;
            case Constants.REPORT:
                //Go to report
                Toast.makeText(this,"Report not implemented yet", Toast.LENGTH_SHORT).show();
                break;
            case Constants.SETTINGS:
                //Go to settings
                Toast.makeText(this,"Settings not implemented yet", Toast.LENGTH_SHORT).show();
                break;
            case Constants.TRANSACTIONS:
                //startActivity(new Intent(MainActivity.this, TransactionActivity.class));
                break;
            case Constants.PURCHASE:
                //Go to checkout page
                Intent courseIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.prontocast.com"));
                startActivity(courseIntent);
                break;

        }
    }
}

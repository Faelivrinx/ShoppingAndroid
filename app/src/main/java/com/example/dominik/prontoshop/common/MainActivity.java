package com.example.dominik.prontoshop.common;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.dominik.prontoshop.R;
import com.example.dominik.prontoshop.ui.customerlist.CustomerListFragment;
import com.example.dominik.prontoshop.ui.productlist.ProductListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        openFragment(new CustomerListFragment(), "Product List");

    }

    private void openFragment(Fragment productListFragment, String title) {
        getSupportFragmentManager().beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.acitivity_main_container, productListFragment)
                .addToBackStack(null)
                .commit();

        getSupportActionBar().setTitle(title);
    }


}

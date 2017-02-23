package com.example.dominik.prontoshop.common;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.dominik.prontoshop.ui.checkout.CheckoutFragment;
import com.example.dominik.prontoshop.ui.customerlist.CustomerListFragment;
import com.example.dominik.prontoshop.ui.productlist.ProductListFragment;


public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment selectedFragment = null;
        switch (position){
            case 0:
                selectedFragment =  new ProductListFragment();
                break;
            case 1:
                selectedFragment =  new CustomerListFragment();
                break;
            case 2:
                selectedFragment =  new CheckoutFragment();
                break;
        }
        return selectedFragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";

        switch (position){
            case 0:
                title = "Products";
                break;
            case 1:
                title = "Customers";
                break;
            case 2:
                title = "Checkout";
                break;
        }
        return title;
    }
}

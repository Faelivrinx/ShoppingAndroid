package com.example.dominik.prontoshop.ui.customerlist;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dominik.prontoshop.R;
import com.example.dominik.prontoshop.core.listeners.OnCustomerSelectedListener;
import com.example.dominik.prontoshop.model.Customer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomerListFragment extends Fragment implements OnCustomerSelectedListener {

    private View mRootView;
    private CustomerListAdapter mAdapter;


    @BindView(R.id.fragment_customer_list_recyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.fragment_customer_list_emptyText)
    TextView mEmptyText;

    @BindView(R.id.fragment_customer_list_floatActionButton)
    FloatingActionButton mFab;



    public CustomerListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_customer_list, container, false);

        ButterKnife.bind(this, mRootView);

        List<Customer> tempCustomers = new ArrayList<>();
        mAdapter = new CustomerListAdapter(tempCustomers, getActivity(), this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);

        if(tempCustomers.size() < 1){
            showEmptyTextMessage();
        } else {
            hideEmptyTextMessage();
        }

        return mRootView;
    }


    private void hideEmptyTextMessage() {
        mEmptyText.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void showEmptyTextMessage() {
        mRecyclerView.setVisibility(View.GONE);
        mEmptyText.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSelectCustomer(Customer customer) {


    }

    @Override
    public void onLongClickedCustomer(Customer customer) {

    }
}

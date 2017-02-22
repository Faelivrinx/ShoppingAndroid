package com.example.dominik.prontoshop.ui.productlist;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dominik.prontoshop.R;
import com.example.dominik.prontoshop.core.listeners.OnProductSelectedListener;
import com.example.dominik.prontoshop.model.Product;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductListFragment extends Fragment implements OnProductSelectedListener {

    private View mRootView;
    private ProductListAdapter mAdapter;


    @BindView(R.id.fragment_product_list_recyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.fragment_product_list_emptyText)
    TextView mEmptyText;

    @BindView(R.id.fragment_product_list_floatActionButton)
    FloatingActionButton mFab;


    public ProductListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_product_list, container, false);
        ButterKnife.bind(this, mRootView);

        List<Product> tempProducts = new ArrayList<>();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());

        mAdapter = new ProductListAdapter(tempProducts, getActivity(), this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        if(tempProducts.size() < 1){
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
    public void onSelectProduct(Product selectedProduct) {

    }

    @Override
    public void onLongClickProduct(Product clickedProduct) {

    }
}

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
import android.widget.Toast;

import com.example.dominik.prontoshop.R;
import com.example.dominik.prontoshop.core.listeners.OnProductSelectedListener;
import com.example.dominik.prontoshop.model.Product;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ProductListFragment extends Fragment implements OnProductSelectedListener, ProductListContract.View {

    private View mRootView;
    private ProductListAdapter mAdapter;
    private ProductListContract.Actions mPresenter;

    @Bind(R.id.fragment_product_list_recyclerView)
    RecyclerView mRecyclerView;

    @Bind(R.id.fragment_product_list_emptyText)
    TextView mEmptyText;

    @Bind(R.id.fragment_product_list_floatActionButton)
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
        mPresenter = new ProductPresenter(this);

        List<Product> tempProducts = new ArrayList<>();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());

        mAdapter = new ProductListAdapter(tempProducts, getActivity(), this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);


        return mRootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.loadProducts();
    }

    @Override
    public void onSelectProduct(Product selectedProduct) {

    }

    @Override
    public void onLongClickProduct(Product clickedProduct) {

    }

    @Override
    public void showProducts(List<Product> products) {
        mAdapter.replaceData(products);
    }

    @Override
    public void showAddProductForm() {

    }

    @Override
    public void showEditProductForm(Product product) {

    }

    @Override
    public void showDeleteProductPrompt(Product product) {

    }

    @Override
    public void showGoogleSearch(Product product) {

    }

    @Override
    public void showEmptyText() {
        mRecyclerView.setVisibility(View.GONE);
        mEmptyText.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmptyText() {
        mEmptyText.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}

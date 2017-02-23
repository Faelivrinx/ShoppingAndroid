package com.example.dominik.prontoshop.ui.checkout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.dominik.prontoshop.R;
import com.example.dominik.prontoshop.core.listeners.CartActionsListener;
import com.example.dominik.prontoshop.model.LineItem;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class CheckoutFragment extends Fragment implements CartActionsListener {

    private View mRootView;
    private CheckoutAdapter mAdapter;


    @BindView(R.id.fragment_checkout_list_recyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.fragment_checkout_buttonCard)
    Button mButtonCheckout;

    @BindView(R.id.fragment_checkout_clearButton)
    Button mButtonClear;

    @BindView(R.id.fragment_checkout_list_emptyText)
    TextView mEmptyText;

    @BindView(R.id.fragment_checkout_list_textView_sub_total)
    TextView mSubTotalTextView;

    @BindView(R.id.fragment_checkout_list_textView_total)
    TextView mTotalTextView;

    @BindView(R.id.fragment_checkout_list_textView_tax)
    TextView mTotalTaxValue;

    @BindView(R.id.fragment_checkout_radioGroup_payment_type)
    RadioGroup mRadioGroup;





    public CheckoutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_checkout, container, false);
        ButterKnife.bind(this, mRootView);

        List<LineItem>tempLineItems = new ArrayList<>();
        mAdapter = new CheckoutAdapter(tempLineItems, getActivity(), this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);

        if(tempLineItems.size() < 1){
            showEmptyTextMessage();
        } else {
            hideEmptyTextMessage();
        }

        return mRootView;
    }



    @Override
    public void onItemDeleted(LineItem item) {

    }

    @Override
    public void onItemQtyChanged(LineItem item, int qty) {

    }


    private void hideEmptyTextMessage() {
        mEmptyText.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void showEmptyTextMessage() {
        mRecyclerView.setVisibility(View.GONE);
        mEmptyText.setVisibility(View.VISIBLE);
    }
}

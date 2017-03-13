package com.example.dominik.prontoshop.ui.customerlist;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dominik.prontoshop.R;
import com.example.dominik.prontoshop.core.listeners.OnCustomerSelectedListener;
import com.example.dominik.prontoshop.model.Customer;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class CustomerListAdapter extends RecyclerView.Adapter<CustomerListAdapter.ViewHolder> {

    private List<Customer> mCustomers;
    private Context mContext;
    private boolean shouldHightlightSelectedCustomer = false;
    private int selectedPosition = 0;
    private OnCustomerSelectedListener mListener;

    public CustomerListAdapter(List<Customer> mCustomers, Context mContext, OnCustomerSelectedListener mListener) {
        this.mCustomers = mCustomers;
        this.mContext = mContext;
        this.mListener = mListener;
    }

    public void replaceData(List<Customer> customers){
        mCustomers = customers;
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_customer, parent, false);

        ViewHolder viewHolder = new ViewHolder(rootView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Customer selectedCustomer = mCustomers.get(position);

        holder.customerName.setText(selectedCustomer.getCustomerName());
        holder.customerEmail.setText(selectedCustomer.getEmailAddress());
        Picasso.with(mContext).load(selectedCustomer.getProfileImagePath())
                .fit()
                .placeholder(R.drawable.profile_icon)
                .into(holder.customerHeadShot);

        if (shouldHightlightSelectedCustomer) {
            if (selectedPosition == position) {
                holder.itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.primary_light));
            } else {
                holder.itemView.setBackgroundColor(Color.TRANSPARENT);
            }
        } else {
            holder.itemView.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    @Override
    public int getItemCount() {
        return mCustomers.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        @Bind(R.id.list_customer_customerName)
        TextView customerName;

        @Bind(R.id.list_customer_customerEmail)
        TextView customerEmail;

        @Bind(R.id.list_customer_imageView_head)
        ImageView customerHeadShot;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            shouldHightlightSelectedCustomer = true;
            selectedPosition = getLayoutPosition();

            Customer selectedCustomer = mCustomers.get(selectedPosition);
            mListener.onSelectCustomer(selectedCustomer);
            //refresh onBindViewHolder
            notifyDataSetChanged();

        }

        @Override
        public boolean onLongClick(View view) {
            Customer selectedCustomer = mCustomers.get(selectedPosition);
            mListener.onLongClickedCustomer(selectedCustomer);
            return true;
        }
    }
}

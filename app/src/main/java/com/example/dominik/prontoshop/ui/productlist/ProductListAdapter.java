package com.example.dominik.prontoshop.ui.productlist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.dominik.prontoshop.R;
import com.example.dominik.prontoshop.core.listeners.OnProductSelectedListener;
import com.example.dominik.prontoshop.model.Product;
import com.example.dominik.prontoshop.util.Formatter;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {


    private List<Product> mProducts;
    private Context mContext;
    private OnProductSelectedListener listener;
    private LayoutInflater inflater;

    public ProductListAdapter(List<Product> mProducts, Context mContext, OnProductSelectedListener listener) {
        this.mProducts = mProducts;
        this.mContext = mContext;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_product, parent, false);
        ViewHolder viewHolder = new ViewHolder(rootView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
    if (mProducts != null) {
        Product product = mProducts.get(position);
        Picasso.with(mContext).load(product.getImagePath())
                .fit()
                .placeholder(R.drawable.default_image)
                .into(holder.productImage);

        holder.productName.setText(product.getProductName());
        holder.productCategory.setText(product.getCategoryName());
        holder.productPrice.setText(Formatter.formatCurrency(product.getSalePrice()));
        String productDescription = product.getDescription();
        String shortDescription = product.getDescription().substring(0, Math.min(productDescription.length(), 70));
        holder.productDescription.setText(shortDescription);
    }
    else {

    }

    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        @BindView(R.id.list_product_productName)
        TextView productName;

        @BindView(R.id.list_product_productCategory)
        TextView productCategory;

        @BindView(R.id.list_product_productPrice)
        TextView productPrice;

        @BindView(R.id.list_product_description)
        TextView productDescription;

        @BindView(R.id.list_product_addCartButton)
        ImageView addCartButton;

        @BindView(R.id.list_product_image)
        ImageView productImage;


        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
            addCartButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Product selectedProduct = mProducts.get(getLayoutPosition());
            listener.onSelectProduct(selectedProduct);
        }

        @Override
        public boolean onLongClick(View view) {
            Product clickedProduct = mProducts.get(getLayoutPosition());
            listener.onLongClickProduct(clickedProduct);
            return true;
        }
    }
}

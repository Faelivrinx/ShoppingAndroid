package com.example.dominik.prontoshop.ui.checkout;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dominik.prontoshop.R;
import com.example.dominik.prontoshop.core.listeners.CartActionsListener;
import com.example.dominik.prontoshop.model.LineItem;
import com.example.dominik.prontoshop.util.Formatter;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CheckoutAdapter extends RecyclerView.Adapter<CheckoutAdapter.ViewHolder> {

    private List<LineItem> mLineItems;
    private Activity mContext;
    private CartActionsListener mListener;


    public CheckoutAdapter(List<LineItem> mLineItems, Context mContext, CartActionsListener mListener) {
        this.mLineItems = mLineItems;

        //trzeba rzutowac na aktywność. Zwróc uwagę!
        this.mContext =(Activity) mContext;

        this.mListener = mListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_shopping_cart, parent, false);
        ViewHolder viewHolder = new ViewHolder(rootView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LineItem item = mLineItems.get(position);
        Picasso.with(mContext).load(item.getImagePath())
                .fit()
                .placeholder(R.drawable.default_image)
                .into(holder.productImage);

        holder.productName.setText(item.getProductName());
        holder.productPrice.setText(Formatter.formatCurrency(item.getSalePrice()));
        holder.qtyEditText.setText(String.valueOf(item.getQuantity()));
    }

    @Override
    public int getItemCount() {
        return mLineItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.list_shopping_cart_productName)
        TextView productName;

        @Bind(R.id.list_shopping_cart_viewPrice)
        TextView productPrice;

        @Bind(R.id.list_shopping_cart_image)
        ImageView productImage;

        @Bind(R.id.list_shopping_cart_input_textQty)
        EditText qtyEditText;

        @Bind(R.id.list_shopping_cart_input_btnDelete)
        Button btnDelete;

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            btnDelete.setOnClickListener(this);
            qtyEditText.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    //get layoutPosition
                    LineItem item = mLineItems.get(getLayoutPosition());
                    updateQtyDialog(item);
                }
            });
        }

        @Override
        public void onClick(View view) {
            LineItem item = mLineItems.get(getLayoutPosition());
            mListener.onItemDeleted(item);
        }
    }

    private void updateQtyDialog(final LineItem item) {

        final AlertDialog.Builder dialog  = new AlertDialog.Builder(mContext);

        //dzięki temu, że contex jest typu activity
        LayoutInflater inflater = mContext.getLayoutInflater();

        View rootView = inflater.inflate(R.layout.dialog_enter_item_qty, null);
        dialog.setView(rootView);

        View titleView = inflater.inflate(R.layout.dialog_title, null);
        TextView title = (TextView)titleView.findViewById(R.id.dialog_title_textView);
        title.setText(item.getProductName());
        dialog.setCustomTitle(titleView);

        final EditText qtyEditText = (EditText)rootView.findViewById(R.id.dialog_enter_item_qty);
        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(qtyEditText.getText().toString().isEmpty()){
                    int qtyEntered = Integer.parseInt(qtyEditText.getText().toString());
                    mListener.onItemQtyChanged(item, qtyEntered);
                }
                else{
                    Toast.makeText(mContext, "Invalid Qty", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        dialog.show();

    }
}

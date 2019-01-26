package com.app.foodMonkey.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.foodMonkey.EditCartActivity;
import com.app.foodMonkey.Models.AddItemsToCartModel;
import com.app.foodMonkey.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kartikeya on 30/09/2018.
 */

public class EditCartAdapter extends RecyclerView.Adapter<EditCartAdapter.MyViewHolder> {

    Context mContext;
    List<AddItemsToCartModel> addItemsToCartModelArrayList = new ArrayList<>();

    public EditCartAdapter(Context mContext, List<AddItemsToCartModel> addItemsToCartModelArrayList) {
        this.mContext = mContext;
        this.addItemsToCartModelArrayList = addItemsToCartModelArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.editcart_custom_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if (position == 0) {
            holder.titleLayout.setVisibility(View.VISIBLE);
        } else if (!addItemsToCartModelArrayList.get(position - 1).getRestName().equals(
                addItemsToCartModelArrayList.get(position).getRestName())) {
            holder.titleLayout.setVisibility(View.VISIBLE);
        }
        holder.sectionTitle.setText(addItemsToCartModelArrayList.get(position).getRestName());
        holder.itemTitle.setText(addItemsToCartModelArrayList.get(position).getName());
        holder.itemPrice.setText(addItemsToCartModelArrayList.get(position).getPrice());
        holder.noOfItem.setText(addItemsToCartModelArrayList.get(position).getQuantity());

    }

    @Override
    public int getItemCount() {
        return addItemsToCartModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.itemTitle)
        TextView itemTitle;

        @BindView(R.id.itemPrice)
        TextView itemPrice;

        @BindView(R.id.noOfItem)
        TextView noOfItem;

        @BindView(R.id.titleLayout)
        LinearLayout titleLayout;

        @BindView(R.id.sectionTitle)
        TextView sectionTitle;

        @BindView(R.id.removeQuantity)
        ImageView removeQuantity;

        @BindView(R.id.addQuantity)
        ImageView addQuantity;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.removeQuantity)
        void setRemoveQuantity() {
            int quantity = Integer.parseInt(addItemsToCartModelArrayList.get(getAdapterPosition()).getQuantity().trim());
            float price= Float.parseFloat(addItemsToCartModelArrayList.get(getAdapterPosition()).getPrice());

            if (quantity >= 1) {
                quantity = quantity - 1;
                addItemsToCartModelArrayList.get(getAdapterPosition()).setQuantity(String.valueOf(quantity));
//                float totalValue=price*quantity;
                ((EditCartActivity)mContext).updateTotalPrice(price,"remove");

            }
            if(quantity==0){
                addItemsToCartModelArrayList.remove(getAdapterPosition());
            }
            notifyDataSetChanged();
        }

        @OnClick(R.id.addQuantity)
        void setAddQuantity() {
            int quantity = Integer.parseInt(addItemsToCartModelArrayList.get(getAdapterPosition()).getQuantity().trim());
            float price= Float.parseFloat(addItemsToCartModelArrayList.get(getAdapterPosition()).getPrice());
//            float oldValue=price*quantity;
            if (quantity >=1) {
                quantity = quantity + 1;
            }
//            float totalValue=price*quantity;
//            totalValue=totalValue-oldValue;
            ((EditCartActivity)mContext).updateTotalPrice(price,"adding");
            addItemsToCartModelArrayList.get(getAdapterPosition()).setQuantity(String.valueOf(quantity));
            notifyDataSetChanged();
        }
    }
}

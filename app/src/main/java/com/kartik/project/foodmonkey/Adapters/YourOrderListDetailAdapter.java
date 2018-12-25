package com.kartik.project.foodmonkey.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.annotations.SerializedName;
import com.kartik.project.foodmonkey.ApiObject.MenuOrderList;
import com.kartik.project.foodmonkey.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kartikeya on 25/12/2018.
 */

public class YourOrderListDetailAdapter extends RecyclerView.Adapter<YourOrderListDetailAdapter.MyViewHolder> {
    Context mContext;
    List<MenuOrderList> dataList = new ArrayList<>();

    public YourOrderListDetailAdapter(Context mContext, List<MenuOrderList> dataList) {
        this.mContext = mContext;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.simple_order_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.itemName.setText(dataList.get(position).getItemName());
        holder.price.setText("£"+dataList.get(position).getItemPrice());
        holder.quantity.setText("x "+dataList.get(position).getQuantity());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.price)
        TextView price;

        @BindView(R.id.quantity)
        TextView quantity;

        @BindView(R.id.itemName)
        TextView itemName;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

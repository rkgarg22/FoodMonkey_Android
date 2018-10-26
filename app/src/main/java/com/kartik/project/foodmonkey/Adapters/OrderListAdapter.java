package com.kartik.project.foodmonkey.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kartik.project.foodmonkey.Models.InfoModel;
import com.kartik.project.foodmonkey.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kartikeya on 30/09/2018.
 */

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.MyViewHolder> {
    Context mContext;
    ArrayList<InfoModel> arrayList = new ArrayList<>();

    public OrderListAdapter(Context mContext, ArrayList<InfoModel> arrayList) {
        this.mContext = mContext;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.checkout_custom_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.itemName.setText(arrayList.get(position).getTitle());
        holder.noOfItems.setText(arrayList.get(position).getNoOfItem());
        holder.amountOfItem.setText(arrayList.get(position).getDescription());  // Price of Item

        if (position != 0 && position != arrayList.size()) {
            if (!arrayList.get(position).getRestId().equals(arrayList.get(position - 1).getRestId())) {
                holder.restTitle.setText(arrayList.get(position).getRestId());
                holder.restTitle.setVisibility(View.VISIBLE);
            }
        } else if (position == 0) {
            holder.restTitle.setText(arrayList.get(position).getRestId());
            holder.restTitle.setVisibility(View.VISIBLE);

        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.itemName)
        TextView itemName;

        @BindView(R.id.noOfItems)
        TextView noOfItems;

        @BindView(R.id.amountOfItem)
        TextView amountOfItem;

        @BindView(R.id.restTitle)
        TextView restTitle;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

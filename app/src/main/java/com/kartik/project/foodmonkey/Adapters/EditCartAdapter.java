package com.kartik.project.foodmonkey.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kartik.project.foodmonkey.Models.EditCartModel;
import com.kartik.project.foodmonkey.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kartikeya on 30/09/2018.
 */

public class EditCartAdapter extends RecyclerView.Adapter<EditCartAdapter.MyViewHolder> {

    Context mContext;
    ArrayList<EditCartModel> editCartModelArrayList = new ArrayList<>();

    public EditCartAdapter(Context mContext, ArrayList<EditCartModel>  editCartModelArrayList) {
        this.mContext = mContext;
        this.editCartModelArrayList = editCartModelArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.editcart_custom_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.itemTitle.setText(editCartModelArrayList.get(position).getItemTitle());
        holder.itemPrice.setText(editCartModelArrayList.get(position).getPerItemPrice());
        holder.noOfItem.setText(editCartModelArrayList.get(position).getNoOfItems());
    }

    @Override
    public int getItemCount() {
        return editCartModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.itemTitle)
        TextView itemTitle;

        @BindView(R.id.itemPrice)
        TextView itemPrice;

        @BindView(R.id.noOfItem)
        TextView noOfItem;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

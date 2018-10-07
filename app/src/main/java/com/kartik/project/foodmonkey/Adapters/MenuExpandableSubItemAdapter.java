package com.kartik.project.foodmonkey.Adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.kartik.project.foodmonkey.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kartikeya on 07/10/2018.
 */

public class MenuExpandableSubItemAdapter extends RecyclerView.Adapter<MenuExpandableSubItemAdapter.MyViewHolder> {

    Context mContext;

    public MenuExpandableSubItemAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.custom_categories_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.underLine.setVisibility(View.GONE);
        holder.parentLayout.setBackgroundResource(android.R.color.white);
        holder.radioButton.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
        holder.radioButton.setButtonTintList(ColorStateList.valueOf(mContext.getResources().getColor(R.color.colorPrimary)));
        if (position % 2 == 0) {
            holder.radioButton.setText("Extra Hot");
        } else {
            holder.radioButton.setText("Extreme");
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.underline1)
        View underLine;

        @BindView(R.id.parentLayout)
        LinearLayout parentLayout;

        @BindView(R.id.radioItem)
        RadioButton radioButton;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

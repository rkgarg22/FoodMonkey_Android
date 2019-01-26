package com.app.foodMonkey.Adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.app.foodMonkey.ApiObject.MenuAddOnObject;
import com.app.foodMonkey.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kartikeya on 07/10/2018.
 */

public class MenuExpandableSubItemAdapter extends RecyclerView.Adapter<MenuExpandableSubItemAdapter.MyViewHolder> {

    Context mContext;
    ArrayList<MenuAddOnObject> addOn = new ArrayList<>();

    public MenuExpandableSubItemAdapter(Context mContext, ArrayList<MenuAddOnObject> addOn) {
        this.mContext = mContext;
        this.addOn = addOn;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.custom_sub_expand_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.underLine.setVisibility(View.GONE);
        holder.parentLayout.setBackgroundResource(android.R.color.white);
        holder.radioButton.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
        holder.radioButton.setButtonTintList(ColorStateList.valueOf(mContext.getResources().getColor(R.color.colorPrimary)));
//        if (position % 2 == 0) {
        holder.radioButton.setText(addOn.get(position).getAddonName());
        if (addOn.get(position).isSelected()) {
            holder.radioButton.setChecked(true);
        } else {
            holder.radioButton.setChecked(false);
        }

//        holder.radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//
//            }
//        });
//        } else {
//            holder.radioButton.setText("Extreme");
//        }
    }

    @Override
    public int getItemCount() {
        return addOn.size();
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

        @OnClick(R.id.radioItem)
        void setRadioButton() {
            if (addOn.get(getAdapterPosition()).isSelected()) {
                addOn.get(getAdapterPosition()).setSelected(false);
//                ((DetailActivity) mContext).removeDataSubItemAdapter(addOn.get(getAdapterPosition()).getItemId(),
//                        addOn.get(getAdapterPosition()).getAddonName(), addOn.get(getAdapterPosition()).getAddonPrice());
            } else {
                addOn.get(getAdapterPosition()).setSelected(true);
//                ((DetailActivity) mContext).receiveDataSubItemAdapter(addOn.get(getAdapterPosition()).getItemId(),
//                        addOn.get(getAdapterPosition()).getAddonName(), addOn.get(getAdapterPosition()).getAddonPrice());
            }
            notifyDataSetChanged();

        }
    }
}

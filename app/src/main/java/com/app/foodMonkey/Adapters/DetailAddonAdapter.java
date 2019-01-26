package com.app.foodMonkey.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.foodMonkey.ApiObject.MenuObject;
import com.app.foodMonkey.DetailActivity;
import com.app.foodMonkey.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kartikeya on 26/10/2018.
 */

public class DetailAddonAdapter extends RecyclerView.Adapter<DetailAddonAdapter.MyViewHolder> {

    Context mContext;
    ArrayList<MenuObject> menus = new ArrayList<>();

    public DetailAddonAdapter(Context mContext, ArrayList<MenuObject> menus) {
        this.mContext = mContext;
        this.menus = menus;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_child_expandable, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.childItem.setText(menus.get(position).getItemName());
        holder.childItemAmmount.setText("£ " +menus.get(position).getItemPrice());
        if (menus.get(position).getIsItemNonVeg() == 1) {
            holder.vegNonVegIndicator.setImageResource(R.drawable.nonveg_icon);
        } else {
            holder.vegNonVegIndicator.setImageResource(R.drawable.veg_icon);
        }
    }

    @Override
    public int getItemCount() {
        return menus.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.vegNonVegIndicator)
        ImageView vegNonVegIndicator;

        @BindView(R.id.subParent)
        RelativeLayout subParent;

        @BindView(R.id.childItem)
        TextView childItem;

        @BindView(R.id.childItemAmmount)
        TextView childItemAmmount;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.subParent)
        void setSubParent() {
            ((DetailActivity)mContext).setAddItemToCartPopUpVisiblity(menus.get(getAdapterPosition()));
        }
    }
}

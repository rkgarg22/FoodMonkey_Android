package com.app.foodMonkey.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.foodMonkey.ApiObject.MenuSubCategoryObject;
import com.app.foodMonkey.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kartikeya on 28/12/2018.
 */

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.MyViewHolder> {
    Context mContext;
    DetailAddonAdapter detailAddonAdapter;
    ArrayList<MenuSubCategoryObject> menuSubCategory = new ArrayList<>();

    public SubCategoryAdapter(Context mContext, ArrayList<MenuSubCategoryObject> menuSubCategory) {
        this.mContext = mContext;
        this.menuSubCategory = menuSubCategory;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.custom_subcategory, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.header.setText(menuSubCategory.get(position).getMenuSubCateName());
        holder.description.setText(menuSubCategory.get(position).getSubCateInfo());

        detailAddonAdapter = new DetailAddonAdapter(mContext, menuSubCategory.get(position).getMenus());
        holder.menuRecyclerView.setAdapter(detailAddonAdapter);
        if (menuSubCategory.get(position).isSelected2()) {
            holder.menuRecyclerView.setVisibility(View.VISIBLE);
        } else {
            holder.menuRecyclerView.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return menuSubCategory.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.header)
        TextView header;

        @BindView(R.id.description)
        TextView description;

        @BindView(R.id.parentLayout)
        LinearLayout parentLayout;

        @BindView(R.id.menuRecyclerView)
        RecyclerView menuRecyclerView;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            menuRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            menuRecyclerView.setNestedScrollingEnabled(false);

        }

        @OnClick(R.id.parentLayout)
        void setParentLayout() {
            if (menuSubCategory.get(getAdapterPosition()).isSelected2()) {
                menuRecyclerView.setVisibility(View.GONE);
                menuSubCategory.get(getAdapterPosition()).setSelected2(false);
            } else {
                menuSubCategory.get(getAdapterPosition()).setSelected2(true);
                menuRecyclerView.setVisibility(View.VISIBLE);
            }
            notifyDataSetChanged();
        }
    }
}

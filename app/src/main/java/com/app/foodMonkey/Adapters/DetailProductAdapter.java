package com.app.foodMonkey.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.foodMonkey.ApiObject.MenuDetailCategoryObject;
import com.app.foodMonkey.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kartikeya on 26/10/2018.
 */

public class DetailProductAdapter extends RecyclerView.Adapter<DetailProductAdapter.MyViewHolder> {
    Context mContext;
    ArrayList<MenuDetailCategoryObject> menuCategory = new ArrayList<>();
    DetailAddonAdapter detailAddonAdapter;
    SubCategoryAdapter subCategoryAdapter;

    public DetailProductAdapter(Context mContext, ArrayList<MenuDetailCategoryObject> menuCategory) {
        this.mContext = mContext;
        this.menuCategory = menuCategory;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.custom_expandable_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.header.setText(menuCategory.get(position).getMenuCategoryName());
        detailAddonAdapter = new DetailAddonAdapter(mContext, menuCategory.get(position).getMenus());
        subCategoryAdapter = new SubCategoryAdapter(mContext, menuCategory.get(position).getMenuSubCategory());
        holder.menusRecyclerView.setAdapter(detailAddonAdapter);
        holder.categorySubMenuRecyclerView.setAdapter(subCategoryAdapter);
        if (menuCategory.get(position).isSelected()) {
            holder.categorySubMenuRecyclerView.setVisibility(View.VISIBLE);
            holder.menusRecyclerView.setVisibility(View.VISIBLE);
        } else {
            holder.categorySubMenuRecyclerView.setVisibility(View.GONE);
            holder.menusRecyclerView.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return menuCategory.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.header)
        TextView header;

        @BindView(R.id.dropDown)
        ImageView dropDown;

        @BindView(R.id.menusRecyclerView)
        RecyclerView menusRecyclerView;

        @BindView(R.id.categorySubMenuRecyclerView)
        RecyclerView categorySubMenuRecyclerView;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            menusRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            menusRecyclerView.setNestedScrollingEnabled(false);

            categorySubMenuRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            categorySubMenuRecyclerView.setNestedScrollingEnabled(false);
        }

        @OnClick(R.id.header)
        void setHeader() {
            if (menuCategory.get(getAdapterPosition()).isSelected()) {
                menusRecyclerView.setVisibility(View.GONE);
                categorySubMenuRecyclerView.setVisibility(View.GONE);
                menuCategory.get(getAdapterPosition()).setSelected(false);
            } else {
                menuCategory.get(getAdapterPosition()).setSelected(true);
                menusRecyclerView.setVisibility(View.VISIBLE);
                categorySubMenuRecyclerView.setVisibility(View.VISIBLE);
            }
            notifyDataSetChanged();
        }


    }
}

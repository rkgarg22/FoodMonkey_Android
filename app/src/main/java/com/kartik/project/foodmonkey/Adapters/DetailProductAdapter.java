package com.kartik.project.foodmonkey.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kartik.project.foodmonkey.ApiObject.MenuDetailCategoryObject;
import com.kartik.project.foodmonkey.R;

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
        holder.menusRecyclerView.setAdapter(detailAddonAdapter);
        if (menuCategory.get(position).isSelected()) {
            holder.menusRecyclerView.setVisibility(View.VISIBLE);
        } else {
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

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            menusRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            menusRecyclerView.setNestedScrollingEnabled(false);
        }

        @OnClick(R.id.header)
        void setHeader() {
            if (menuCategory.get(getAdapterPosition()).isSelected()) {
                menusRecyclerView.setVisibility(View.GONE);
                menuCategory.get(getAdapterPosition()).setSelected(false);
            } else {
                menuCategory.get(getAdapterPosition()).setSelected(true);
                menusRecyclerView.setVisibility(View.VISIBLE);
            }
            notifyDataSetChanged();
        }


    }
}

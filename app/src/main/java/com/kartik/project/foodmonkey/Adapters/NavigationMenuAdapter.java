package com.kartik.project.foodmonkey.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.kartik.project.foodmonkey.Models.NavigationModel;
import com.kartik.project.foodmonkey.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kartikeya on 23/09/2018.
 */

public class NavigationMenuAdapter extends RecyclerView.Adapter<NavigationMenuAdapter.MyViewHolder> {
    Context mContext;
    ArrayList<NavigationModel> navArrayList = new ArrayList<>();

    public NavigationMenuAdapter(Context mContext, ArrayList<NavigationModel> navArrayList) {
        this.mContext = mContext;
        this.navArrayList = navArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.custom_nav_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.icons.setImageResource(navArrayList.get(position).getIcons());
        holder.userName.setText(navArrayList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return navArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.icons)
        SimpleDraweeView icons;
        @BindView(R.id.userName)
        TextView userName;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

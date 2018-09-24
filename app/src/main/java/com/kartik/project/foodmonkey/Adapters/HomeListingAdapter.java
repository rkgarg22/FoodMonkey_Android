package com.kartik.project.foodmonkey.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kartik.project.foodmonkey.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kartikeya on 25/09/2018.
 */

public class HomeListingAdapter extends RecyclerView.Adapter<HomeListingAdapter.MyViewHolder> {
    Context mContext;

    public HomeListingAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public HomeListingAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.custom_listing_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeListingAdapter.MyViewHolder holder, int position) {
        if (position < 3) {
            holder.sponsoredText.setVisibility(View.VISIBLE);
        } else {
            holder.sponsoredText.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.sponsoredText)
        TextView sponsoredText;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

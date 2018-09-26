package com.kartik.project.foodmonkey.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kartik.project.foodmonkey.DetailActivity;
import com.kartik.project.foodmonkey.R;

import Infrastructure.AppCommon;
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
        if (position == 8) {
            holder.textTitle.setVisibility(View.VISIBLE);
        }
        if (position >= 8) {
            setClosedRestColor(holder);
        }

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, DetailActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.sponsoredText)
        TextView sponsoredText;

        @BindView(R.id.textTitle)
        TextView textTitle;

        @BindView(R.id.displayPic)
        ImageView displayPic;

        @BindView(R.id.distanceText)
        TextView distanceText;

        @BindView(R.id.minSpendText)
        TextView minSpendText;

        @BindView(R.id.deliveryText)
        TextView deliveryText;

        @BindView(R.id.ratingBar)
        RatingBar ratingBar;

        @BindView(R.id.locationIcon)
        ImageView locationIcon;

        @BindView(R.id.parentLayout)
        RelativeLayout parentLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private void setClosedRestColor(MyViewHolder holder) {
        AppCommon.getInstance(mContext).setImageBlack(holder.displayPic);
        holder.distanceText.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
        holder.deliveryText.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
        holder.minSpendText.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
        holder.sponsoredText.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
        holder.locationIcon.setImageTintList(ColorStateList.valueOf(mContext.getResources().getColor(R.color.colorAccent)));
        holder.ratingBar.setProgressTintList(ColorStateList.valueOf(mContext.getResources().getColor(R.color.colorAccent)));
    }


}

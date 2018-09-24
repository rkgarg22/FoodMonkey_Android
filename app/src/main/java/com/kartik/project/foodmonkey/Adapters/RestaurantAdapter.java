package com.kartik.project.foodmonkey.Adapters;

import android.content.Context;
import android.content.Intent;
import android.media.Rating;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.kartik.project.foodmonkey.HomeListingActivity;
import com.kartik.project.foodmonkey.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kartikeya on 24/09/2018.
 */

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.MyViewHolder> {
    Context mContext;
    String comingFor;

    public RestaurantAdapter(Context mContext, String comingFor) {
        this.mContext = mContext;
        this.comingFor = comingFor;
    }

    @NonNull
    @Override
    public RestaurantAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.custom_restaurant_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantAdapter.MyViewHolder holder, int position) {
        if (comingFor.equals(mContext.getResources().getString(R.string.takeOut))) {
            holder.ratingBar.setVisibility(View.GONE);
            holder.reviewsText.setVisibility(View.GONE);
            holder.seeMore.setText("$5.00");
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ratingBar)
        RatingBar ratingBar;

        @BindView(R.id.reviewsText)
        TextView reviewsText;

        @BindView(R.id.seeMore)
        TextView seeMore;


        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.seeMore)
        void setSeeMore() {

        }
    }
}

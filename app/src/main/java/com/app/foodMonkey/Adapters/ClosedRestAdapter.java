package com.app.foodMonkey.Adapters;

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

import com.facebook.drawee.view.SimpleDraweeView;
import com.app.foodMonkey.ApiResponse.CloseResturantObject;
import com.app.foodMonkey.DetailActivity;
import com.app.foodMonkey.R;

import java.util.ArrayList;
import java.util.List;

import Infrastructure.AppCommon;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.app.foodMonkey.API.ServiceGenerator.API_BASE_URL;

/**
 * Created by kartikeya on 21/10/2018.
 */

public class ClosedRestAdapter extends RecyclerView.Adapter<ClosedRestAdapter.MyViewHolder> {
    Context mContext;
    private List<CloseResturantObject> closeResturantObjects = new ArrayList<>();

    public ClosedRestAdapter(Context mContext, List<CloseResturantObject> closeResturantObjects) {
        this.mContext = mContext;
        this.closeResturantObjects = closeResturantObjects;
    }

    public ClosedRestAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.custom_listing_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        String halal="";
        if (closeResturantObjects.get(position).getIsHalal()==1){
            halal="halal";
        }
        holder.displayPic.setImageURI(API_BASE_URL + closeResturantObjects.get(position).getImageLink());
        holder.resturantName.setText(closeResturantObjects.get(position).getRestName());
        holder.cuisineListText.setText(closeResturantObjects.get(position).getCousineList()+", "+halal);
        holder.numberOfReview.setText("" + closeResturantObjects.get(position).getNumberOfReviews());
        holder.deliveryText.setText("£ " + closeResturantObjects.get(position).getDelivery());
        holder.minSpendText.setText("£ " + closeResturantObjects.get(position).getMinSpend());
        holder.distanceText.setText(String.valueOf(closeResturantObjects.get(position).getDistance())+" miles");
        if (!closeResturantObjects.get(position).getAggregateFeedback().equals("0.00"))
            holder.ratingBar.setRating(Float.parseFloat(closeResturantObjects.get(position).getAggregateFeedback())+1);

//        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Toast.makeText(mContext, "Currently under development" + restID, Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(mContext, DetailActivity.class);
//                intent.putExtra("restID", closeResturantObjects.get(holder.getAdapterPosition()).getRestId());
//                intent.putExtra("distance", closeResturantObjects.get(holder.getAdapterPosition()).getDistance());
//                mContext.startActivity(intent);
//
//            }
//        });
        setClosedRestColor(holder);
    }

    @Override
    public int getItemCount() {
        return closeResturantObjects.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.sponsoredText)
        TextView sponsoredText;

        @BindView(R.id.textTitle)
        TextView textTitle;

        @BindView(R.id.titleText)
        TextView resturantName;

        @BindView(R.id.descriptionsText)
        TextView cuisineListText;

        @BindView(R.id.numberOfReview)
        TextView numberOfReview;

        @BindView(R.id.displayPic)
        SimpleDraweeView displayPic;

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

        @BindView(R.id.deliveryIcon)
        ImageView deliveryIcon;

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
        holder.deliveryIcon.setImageTintList(ColorStateList.valueOf(mContext.getResources().getColor(R.color.colorAccent)));
        holder.ratingBar.setProgressTintList(ColorStateList.valueOf(mContext.getResources().getColor(R.color.colorAccent)));
    }
}


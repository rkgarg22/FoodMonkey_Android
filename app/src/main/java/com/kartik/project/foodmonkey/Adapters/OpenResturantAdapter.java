package com.kartik.project.foodmonkey.Adapters;

import android.content.Context;
import android.content.Intent;
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
import com.kartik.project.foodmonkey.ApiResponse.OpenResturantObject;
import com.kartik.project.foodmonkey.DetailActivity;
import com.kartik.project.foodmonkey.R;

import java.util.ArrayList;
import java.util.List;

import Infrastructure.AppCommon;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.kartik.project.foodmonkey.API.ServiceGenerator.API_BASE_URL;

/**
 * Created by kartikeya on 21/10/2018.
 */

public class OpenResturantAdapter extends RecyclerView.Adapter<OpenResturantAdapter.MyViewHolder> {

    Context mContext;
    private List<OpenResturantObject> openResturant = new ArrayList<>();

    public OpenResturantAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public OpenResturantAdapter(Context mContext, List<OpenResturantObject> openResturant) {
        this.mContext = mContext;
        this.openResturant = openResturant;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.custom_listing_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.resturantName.setText(openResturant.get(position).getRestName());
        holder.cuisineListText.setText(openResturant.get(position).getCousineList());
        holder.numberOfReview.setText("" + openResturant.get(position).getNumberOfReviews());
        holder.deliveryText.setText("£ " + openResturant.get(position).getDelivery());
        holder.minSpendText.setText("£ " + openResturant.get(position).getMinSpend());
//        holder.distanceText.setText(openResturant.get(position).getDistance().substring(0, 3) + " miles");
        holder.distanceText.setText(openResturant.get(position).getDistance() + " miles");
        holder.ratingBar.setRating(Float.parseFloat(openResturant.get(position).getAggregateFeedback()));
        holder.displayPic.setController(AppCommon.getDraweeController(holder.displayPic, API_BASE_URL + openResturant.get(position).getImageLink(), 100));

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(mContext, "Currently under development" + restID, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("restID", openResturant.get(position).getRestId());
                mContext.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return openResturant.size();
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
}

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
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.kartik.project.foodmonkey.ApiObject.PreOrderRestObject;
import com.kartik.project.foodmonkey.DetailActivity;
import com.kartik.project.foodmonkey.HomeListingActivity;
import com.kartik.project.foodmonkey.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.kartik.project.foodmonkey.API.ServiceGenerator.API_BASE_URL;

/**
 * Created by kartikeya on 21/10/2018.
 */

public class PreOrderRestAdapter extends RecyclerView.Adapter<PreOrderRestAdapter.MyViewHolder> {
    Context mContext;
    List<PreOrderRestObject> preorderResturant = new ArrayList<>();

    public PreOrderRestAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public PreOrderRestAdapter(Context mContext, List<PreOrderRestObject> preorderResturant) {
        this.mContext = mContext;
        this.preorderResturant = preorderResturant;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.custom_listing_layout, parent, false);
        return new MyViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        if (preorderResturant.get(position) != null)
            holder.displayPic.setImageURI(API_BASE_URL + preorderResturant.get(position).getImageLink());

        holder.resturantName.setText(preorderResturant.get(position).getRestName());
        holder.cuisineListText.setText(preorderResturant.get(position).getCousineList());
        holder.numberOfReview.setText("" + preorderResturant.get(position).getNumberOfReviews());
        holder.deliveryText.setText("£ " + preorderResturant.get(position).getDelivery());
        holder.minSpendText.setText("£ " + preorderResturant.get(position).getMinSpend());
        holder.distanceText.setText(String.valueOf(preorderResturant.get(position).getDistance()).substring(0, 3)+" miles");
        holder.ratingBar.setRating(Float.parseFloat(preorderResturant.get(position).getAggregateFeedback()));

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(mContext, "Currently under development" + restID, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("restID", preorderResturant.get(holder.getAdapterPosition()).getRestId());
                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return preorderResturant.size();
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

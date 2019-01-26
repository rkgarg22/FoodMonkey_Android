package com.app.foodMonkey.Adapters;

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
import com.app.foodMonkey.ApiObject.PreOrderRestObject;
import com.app.foodMonkey.DetailActivity;
import com.app.foodMonkey.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.app.foodMonkey.API.ServiceGenerator.API_BASE_URL;

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
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        String halal="";
        if (preorderResturant.get(position).getIsHalal()==1){
            halal="halal";
        }
        if (preorderResturant.get(position) != null)
            holder.displayPic.setImageURI(API_BASE_URL + preorderResturant.get(position).getImageLink());

        holder.resturantName.setText(preorderResturant.get(position).getRestName());
        holder.cuisineListText.setText(preorderResturant.get(position).getCousineList()+","+halal);
        holder.numberOfReview.setText("(" + preorderResturant.get(position).getNumberOfReviews() + ")");
        holder.deliveryText.setText("£ " + preorderResturant.get(position).getDelivery());
        holder.minSpendText.setText("£ " + preorderResturant.get(position).getMinSpend());
//        if (!preorderResturant.get(position).getDistance().isEmpty())
        holder.distanceText.setText(preorderResturant.get(position).getDistance() + " miles");
//        holder.distanceText.setVisibility(View.GONE);
        if (!preorderResturant.get(position).getAggregateFeedback().equals("0.00"))
            holder.ratingBar.setRating(Float.parseFloat(preorderResturant.get(position).getAggregateFeedback()) + 1);

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(mContext, "Currently under development" + restID, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("restID", preorderResturant.get(holder.getAdapterPosition()).getRestId());
                intent.putExtra("distance", preorderResturant.get(holder.getAdapterPosition()).getDistance());
                intent.putExtra("comingFrom", "preOrderRest");
                intent.putExtra("opening", preorderResturant.get(holder.getAdapterPosition()).getDeliveryStartTime());
                intent.putExtra("minSpend", preorderResturant.get(holder.getAdapterPosition()).getMinSpend());
                mContext.startActivity(intent);

            }
        });

        if (!preorderResturant.get(position).getDelivery().equals("0.00")) {
            holder.deliveryText.setText("£ " + preorderResturant.get(position).getDelivery());
        } else {
            holder.deliveryText.setVisibility(View.GONE);
            holder.delivery.setText(mContext.getString(R.string.freeDelivery));
        }
        if (preorderResturant.get(position).getDiscountOffer() != 0) {
            holder.discountText.setText(preorderResturant.get(position).getDiscountOffer() + "% off");
            holder.discountText.setVisibility(View.VISIBLE);
        }
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

        @BindView(R.id.delivery)
        TextView delivery;

        @BindView(R.id.discountText)
        TextView discountText;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

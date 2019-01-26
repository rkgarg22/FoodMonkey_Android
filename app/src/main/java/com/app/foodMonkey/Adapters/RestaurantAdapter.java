package com.app.foodMonkey.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.app.foodMonkey.ApiObject.HomePopularObject;
import com.app.foodMonkey.DetailActivity;
import com.app.foodMonkey.HomeActivity;
import com.app.foodMonkey.R;

import java.util.ArrayList;

import Infrastructure.AppCommon;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.app.foodMonkey.API.ServiceGenerator.API_BASE_URL;

/**
 * Created by kartikeya on 24/09/2018.
 */

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.MyViewHolder> {
    Context mContext;
    String comingFor;
    ArrayList<HomePopularObject> popularRestaurants = new ArrayList<>();

    public RestaurantAdapter(Context mContext, String comingFor, ArrayList<HomePopularObject> popularRestaurants) {
        this.mContext = mContext;
        this.comingFor = comingFor;
        this.popularRestaurants = popularRestaurants;
    }

    @NonNull
    @Override
    public RestaurantAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (mContext instanceof HomeActivity) {
            view = LayoutInflater.from(mContext).inflate(R.layout.custom_restaurant_layout, parent, false);
        } else {
            view = LayoutInflater.from(mContext).inflate(R.layout.custom_restaurant_listing, parent, false);
        }
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantAdapter.MyViewHolder holder, int position) {
        if (mContext instanceof HomeActivity) {
            holder.title.setText(popularRestaurants.get(position).getRestName());
            holder.displayPic.setImageURI(String.valueOf(API_BASE_URL + popularRestaurants.get(position).getImageLink()));
            holder.reviewsText.setText(popularRestaurants.get(position).getNumberOfReviews() + " reviews");
            if (!popularRestaurants.get(position).getAggregateFeedback().equals("0.00"))
                holder.ratingBar.setRating(Float.parseFloat(popularRestaurants.get(position).getAggregateFeedback()) + 1);
            holder.descriptions.setText(popularRestaurants.get(position).getCousine1() + "," + popularRestaurants.get(position).getCousine2() + "");
        } else {
            holder.title.setText(popularRestaurants.get(position).getRestName());
            holder.displayPic.setController(AppCommon.getDraweeController(holder.displayPic,
                    String.valueOf(API_BASE_URL + popularRestaurants.get(position).getImageLink()), 100));
            if (!popularRestaurants.get(position).getAggregateFeedback().equals("0.00"))
                holder.ratingBar.setRating(Float.parseFloat(popularRestaurants.get(position).getAggregateFeedback()) + 1);
            holder.reviewsText.setText("(" + popularRestaurants.get(position).getNumberOfReviews() + ")");
            holder.descriptions.setText(popularRestaurants.get(position).getCousineList());
            if (!popularRestaurants.get(position).getDelivery().equals("0.00")) {
                holder.deliveryText.setText("£ " + popularRestaurants.get(position).getDelivery());
            } else {
                holder.deliveryText.setVisibility(View.GONE);
                holder.delivery.setText(mContext.getString(R.string.freeDelivery));
            }
            holder.minSpendText.setText("£ " + popularRestaurants.get(position).getMinSpend());
            holder.distanceLayout.setVisibility(View.GONE);
//            holder.distanceText.setText( popularRestaurants.get(position).getDeliveryTime()+" miles");
            if (popularRestaurants.get(position).getIsSponsoredRest() == 1) {
                holder.sponsoredText.setVisibility(View.VISIBLE);
            } else {
                holder.sponsoredText.setVisibility(View.GONE);
            }
            if (popularRestaurants.get(position).getDiscountOffer() != 0) {
                holder.discountText.setVisibility(View.VISIBLE);
                holder.discountText.setText(popularRestaurants.get(position).getDiscountOffer() + "% off");
            }
        }

    }

    @Override
    public int getItemCount() {
//        if (popularRestaurants.size() > 3) {
//            return 3;
//        } else {
        return popularRestaurants.size();
//        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.displayPic)
        SimpleDraweeView displayPic;

        @BindView(R.id.title)
        TextView title;

        @Nullable
        @BindView(R.id.delivery)
        TextView delivery;

        @BindView(R.id.ratingBar)
        RatingBar ratingBar;

        @BindView(R.id.reviewsText)
        TextView reviewsText;

        @BindView(R.id.descriptions)
        TextView descriptions;

        @BindView(R.id.seeMore)
        TextView seeMore;

        @BindView(R.id.distanceLayout)
        LinearLayout distanceLayout;
        /* -------------  Listing Variables ---------- */

        @BindView(R.id.deliveryText)
        TextView deliveryText;

        @BindView(R.id.minSpendText)
        TextView minSpendText;

        @BindView(R.id.distanceText)
        TextView distanceText;

        @BindView(R.id.sponsoredText)
        TextView sponsoredText;

        @Nullable
        @BindView(R.id.discountText)
        TextView discountText;


        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick({R.id.seeMore, R.id.parentLayout})
        void setSeeMore() {
            int restID = popularRestaurants.get(getAdapterPosition()).getRestId();
            String distance = popularRestaurants.get(getAdapterPosition()).getDistance();
//            Toast.makeText(mContext, "Currently under development", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(mContext, DetailActivity.class);
            intent.putExtra("restID", restID);
            intent.putExtra("distance", distance);
            mContext.startActivity(intent);
        }
    }
}

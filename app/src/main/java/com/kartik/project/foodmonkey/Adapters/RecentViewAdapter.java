package com.kartik.project.foodmonkey.Adapters;

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
import com.kartik.project.foodmonkey.ApiObject.HomePopularObject;
import com.kartik.project.foodmonkey.ApiObject.HomeViewedObject;
import com.kartik.project.foodmonkey.DetailActivity;
import com.kartik.project.foodmonkey.HomeActivity;
import com.kartik.project.foodmonkey.R;

import java.util.ArrayList;

import Infrastructure.AppCommon;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kartikeya on 10/10/2018.
 */

public class RecentViewAdapter extends RecyclerView.Adapter<RecentViewAdapter.MyViewHolder> {
    Context mContext;
    String comingFor;
    ArrayList<HomeViewedObject> viewedObjects = new ArrayList<>();

    public RecentViewAdapter(Context mContext, String comingFor, ArrayList<HomeViewedObject> viewedObjects) {
        this.mContext = mContext;
        this.comingFor = comingFor;
        this.viewedObjects = viewedObjects;
    }

    @NonNull
    @Override
    public RecentViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (mContext instanceof HomeActivity) {
            view = LayoutInflater.from(mContext).inflate(R.layout.custom_restaurant_layout, parent, false);
        } else {
            view = LayoutInflater.from(mContext).inflate(R.layout.custom_restaurant_listing, parent, false);
        }
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentViewAdapter.MyViewHolder holder, int position) {
        if (comingFor.equals(mContext.getResources().getString(R.string.takeOut))) {
//            holder.ratingBar.setVisibility(View.GONE);
//            holder.reviewsText.setVisibility(View.GONE);
            holder.descriptions.setVisibility(viewedObjects.get(position).getDeliveryAreaCovered());
            holder.seeMore.setText(viewedObjects.get(position).getMinSpend());//"$5.00");
            holder.title.setText(viewedObjects.get(position).getRestName());
            holder.displayPic.setController(AppCommon.getDraweeController(holder.displayPic, "http://food-monkey.com" + viewedObjects.get(position).getImageLink(), 200));
            holder.reviewsText.setText(viewedObjects.get(position).getNumberOfReviews() + " reviews");
            holder.ratingBar.setRating(Float.parseFloat(viewedObjects.get(position).getAggregateFeedback()));
        }else {
            holder.title.setText(viewedObjects.get(position).getRestName());
            holder.displayPic.setController(AppCommon.getDraweeController(holder.displayPic,
                    String.valueOf("http://food-monkey.com" + viewedObjects.get(position).getImageLink()),100));

            holder.ratingBar.setRating(Float.parseFloat(viewedObjects.get(position).getAggregateFeedback()));
            holder.reviewsText.setText("("+viewedObjects.get(position).getNumberOfReviews() + ")");
            holder.descriptions.setVisibility(View.GONE);
//            holder.descriptions.setText(viewedObjects.get(position).getCousine1() + viewedObjects.get(position).getCousine2() + "");
            holder.deliveryText.setText( viewedObjects.get(position).getDelivery());
            holder.minSpendText.setText("£ " + viewedObjects.get(position).getMinSpend());
//            holder.distanceText.setText( viewedObjects.get(position).getDeliveryTime()+" miles");
            holder.distanceLayout.setVisibility(View.GONE);
            if (viewedObjects.get(position).getIsSponsoredRest()==1){
                holder.sponsoredText.setVisibility(View.VISIBLE);
            }else {
                holder.sponsoredText.setVisibility(View.GONE);
            }

            if (!viewedObjects.get(position).getDelivery().equals("0.00")) {
                holder.deliveryText.setText("£ " + viewedObjects.get(position).getDelivery());
            } else {
                holder.deliveryText.setVisibility(View.GONE);
                holder.delivery.setText(mContext.getString(R.string.freeDelivery));
            }
            if (viewedObjects.get(position).getDiscountOffer() != 0) {
                holder.discountText.setVisibility(View.VISIBLE);
                holder.discountText.setText(viewedObjects.get(position).getDiscountOffer() + "% off");
            }
        }
    }

    @Override
    public int getItemCount() {
        return viewedObjects.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ratingBar)
        RatingBar ratingBar;

        @BindView(R.id.reviewsText)
        TextView reviewsText;

        @BindView(R.id.seeMore)
        TextView seeMore;

        @BindView(R.id.title)
        TextView title;

        @BindView(R.id.descriptions)
        TextView descriptions;

        @BindView(R.id.displayPic)
        SimpleDraweeView displayPic;
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

        @Nullable
        @BindView(R.id.delivery)
        TextView delivery;

        @BindView(R.id.distanceLayout)
        LinearLayout distanceLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.seeMore)
        void setSeeMore() {
            int restID = viewedObjects.get(getAdapterPosition()).getRestId();
//            Toast.makeText(mContext, "Currently under development", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(mContext, DetailActivity.class);
            intent.putExtra("restID", restID);
            mContext.startActivity(intent);
        }
    }
}
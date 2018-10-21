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
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.kartik.project.foodmonkey.ApiObject.PreOrderRestObject;
import com.kartik.project.foodmonkey.ApiObject.RestutantListObject;
import com.kartik.project.foodmonkey.ApiResponse.CloseResturantObject;
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
 * Created by kartikeya on 25/09/2018.
 */

public class HomeListingAdapter extends RecyclerView.Adapter<HomeListingAdapter.MyViewHolder> {
    Context mContext;
    private List<OpenResturantObject> openResturant = new ArrayList<>();
    private List<PreOrderRestObject> preorderResturant = new ArrayList<>();
    private List<CloseResturantObject> closeResturant = new ArrayList<>();
    int selectedPosition;
    int exactPosition;

    public HomeListingAdapter(Context mContext, RestutantListObject restutantList) {
        this.mContext = mContext;
        openResturant = restutantList.getOpenResturant();
        preorderResturant = restutantList.getPreorderResturant();
        closeResturant = restutantList.getCloseResturant();
    }

    @NonNull
    @Override
    public HomeListingAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.custom_listing_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final HomeListingAdapter.MyViewHolder holder, final int i) {
        fetchingExactID(i, holder);

//        if (position == 3) {
//            holder.sponsoredText.setVisibility(View.VISIBLE);
//        } else {
//            holder.sponsoredText.setVisibility(View.GONE);
//        }
//        if (preorderResturant.size() != 0) {
//            if (position == openResturant.size() + 1) {
//                holder.textTitle.setText(preorderResturant.get());
//                holder.textTitle.setVisibility(View.VISIBLE);
//            }
//        }
//
//        if (position >= 8) {
//            setClosedRestColor(holder);
//        }

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int restID = fetchingExactID(i, holder);
                Toast.makeText(mContext, "Currently under development" + restID, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("restID", restID);
                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        int listSize = openResturant.size() + preorderResturant.size() + closeResturant.size();
        return listSize;
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


    int fetchingExactID(int position, MyViewHolder holder) {
        int resturantID = 0;
        if (position >= openResturant.size())  // preOrder position here
        {
            if (position == openResturant.size() && !preorderResturant.isEmpty()) {
                holder.textTitle.setVisibility(View.VISIBLE);
                holder.textTitle.setText(preorderResturant.size() + " " + mContext.getString(R.string.restaurantTakingPreOrder));
            }
            exactPosition = position - openResturant.size();
            if (position >= openResturant.size() + preorderResturant.size()) // closed resturant position
            {
                exactPosition = position - openResturant.size() - preorderResturant.size();
                if (position == openResturant.size() + preorderResturant.size() && !closeResturant.isEmpty()) {
                    holder.textTitle.setVisibility(View.VISIBLE);
                    holder.textTitle.setText(mContext.getString(R.string.closedRestaurents));
                }
                if (closeResturant.get(exactPosition) != null) {
                    setClosedRestColor(holder);
                    holder.displayPic.setImageURI(API_BASE_URL + closeResturant.get(exactPosition).getImageLink());
                    holder.resturantName.setText(closeResturant.get(exactPosition).getRestName());
                    holder.cuisineListText.setText(closeResturant.get(exactPosition).getCousineList());
                    holder.numberOfReview.setText("" + closeResturant.get(exactPosition).getNumberOfReviews());
                    holder.deliveryText.setText("£ " + closeResturant.get(exactPosition).getDelivery());
                    holder.minSpendText.setText("£ " + closeResturant.get(exactPosition).getMinSpend());
                    holder.distanceText.setText(closeResturant.get(exactPosition).getDistance() + " miles");
                    holder.ratingBar.setRating(Float.parseFloat(closeResturant.get(exactPosition).getAggregateFeedback()));
                    resturantID = closeResturant.get(exactPosition).getRestId();

                } else {
                    holder.parentLayout.setVisibility(View.GONE);
                }
            } else {
                if (preorderResturant.get(exactPosition) != null)
                    holder.displayPic.setImageURI(API_BASE_URL + preorderResturant.get(exactPosition).getImageLink());

                holder.resturantName.setText(preorderResturant.get(exactPosition).getRestName());
                holder.cuisineListText.setText(preorderResturant.get(exactPosition).getCousineList());
                holder.numberOfReview.setText("" + preorderResturant.get(exactPosition).getNumberOfReviews());
                holder.deliveryText.setText("£ " + preorderResturant.get(exactPosition).getDelivery());
                holder.minSpendText.setText("£ " + preorderResturant.get(exactPosition).getMinSpend());
                holder.distanceText.setText(preorderResturant.get(exactPosition).getDistance() + " miles");
                holder.ratingBar.setRating(Float.parseFloat(preorderResturant.get(exactPosition).getAggregateFeedback()));
                resturantID = preorderResturant.get(exactPosition).getRestId();
            }
        } else {
            exactPosition = position;
            if (openResturant.get(exactPosition) != null)
                holder.displayPic.setImageURI(API_BASE_URL + openResturant.get(exactPosition).getImageLink());

            holder.resturantName.setText(openResturant.get(exactPosition).getRestName());
            holder.cuisineListText.setText(openResturant.get(exactPosition).getCousineList());
            holder.numberOfReview.setText("" + openResturant.get(exactPosition).getNumberOfReviews());
            holder.deliveryText.setText("£ " + openResturant.get(exactPosition).getDelivery());
            holder.minSpendText.setText("£ " + openResturant.get(exactPosition).getMinSpend());
            holder.distanceText.setText(openResturant.get(exactPosition).getDistance().substring(0, 3) + " miles");
            holder.ratingBar.setRating(Float.parseFloat(openResturant.get(exactPosition).getAggregateFeedback()));
            resturantID = openResturant.get(exactPosition).getRestId();
        }
        return resturantID;
    }


}

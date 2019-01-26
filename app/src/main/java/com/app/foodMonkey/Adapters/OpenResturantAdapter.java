package com.app.foodMonkey.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.app.foodMonkey.ApiResponse.OpenResturantObject;
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
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        String halal="";
        if (openResturant.get(position).getIsHalal()==1){
            halal="halal";
        }
        holder.resturantName.setText(openResturant.get(position).getRestName());
        holder.cuisineListText.setText(openResturant.get(position).getCousineList()+", "+halal);
        holder.numberOfReview.setText("(" + openResturant.get(position).getNumberOfReviews() + ")");
//        holder.deliveryText.setText("£ " + openResturant.get(position).getDelivery());
        holder.minSpendText.setText("£ " + openResturant.get(position).getMinSpend());
        holder.distanceLayout.setVisibility(View.VISIBLE);
        holder.distanceText.setText(openResturant.get(position).getDistance() + " miles");
        holder.distanceText.setVisibility(View.VISIBLE);
        if (!openResturant.get(position).getAggregateFeedback().equals("0.00"))
            holder.ratingBar.setRating(Float.parseFloat(openResturant.get(position).getAggregateFeedback()) + 1);
        holder.displayPic.setController(AppCommon.getDraweeController(holder.displayPic, API_BASE_URL + openResturant.get(position).getImageLink(), 100));

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(mContext, "Currently under development" + restID, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("restID", openResturant.get(holder.getAdapterPosition()).getRestId());
                intent.putExtra("distance", openResturant.get(holder.getAdapterPosition()).getDistance());
                mContext.startActivity(intent);

            }
        });
        if (openResturant.get(position).getIsSponsoredRest() == 1) {
            holder.sponsoredText.setVisibility(View.VISIBLE);
        } else {
            holder.sponsoredText.setVisibility(View.GONE);
        }
        if (openResturant.get(position).getDiscountOffer() != 0) {
            holder.discountText.setText(openResturant.get(position).getDiscountOffer() + "% off");
            holder.discountText.setVisibility(View.VISIBLE);
        }

        if (!openResturant.get(position).getDelivery().equals("0.00")) {
            holder.deliveryText.setText("£ " + openResturant.get(position).getDelivery());
        } else {
            holder.deliveryText.setVisibility(View.GONE);
            holder.delivery.setText(mContext.getString(R.string.freeDelivery));
        }
        if (openResturant.get(position).getDeliveryOption().equals("Takeaway")) {
            holder.deliveryIcon.setImageResource(R.drawable.bag);
            holder.delivery.setVisibility(View.GONE);
            holder.deliveryText.setText("COLLECT NOW");
            holder.deliveryText.setVisibility(View.VISIBLE);
//            holder.minSpend.setText("OPENING AT " + openResturant.get(position).getCollection_From_Time());
            holder.minSpendText.setVisibility(View.GONE);
            holder.minSpend.setVisibility(View.GONE);
        }

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

        @BindView(R.id.discountText)
        TextView discountText;

        @BindView(R.id.ratingBar)
        RatingBar ratingBar;

        @BindView(R.id.locationIcon)
        ImageView locationIcon;

        @BindView(R.id.deliveryIcon)
        ImageView deliveryIcon;

        @BindView(R.id.parentLayout)
        RelativeLayout parentLayout;

        @BindView(R.id.distanceLayout)
        LinearLayout distanceLayout;

        @BindView(R.id.delivery)
        TextView delivery;

        @BindView(R.id.minSpend)
        TextView minSpend;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

package com.kartik.project.foodmonkey.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.kartik.project.foodmonkey.ApiObject.HomePopularObject;
import com.kartik.project.foodmonkey.DetailActivity;
import com.kartik.project.foodmonkey.R;

import java.util.ArrayList;

import Infrastructure.AppCommon;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.kartik.project.foodmonkey.API.ServiceGenerator.API_BASE_URL;

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
        View view = LayoutInflater.from(mContext).inflate(R.layout.custom_restaurant_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantAdapter.MyViewHolder holder, int position) {
        holder.title.setText(popularRestaurants.get(position).getRestName());
        holder.displayPic.setImageURI(String.valueOf(API_BASE_URL + popularRestaurants.get(position).getImageLink()));
        holder.reviewsText.setText(popularRestaurants.get(position).getNumberOfReviews() + " reviews");
        holder.descriptions.setText(popularRestaurants.get(position).getCousine1() + popularRestaurants.get(position).getCousine2()+"");
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

        @BindView(R.id.ratingBar)
        RatingBar ratingBar;

        @BindView(R.id.reviewsText)
        TextView reviewsText;

        @BindView(R.id.descriptions)
        TextView descriptions;

        @BindView(R.id.seeMore)
        TextView seeMore;


        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick({R.id.seeMore, R.id.parentLayout})
        void setSeeMore() {
            int restID = popularRestaurants.get(getAdapterPosition()).getRestId();
//            Toast.makeText(mContext, "Currently under development", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(mContext, DetailActivity.class);
            intent.putExtra("restID", restID);
            mContext.startActivity(intent);
        }
    }
}

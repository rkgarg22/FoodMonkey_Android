package com.kartik.project.foodmonkey.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.kartik.project.foodmonkey.ApiObject.HomePopularObject;
import com.kartik.project.foodmonkey.ApiObject.HomeViewedObject;
import com.kartik.project.foodmonkey.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kartikeya on 10/10/2018.
 */

public class RecentViewAdapter extends RecyclerView.Adapter<RecentViewAdapter.MyViewHolder> {
    Context mContext;
    String comingFor;
    ArrayList<HomeViewedObject> viewedObjects=new ArrayList<>();

    public RecentViewAdapter(Context mContext, String comingFor, ArrayList<HomeViewedObject> viewedObjects) {
        this.mContext = mContext;
        this.comingFor = comingFor;
        this.viewedObjects = viewedObjects;
    }

    @NonNull
    @Override
    public RecentViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.custom_restaurant_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentViewAdapter.MyViewHolder holder, int position) {
        if (comingFor.equals(mContext.getResources().getString(R.string.takeOut))) {
            holder.ratingBar.setVisibility(View.GONE);
            holder.reviewsText.setVisibility(View.GONE);
            holder.seeMore.setText("$5.00");
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


        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.seeMore)
        void setSeeMore() {

        }
    }
}
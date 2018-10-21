package com.kartik.project.foodmonkey.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.kartik.project.foodmonkey.ApiObject.HomeOrderedObject;
import com.kartik.project.foodmonkey.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kartikeya on 10/10/2018.
 */

public class OrderAdapter  extends RecyclerView.Adapter<OrderAdapter.MyViewHolder> {
    Context mContext;
    String comingFor;
    ArrayList<HomeOrderedObject> homeOrderedObjects=new ArrayList<>();
    public OrderAdapter(Context mContext, String comingFor, ArrayList<HomeOrderedObject> homeOrderedObjects) {
        this.mContext = mContext;
        this.comingFor = comingFor;
        this.homeOrderedObjects = homeOrderedObjects;

    }

    @NonNull
    @Override
    public OrderAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.custom_restaurant_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.MyViewHolder holder, int position) {
        if (comingFor.equals(mContext.getResources().getString(R.string.takeOut))) {
            holder.ratingBar.setVisibility(View.GONE);
            holder.reviewsText.setVisibility(View.GONE);
            holder.seeMore.setText("$5.00");
        }
    }

    @Override
    public int getItemCount() {
        return homeOrderedObjects.size();
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

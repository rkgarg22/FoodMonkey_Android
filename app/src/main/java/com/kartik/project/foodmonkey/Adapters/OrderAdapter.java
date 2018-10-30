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
import com.kartik.project.foodmonkey.ApiObject.HomeOrderedObject;
import com.kartik.project.foodmonkey.DetailActivity;
import com.kartik.project.foodmonkey.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.kartik.project.foodmonkey.API.ServiceGenerator.API_BASE_URL;

/**
 * Created by kartikeya on 10/10/2018.
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder> {
    Context mContext;
    String comingFor;
    ArrayList<HomeOrderedObject> homeOrderedObjects = new ArrayList<>();

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
//        holder.displayPic.setImageURI(homeOrderedObjects.get(position).getImageLink());
        holder.ratingBar.setVisibility(View.GONE);
        holder.reviewsText.setVisibility(View.GONE);
        holder.seeMore.setText("$"+homeOrderedObjects.get(position).getMinSpend());
        holder.title.setText(homeOrderedObjects.get(position).getRestName());
        holder.displayPic.setImageURI(String.valueOf(API_BASE_URL + homeOrderedObjects.get(position).getImageLink()));
        holder.reviewsText.setText(homeOrderedObjects.get(position).getNumberOfReviews() + " reviews");
        holder.descriptions.setText(homeOrderedObjects.get(position).getCousine1() + homeOrderedObjects.get(position).getCousine2() + "");
    }

    @Override
    public int getItemCount() {
        return homeOrderedObjects.size();
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

        @OnClick({R.id.seeMore,R.id.parentLayout})
        void setSeeMore() {
            int restID = homeOrderedObjects.get(getAdapterPosition()).getRestId();
//            Toast.makeText(mContext, "Currently under development", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(mContext, DetailActivity.class);
            intent.putExtra("restID", restID);
            mContext.startActivity(intent);
        }
    }
}

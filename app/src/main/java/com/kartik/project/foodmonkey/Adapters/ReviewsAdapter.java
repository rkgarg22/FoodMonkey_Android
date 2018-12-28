package com.kartik.project.foodmonkey.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.kartik.project.foodmonkey.ApiObject.ResturantFeedbackList;
import com.kartik.project.foodmonkey.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kartikeya on 29/09/2018.
 */

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.MyViewHolder> {
    Context mContext;
    List<ResturantFeedbackList> resturantFeedbackList = new ArrayList<>();

    public ReviewsAdapter(Context mContext, List<ResturantFeedbackList> resturantFeedbackList) {
        this.mContext = mContext;
        this.resturantFeedbackList = resturantFeedbackList;
    }

    @NonNull
    @Override
    public ReviewsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.custom_review_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewsAdapter.MyViewHolder holder, int position) {
        holder.name.setText(resturantFeedbackList.get(position).getCustomerName());
        holder.dateText.setText(changeDateFormat(resturantFeedbackList.get(position).getRatingDate()));
        holder.descriptionText.setText(resturantFeedbackList.get(position).getComments());
        holder.ratingBar.setRating(Float.parseFloat(resturantFeedbackList.get(position).getNumberOfStars()));
    }

    @Override
    public int getItemCount() {
        return resturantFeedbackList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.dateText)
        TextView dateText;
        @BindView(R.id.ratingBar)
        RatingBar ratingBar;
        @BindView(R.id.descriptionText)
        TextView descriptionText;


        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    public String changeDateFormat(String time) {
        String inputPattern = "yyyy-MM-dd HH:mm:ss";
        String outputPattern = "dd/MM/yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }
}

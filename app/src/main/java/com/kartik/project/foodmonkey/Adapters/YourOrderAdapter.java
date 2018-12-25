package com.kartik.project.foodmonkey.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.kartik.project.foodmonkey.ApiObject.CustomerOrdersList;
import com.kartik.project.foodmonkey.ApiObject.OrderListDetailObj;
import com.kartik.project.foodmonkey.R;
import com.kartik.project.foodmonkey.YourOrderDetailActivity;
import com.kartik.project.foodmonkey.YourOrderListingActivty;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Infrastructure.AppCommon;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kartikeya on 22/12/2018.
 */

public class YourOrderAdapter extends RecyclerView.Adapter<YourOrderAdapter.Holder> {
    Context mContext;
    ArrayList<OrderListDetailObj> orderList = new ArrayList();

    public YourOrderAdapter(Context mContext, ArrayList<OrderListDetailObj> orderList) {
        this.mContext = mContext;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.custom_order_list, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.title.setText(orderList.get(position).getRestName());
        holder.ratingBar.setRating(Float.parseFloat(orderList.get(position).getAggregateFeedback()));
        holder.dateText.setText(parseDateToddMMyyyy(orderList.get(position).getOrderDateTime()));
        holder.amountText.setText("£" + orderList.get(position).getOrderAmount());
        holder.reviewsText.setText("(" + orderList.get(position).getNumberOfReviews() + ")");
        holder.orderID.setText("Order ID:" + orderList.get(position).getOrderId());
        holder.displayPic.setController(AppCommon.getDraweeController(holder.displayPic, "http://food-monkey.com" +
                orderList.get(position).getRestImageLink(), 200));
        holder.descriptions.setText(orderList.get(position).getCousine1() + "," + orderList.get(position).getCousine2());

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.displayPic)
        SimpleDraweeView displayPic;

        @BindView(R.id.title)
        TextView title;

        @BindView(R.id.orderID)
        TextView orderID;

        @BindView(R.id.ratingBar)
        RatingBar ratingBar;

        @BindView(R.id.reviewsText)
        TextView reviewsText;

        @BindView(R.id.descriptions)
        TextView descriptions;

        @BindView(R.id.amountText)
        TextView amountText;

        @BindView(R.id.viewDetail)
        TextView viewDetail;

        @BindView(R.id.dateText)
        TextView dateText;


        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.viewDetail)
        void setViewDetail() {
            Intent intent = new Intent(mContext, YourOrderDetailActivity.class);
            Bundle bundle=new Bundle();
            bundle.putSerializable("data",orderList.get(getAdapterPosition()));
            intent.putExtras(bundle);
            mContext.startActivity(intent);
        }
    }

    public String parseDateToddMMyyyy(String inputDate) {
        String inputPattern = "yyyy-MM-dd HH:mm:ss";   //2018-12-02 21:22:47"
        String outputPattern = "dd/MM/yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(inputDate);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }
}

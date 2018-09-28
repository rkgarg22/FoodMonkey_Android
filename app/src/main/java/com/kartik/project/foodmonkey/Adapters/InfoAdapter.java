package com.kartik.project.foodmonkey.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kartik.project.foodmonkey.Models.InfoModel;
import com.kartik.project.foodmonkey.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kartikeya on 29/09/2018.
 */

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.MyViewHolder> {
    Context mContext;
    ArrayList<InfoModel> arrayList = new ArrayList<>();

    public InfoAdapter(Context mContext, ArrayList<InfoModel>  arrayList) {
        this.mContext = mContext;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.info_custom_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.titleText.setText(arrayList.get(position).getTitle());
        holder.descriptionText.setText(arrayList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.titleText)
        TextView titleText;

        @BindView(R.id.descriptionText)
        TextView descriptionText;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

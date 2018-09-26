package com.kartik.project.foodmonkey.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.kartik.project.foodmonkey.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kartikeya on 27/09/2018.
 */

public class PopUpAdapter extends RecyclerView.Adapter<PopUpAdapter.MyViewHolder> {
    Context mContext;
    ArrayList<String> arrayList = new ArrayList<>();

    public PopUpAdapter(Context mContext, ArrayList<String> arrayList) {
        this.mContext = mContext;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public PopUpAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.custom_categories_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopUpAdapter.MyViewHolder holder, int position) {
        holder.radioItem.setText(arrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.radioItem)
        RadioButton radioItem;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.radioItem)
        void setRadioItem() {
            int position = getAdapterPosition();
//            Not completed this stuff
//            ArrayList<String> selectedRadioList = new ArrayList<>();
//            for (int i = 0; i < arrayList.size(); i++) {
//                selectedRadioList.add(i, String.valueOf(radioItem.getText()));
//            }
        }

    }
}

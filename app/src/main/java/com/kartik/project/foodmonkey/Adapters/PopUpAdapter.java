package com.kartik.project.foodmonkey.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.kartik.project.foodmonkey.ApiObject.CuisinesListObject;
import com.kartik.project.foodmonkey.HomeListingActivity;
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
    ArrayList<CuisinesListObject> arrayList = new ArrayList<>();
    int lastPostionClicked;

    public PopUpAdapter(Context mContext, ArrayList<CuisinesListObject> arrayList) {
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
        if (position == 0) {
            holder.radioItem.setText(arrayList.get(position).getCuisineName());
        } else
            holder.radioItem.setText(arrayList.get(position).getCuisineName() + " (" + arrayList.get(position).getResturantCount() + ")");
        if (arrayList.get(position).isSelected()) {
            holder.radioItem.setChecked(true);
        } else {
            holder.radioItem.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.radioItem)
        RadioButton radioItem;

        @BindView(R.id.noOfItem)
        TextView noOfItem;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.radioItem)
        void setRadioItem() {
            int position = getAdapterPosition();
            if (arrayList.get(position).isSelected()) {
                radioItem.setChecked(false);
                arrayList.get(position).setSelected(false);
            } else {
                radioItem.setChecked(true);
                arrayList.get(position).setSelected(true);
                for (int i = 0; i < arrayList.size(); i++) {
                    if (i != position) {
                        arrayList.get(i).setSelected(false);
                    }
                }
                ((HomeListingActivity) mContext).setCuisineSelectedID(position);
            }

            notifyDataSetChanged();
//            Not completed this stuff
//            ArrayList<String> selectedRadioList = new ArrayList<>();
//            for (int i = 0; i < arrayList.size(); i++) {
//                selectedRadioList.add(i, String.valueOf(radioItem.getText()));
//            }
        }
    }
}

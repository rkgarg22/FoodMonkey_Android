package com.kartik.project.foodmonkey.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.kartik.project.foodmonkey.Models.AddAddressModel;
import com.kartik.project.foodmonkey.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kartikeya on 01/10/2018.
 */

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.MyViewHolder> {

    Context mContext;
    private RadioButton lastCheckedRB = null;
    ArrayList<AddAddressModel> addAddressModelArrayList = new ArrayList<>();

    public AddressAdapter(Context mContext, ArrayList<AddAddressModel> addAddressModelArrayList) {
        this.mContext = mContext;
        this.addAddressModelArrayList = addAddressModelArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.add_address_custom_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        holder.addressFull.setText(addAddressModelArrayList.get(position).getAddressFull());
        holder.addressTitle.setText(addAddressModelArrayList.get(position).getAddressTitle());
        holder.addressFull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addAddressModelArrayList.get(holder.getAdapterPosition()).isSelected()) {
                    holder.addressFull.setChecked(false);
                    addAddressModelArrayList.get(holder.getAdapterPosition()).setSelected(false);
                }else {
                    holder.addressFull.setChecked(true);
                    addAddressModelArrayList.get(holder.getAdapterPosition()).setSelected(true);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return addAddressModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.addressTitle)
        TextView addressTitle;

        @BindView(R.id.addressFull)
        RadioButton addressFull;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

package com.kartik.project.foodmonkey.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.kartik.project.foodmonkey.ApiObject.CustomerObject;
import com.kartik.project.foodmonkey.ManagerAddressActivity;
import com.kartik.project.foodmonkey.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kartikeya on 03/12/2018.
 */

public class ManageAddressAdapter extends RecyclerView.Adapter<ManageAddressAdapter.MyViewHolder> {
    Context mContext;
    CustomerObject customerObject;

    public ManageAddressAdapter(Context mContext, CustomerObject customerObject) {
        this.mContext = mContext;
        this.customerObject = customerObject;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.custom_manage_address, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.header.setText(customerObject.getAddresses().get(position).getAddressName());
        holder.name.setText(customerObject.getFirstName() + " " + customerObject.getSurName());
        holder.descriptions.setText(customerObject.getAddresses().get(position).getStreetLine1() +
                ", " + customerObject.getAddresses().get(position).getStreetLine2() + ", " +
                ", " + customerObject.getAddresses().get(position).getPostCode() + ", " +
                customerObject.getAddresses().get(position).getCity());

    }

    @Override
    public int getItemCount() {
        return customerObject.getAddresses().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name)
        TextView name;

        @BindView(R.id.descriptions)
        TextView descriptions;

        @BindView(R.id.header)
        TextView header;

        @BindView(R.id.edit)
        TextView edit;

        @BindView(R.id.delete)
        TextView delete;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.edit)
        void setEdit() {
            ((ManagerAddressActivity)mContext).OnEditClick(customerObject, getAdapterPosition());
//            Toast.makeText(mContext, "Currently under Development", Toast.LENGTH_SHORT).show();
        }
        @OnClick(R.id.delete)
        void setDelete() {
            ((ManagerAddressActivity)mContext).OnDeleteClick(customerObject, getAdapterPosition());
        }
    }
}

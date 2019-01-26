package com.app.foodMonkey.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.app.foodMonkey.ApiObject.CustomerAddressObject;
import com.app.foodMonkey.CheckOutActivity;
import com.app.foodMonkey.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kartikeya on 01/10/2018.
 */

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.MyViewHolder> {

    Context mContext;
    //    private RadioButton lastCheckedRB = null;
    List<CustomerAddressObject> customerAddresses = new ArrayList<>();
//    ArrayList<AddAddressModel> addAddressModelArrayList = new ArrayList<>();

    public AddressAdapter(Context mContext, List<CustomerAddressObject> customerAddresses) {
        this.mContext = mContext;
        this.customerAddresses = customerAddresses;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.add_address_custom_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        String streetLine1 = customerAddresses.get(position).getStreetLine1();
        String streetLine2 = customerAddresses.get(position).getStreetLine2();
        String postalCode = customerAddresses.get(position).getPostCode();
        String houseNo = String.valueOf(customerAddresses.get(position).getHouseNo());
        String city = customerAddresses.get(position).getCity();

        holder.addressFull.setText(houseNo + "," + streetLine1 + "," + streetLine2 + "," + postalCode + "," + city);
        holder.addressTitle.setText(customerAddresses.get(position).getAddressName());
        if (customerAddresses.get(position).isSelected()) {
            holder.addressFull.setChecked(true);
        } else {
            holder.addressFull.setChecked(false);
        }

    }

    @Override
    public int getItemCount() {
        return customerAddresses.size();
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

        @OnClick(R.id.addressFull)
        void setAddressFull() {
            addressFull.setChecked(true);
            for (int i = 0; i < customerAddresses.size(); i++) {
                if (getAdapterPosition() != i) {
                    customerAddresses.get(i).setSelected(false);
                } else {
                    customerAddresses.get(i).setSelected(true);
                }
            }
            ((CheckOutActivity)mContext).setAddressID(customerAddresses.get(getAdapterPosition()).getAddressId());
//            if (customerAddresses.get(getAdapterPosition()).isSelected()) {
//                addressFull.setChecked(false);
//                customerAddresses.get(getAdapterPosition()).setSelected(false);
//            } else {
//                addressFull.setChecked(true);
//                customerAddresses.get(getAdapterPosition()).setSelected(true);
//            }
            notifyDataSetChanged();
        }
    }
}

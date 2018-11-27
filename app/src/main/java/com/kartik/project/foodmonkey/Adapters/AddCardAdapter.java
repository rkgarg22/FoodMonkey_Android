package com.kartik.project.foodmonkey.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kartik.project.foodmonkey.ApiObject.CardListObject;
import com.kartik.project.foodmonkey.ApiObject.StripeDataObject;
import com.kartik.project.foodmonkey.CompletePaymentActivity;
import com.kartik.project.foodmonkey.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kartikeya on 09/10/2018.
 */

public class AddCardAdapter extends RecyclerView.Adapter<AddCardAdapter.MyViewHolder> {

    Context mContext;
    List<StripeDataObject> cardList = new ArrayList<>();

    public AddCardAdapter(Context mContext, List<StripeDataObject> cardList) {
        this.mContext = mContext;
        this.cardList = cardList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.custom_add_card, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
//        holder.outerLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                holder.detailLayout.setVisibility(View.VISIBLE);
//                holder.outerLayout.setVisibility(View.GONE);
//            }
//        });
//
//        holder.selectCardLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                holder.outerLayout.setVisibility(View.VISIBLE);
//                holder.detailLayout.setVisibility(View.GONE);
//            }
//        });
//
//        holder.cvvNumberText.setText("" + cardList.get(position).getCVV());
        String cardNumber = String.valueOf(cardList.get(position).getLast4());
        holder.hiddenCardNumberText.setText(cardList.get(position).getBrand() + "****" + cardNumber + " exp"
                + cardList.get(position).getExpMonth() + "/" + cardList.get(position).getExpYear());
//        holder.hiddenCardNumberText2.setText("****-****-****-" + cardNumber.substring(11, 15));
        if (cardList.get(position).isSelected()) {
            holder.checkboxImageInner.setImageResource(R.drawable.checkbox_click);
        } else {
            holder.checkboxImageInner.setImageResource(R.drawable.check_box);
        }
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        //
        @BindView(R.id.outerLayout)
        RelativeLayout outerLayout;
//
//        @BindView(R.id.detailLayout)
//        RelativeLayout detailLayout;
//
//        @BindView(R.id.selectCardLayout)
//        RelativeLayout selectCardLayout;
//
//        @BindView(R.id.payPalLayout)
//        RelativeLayout payPalLayout;
//
//        @BindView(R.id.checkbox2Image)
//        ImageView paypalCheckBox;

        @BindView(R.id.hiddenCardNumberText)
        TextView hiddenCardNumberText;

        @BindView(R.id.checkboxImageInner)
        ImageView checkboxImageInner;

//        @BindView(R.id.cvvNumberText)
//        TextView cvvNumberText;
//
//        @BindView(R.id.hiddenCardNumberText2)
//        TextView hiddenCardNumberText2;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.outerLayout)
        void setOuterLayout() {
            checkboxImageInner.setImageResource(R.drawable.checkbox_click);
            ((CompletePaymentActivity) mContext).setCardValues(cardList.get(getAdapterPosition()));
            for (int i = 0; i < cardList.size(); i++) {
                if (getAdapterPosition() == i) {
                    cardList.get(i).setSelected(true);
                } else {
                    cardList.get(i).setSelected(false);
                }
            }
            notifyDataSetChanged();
        }

    }
}

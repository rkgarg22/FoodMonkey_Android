package com.kartik.project.foodmonkey;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kartik.project.foodmonkey.Adapters.YourOrderListDetailAdapter;
import com.kartik.project.foodmonkey.ApiObject.OrderListDetailObj;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class YourOrderDetailActivity extends AppCompatActivity {

    @BindView(R.id.left)
    ImageView left;

    @BindView(R.id.toolbarText)
    TextView toolbarText;

    @BindView(R.id.restName)
    TextView restName;

    @BindView(R.id.restAddress)
    TextView restAddress;

    @BindView(R.id.orderID)
    TextView orderID;

    @BindView(R.id.customerName)
    TextView customerName;

    @BindView(R.id.totalAmount)
    TextView totalAmount;

    @BindView(R.id.dateAndTimeText)
    TextView dateAndTimeText;

    @BindView(R.id.phoneNumber)
    TextView phoneNumber;

    @BindView(R.id.addressText)
    TextView addressText;

    @BindView(R.id.subTotalAmount)
    TextView subTotalAmount;

    @BindView(R.id.deliveryStatus)
    TextView deliveryStatus;

    @BindView(R.id.totalPricing)
    TextView totalPricing;

    @BindView(R.id.orderStatus)
    TextView orderStatus;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    OrderListDetailObj detailObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_order_detail);
        ButterKnife.bind(this);

        left.setVisibility(View.VISIBLE);
        toolbarText.setText(getString(R.string.yourOrder));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        if (getIntent() != null) {
            detailObj = (OrderListDetailObj) getIntent().getSerializableExtra("data");
            setData(detailObj);
        }
    }

    @OnClick(R.id.left)
    void setLeft() {
        onBackPressed();
    }

    private void setData(OrderListDetailObj detailObj) {
        restName.setText(detailObj.getRestName());
        restAddress.setText(detailObj.getRestStreetLine1() + " " + detailObj.getRestStreetLine2() + " "
                + detailObj.getRestCity() + " " + detailObj.getRestPostCode());
        orderID.setText("Order ID" + detailObj.getOrderId());
        totalAmount.setText("Total:£" + detailObj.getOrderAmount());
        dateAndTimeText.setText("Date/Time:" + detailObj.getOrderDateTime());
        phoneNumber.setText("Phone:" + detailObj.getDeliveryHouseNo());
        addressText.setText("Address:" + detailObj.getDeliveryHouseNo() + " " + detailObj.getDeliveryStreetLine1() +
                " " + detailObj.getRestStreetLine2() + " " + detailObj.getDeliveryCity() + " " + detailObj.getDeliveryPostCode());
        subTotalAmount.setText("£" + detailObj.getOrderAmount());
        deliveryStatus.setText("£" + detailObj.getDeliveryCharges());
        totalPricing.setText("£" + detailObj.getOrderAmount());
        orderStatus.setText(detailObj.getDeliveryOption());
        YourOrderListDetailAdapter orderListDetailAdapter = new YourOrderListDetailAdapter(this, detailObj.getMenuOrder());
        recyclerView.setAdapter(orderListDetailAdapter);
    }


}

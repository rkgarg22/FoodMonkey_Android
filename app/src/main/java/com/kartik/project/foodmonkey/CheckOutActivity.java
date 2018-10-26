package com.kartik.project.foodmonkey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonSyntaxException;
import com.kartik.project.foodmonkey.API.FoodMonkeyAppService;
import com.kartik.project.foodmonkey.API.ServiceGenerator;
import com.kartik.project.foodmonkey.Adapters.AddressAdapter;
import com.kartik.project.foodmonkey.Adapters.OrderListAdapter;
import com.kartik.project.foodmonkey.ApiEntity.CustAddAddressEntity;
import com.kartik.project.foodmonkey.ApiResponse.CustomerAddressResponse;
import com.kartik.project.foodmonkey.Models.AddAddressModel;
import com.kartik.project.foodmonkey.Models.AddItemsToCartModel;
import com.kartik.project.foodmonkey.Models.InfoModel;

import java.util.ArrayList;
import java.util.List;

import Infrastructure.AppCommon;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckOutActivity extends AppCompatActivity {

    @BindView(R.id.left)
    ImageView left;

    @BindView(R.id.toolbarText)
    TextView toolbarText;

    @BindView(R.id.orderListRecyclerView)
    RecyclerView orderListRecyclerView;

    @BindView(R.id.chooseDeliveryRecyclerView)
    RecyclerView chooseDeliveryRecyclerView;

    @BindView(R.id.loginLayout)
    RelativeLayout loginLayout;

    @BindView(R.id.chooseDeliveryTypeLayout)
    RelativeLayout chooseDeliveryTypeLayout;

    @BindView(R.id.subTotalText)
    TextView subTotalText;

    @BindView(R.id.totalBill)
    TextView totalBill;

    @BindView(R.id.deliveryCharges)
    TextView deliveryCharges;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.addNewAddress)
    TextView addNewAddress;

    OrderListAdapter orderListAdapter;

    AddressAdapter addressAdapter;

    ArrayList<InfoModel> infoOfOrder = new ArrayList<>();
    ArrayList<AddAddressModel> addAddressModelArrayList = new ArrayList<>();

    public final int LOGIN_CODE = 1000;

    List<AddItemsToCartModel> addItemsToCartModelArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        ButterKnife.bind(this);
        left.setVisibility(View.VISIBLE);
        toolbarText.setText(getString(R.string.checkOut));
        addItemsToCartModelArrayList = AppCommon.getInstance(this).getAddToCartObject();

//        toolbarText.setGravity(Gravity.START | Gravity.CENTER_VERTICAL);

        chooseDeliveryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        chooseDeliveryRecyclerView.setNestedScrollingEnabled(false);

        orderListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        orderListRecyclerView.setNestedScrollingEnabled(false);
        setData();
        orderListAdapter = new OrderListAdapter(CheckOutActivity.this, infoOfOrder);
        orderListRecyclerView.setAdapter(orderListAdapter);

        addressAdapter = new AddressAdapter(this, addAddressModelArrayList);

        if (AppCommon.getInstance(this).isUserLogIn()) {
            setLayoutVisiblity(true);
        } else {
//            setLayoutVisiblity(false);
        }

    }

    @OnClick(R.id.left)
    void setLeft() {
        onBackPressed();
    }

    @OnClick(R.id.editOrder)
    void setEditOrder() {
        startActivity(new Intent(CheckOutActivity.this, EditCartActivity.class));
    }

    @OnClick(R.id.addNewAddress)
    void setAddNewAddress() {
        startActivity(new Intent(CheckOutActivity.this, AddAddressActivity.class));
    }

    float totalAmount = 0;
    float deliveryFee = 1;

    void setData() {
        infoOfOrder.clear();
        for (int i = 0; i < addItemsToCartModelArrayList.size(); i++) {
            infoOfOrder.add(new InfoModel(addItemsToCartModelArrayList.get(i).getId(), addItemsToCartModelArrayList.get(i).getRestName(),
                    addItemsToCartModelArrayList.get(i).getName(), "$" + addItemsToCartModelArrayList.get(i).getPrice(),
                    "x " + addItemsToCartModelArrayList.get(i).getQuantity()));

            float price = Float.parseFloat(addItemsToCartModelArrayList.get(i).getPrice());
            int noOfQuantity = Integer.parseInt(addItemsToCartModelArrayList.get(i).getQuantity());
            totalAmount = totalAmount + (price * noOfQuantity);
        }
        subTotalText.setText("" + totalAmount);
        deliveryCharges.setText("$" + deliveryFee);
        totalBill.setText("" + (totalAmount + deliveryFee));

        addAddressModelArrayList.clear();
        addAddressModelArrayList.add(new AddAddressModel("Home", "Eddie Methew, 256 Near ABC LandMark, Street Line 1, Street Line 2, 45874, Bristol"));
        addAddressModelArrayList.add(new AddAddressModel("Bussiness", "Eddie Methew, 256 Near ABC LandMark, Street Line 1, Street Line 2, 45874, Bristol"));
    }

    @OnClick(R.id.loginButton)
    void setLoginButton() {
        if (!AppCommon.getInstance(CheckOutActivity.this).isUserLogIn()) {
            Intent intent = new Intent(CheckOutActivity.this, LoginActivity.class);
            startActivityForResult(intent, LOGIN_CODE);
        }
    }

    @OnClick(R.id.proceedToPayBtn)
    void setProceedToPayBtn() {
        Intent intent = new Intent(CheckOutActivity.this, CompletePaymentActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == LOGIN_CODE) {
                boolean status = data.getBooleanExtra("data", false);
                setLayoutVisiblity(status);
            }
        }
    }

    void setLayoutVisiblity(boolean status) {
        if (status) {
            chooseDeliveryTypeLayout.setVisibility(View.VISIBLE);
            loginLayout.setVisibility(View.GONE);

            // call address API here---
            chooseDeliveryRecyclerView.setAdapter(addressAdapter);

        } else {
            chooseDeliveryTypeLayout.setVisibility(View.GONE);
            loginLayout.setVisibility(View.VISIBLE);
        }
    }
}

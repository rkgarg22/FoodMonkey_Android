package com.kartik.project.foodmonkey;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.JsonSyntaxException;
import com.kartik.project.foodmonkey.API.FoodMonkeyAppService;
import com.kartik.project.foodmonkey.API.ServiceGenerator;
import com.kartik.project.foodmonkey.Adapters.YourOrderAdapter;
import com.kartik.project.foodmonkey.ApiResponse.CustomerOrderListResponse;

import java.util.HashMap;

import Infrastructure.AppCommon;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YourOrderListingActivty extends AppCompatActivity {


    @BindView(R.id.left)
    ImageView left;

    @BindView(R.id.toolbarText)
    TextView toolbarText;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_order_listing_activty);
        ButterKnife.bind(this);
        left.setVisibility(View.VISIBLE);
        toolbarText.setText(getString(R.string.yourOrder));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        callingOrderListing();
    }

    @OnClick(R.id.left)
    void setLeft() {
        onBackPressed();
    }

    private void callingOrderListing() {
        AppCommon.getInstance(YourOrderListingActivty.this).setNonTouchableFlags(YourOrderListingActivty.this);
        if (AppCommon.getInstance(YourOrderListingActivty.this).isConnectingToInternet(YourOrderListingActivty.this)) {
            progressBar.setVisibility(View.VISIBLE);
            FoodMonkeyAppService foodMonkeyAppService = ServiceGenerator.createService(FoodMonkeyAppService.class);
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("TokenKey", AppCommon.getInstance(this).getDeviceToken());
            hashMap.put("Customer_id", AppCommon.getInstance(this).getCustomerID());
            Call call = foodMonkeyAppService.CustomerOrderList(hashMap);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(YourOrderListingActivty.this).clearNonTouchableFlags(YourOrderListingActivty.this);
                    if (response.code() == 200) {
                        progressBar.setVisibility(View.GONE);
                        CustomerOrderListResponse customerOrderListResponse = (CustomerOrderListResponse) response.body();
                        if (customerOrderListResponse != null) {
                            if (customerOrderListResponse.getCode().equals("200")) {
                                YourOrderAdapter yourOrderAdapter = new YourOrderAdapter(YourOrderListingActivty.this,
                                        customerOrderListResponse.getCustomerOrders().getOrderDetail());
                                recyclerView.setAdapter(yourOrderAdapter);
                            } else {
                                AppCommon.showDialog(YourOrderListingActivty.this, customerOrderListResponse.getMessage());
                            }
                        }
                    } else {
                        AppCommon.showDialog(YourOrderListingActivty.this, getString(R.string.serverError));
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    AppCommon.getInstance(YourOrderListingActivty.this).clearNonTouchableFlags(YourOrderListingActivty.this);
                    progressBar.setVisibility(View.GONE);
                    if (t instanceof JsonSyntaxException) {
                        AppCommon.showDialog(YourOrderListingActivty.this, "No Rating found");
                    } else {
                        AppCommon.showDialog(YourOrderListingActivty.this, getResources().getString(R.string.network_error));

                    }
                }
            });
        } else {
            AppCommon.getInstance(YourOrderListingActivty.this).clearNonTouchableFlags(YourOrderListingActivty.this);
            AppCommon.showDialog(YourOrderListingActivty.this, getResources().getString(R.string.network_error));
        }
    }
}

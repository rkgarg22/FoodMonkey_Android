package com.kartik.project.foodmonkey;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.JsonSyntaxException;
import com.kartik.project.foodmonkey.API.FoodMonkeyAppService;
import com.kartik.project.foodmonkey.API.ServiceGenerator;
import com.kartik.project.foodmonkey.ApiObject.OrderStatusObject;
import com.kartik.project.foodmonkey.ApiResponse.OrderStatusResponse;

import java.util.HashMap;

import Infrastructure.AppCommon;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YourOrderActivity extends AppCompatActivity {

    @BindView(R.id.left)
    ImageView left;

    @BindView(R.id.toolbarText)
    TextView toolbarText;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.tabOneSelected)
    LinearLayout tabOneSelected;

    @BindView(R.id.tabOneMinutes)
    TextView tabOneMinutes;

    @BindView(R.id.tabSecondSelected)
    LinearLayout tabSecondSelected;

    @BindView(R.id.tabTwoMintues)
    TextView tabTwoMintues;

    @BindView(R.id.tabThirdSelected)
    LinearLayout tabThirdSelected;

    Call call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_order);
        ButterKnife.bind(this);
        left.setVisibility(View.VISIBLE);
        toolbarText.setText(getString(R.string.yourOrder));
        OrderStatusAPI("303");

    }

    @OnClick(R.id.left)
    void setLeft() {
        onBackPressed();
    }

    void OrderStatusAPI(String orderID) {
        AppCommon.getInstance(this).setNonTouchableFlags(this);
        if (AppCommon.getInstance(this).isConnectingToInternet(this)) {
            progressBar.setVisibility(View.VISIBLE);
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("TokenKey", AppCommon.getInstance(this).getDeviceToken());
            hashMap.put("Order_Id", orderID);
            FoodMonkeyAppService foodMonkeyAppService = ServiceGenerator.createService(FoodMonkeyAppService.class);
            call = foodMonkeyAppService.CustomerOrderStatus(hashMap);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(YourOrderActivity.this).clearNonTouchableFlags(YourOrderActivity.this);
                    if (response.code() == 200) {
                        progressBar.setVisibility(View.GONE);
                        OrderStatusResponse orderStatusResponse = (OrderStatusResponse) response.body();
                        if (response.body() != null) {
                            if (orderStatusResponse.getCode() != null) {
                                if (orderStatusResponse.getCode().equals("200")) {
                                    setData(orderStatusResponse.getOrderStatusObject());
                                } else {
                                    AppCommon.showDialog(YourOrderActivity.this, orderStatusResponse.getMessage());
                                }
                            } else {
                                AppCommon.showDialog(YourOrderActivity.this, orderStatusResponse.getMessage());
                            }
                        }
                    } else {
                        AppCommon.showDialog(YourOrderActivity.this, getString(R.string.serverError));
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    AppCommon.getInstance(YourOrderActivity.this).clearNonTouchableFlags(YourOrderActivity.this);
                    progressBar.setVisibility(View.GONE);
                    if (t instanceof JsonSyntaxException) {
                        AppCommon.showDialog(YourOrderActivity.this, "No resturant found");
                    } else {
                        AppCommon.showDialog(YourOrderActivity.this, getResources().getString(R.string.network_error));

                    }
                }
            });
        } else {
            AppCommon.getInstance(YourOrderActivity.this).clearNonTouchableFlags(YourOrderActivity.this);
            AppCommon.showDialog(this, getResources().getString(R.string.network_error));
        }
    }

    private void setData(OrderStatusObject orderStatusObject) {
        switch (orderStatusObject.getOrderStatus()) {
            case "Open":
                tabOneSelected.setVisibility(View.VISIBLE);
                tabSecondSelected.setVisibility(View.GONE);
                tabThirdSelected.setVisibility(View.GONE);
//                tabOneMinutes.a
                break;
            case "":
                break;

        }
    }

}

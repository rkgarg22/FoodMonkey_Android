package com.kartik.project.foodmonkey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kartik.project.foodmonkey.Adapters.AddressAdapter;
import com.kartik.project.foodmonkey.Adapters.OrderListAdapter;
import com.kartik.project.foodmonkey.Models.AddAddressModel;
import com.kartik.project.foodmonkey.Models.InfoModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    OrderListAdapter orderListAdapter;

    AddressAdapter addressAdapter;

    ArrayList<InfoModel> infoOfOrder = new ArrayList<>();
    ArrayList<AddAddressModel> addAddressModelArrayList = new ArrayList<>();

    public final int LOGIN_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        ButterKnife.bind(this);
        left.setVisibility(View.VISIBLE);
        toolbarText.setText(getString(R.string.checkOut));
//        toolbarText.setGravity(Gravity.START | Gravity.CENTER_VERTICAL);

        chooseDeliveryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        chooseDeliveryRecyclerView.setNestedScrollingEnabled(false);

        orderListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        orderListRecyclerView.setNestedScrollingEnabled(false);
        setDummyData();
        orderListAdapter = new OrderListAdapter(CheckOutActivity.this, infoOfOrder);
        orderListRecyclerView.setAdapter(orderListAdapter);

        addressAdapter = new AddressAdapter(this, addAddressModelArrayList);

        setLayoutVisiblity(false);
    }

    @OnClick(R.id.left)
    void setLeft() {
        onBackPressed();
    }

    @OnClick(R.id.editOrder)
    void setEditOrder() {
        startActivity(new Intent(CheckOutActivity.this, EditCartActivity.class));
    }

    void setDummyData() {
        infoOfOrder.clear();
        infoOfOrder.add(new InfoModel("Caramel Chew Chew", "$2.49", "x 2"));
        infoOfOrder.add(new InfoModel("Chocolate Fudge Brownie", "$2.49", "x 1"));
        infoOfOrder.add(new InfoModel("Cookie Dough", "$2.49", "x 5"));
        infoOfOrder.add(new InfoModel("Stawberry Shake", "$2.49", "x 1"));
        infoOfOrder.add(new InfoModel("Cheese Roll", "$2.49", "x 2"));

        addAddressModelArrayList.clear();
        addAddressModelArrayList.add(new AddAddressModel("Home", "Eddie Methew, 256 Near ABC LandMark, Street Line 1, Street Line 2, 45874, Bristol"));
        addAddressModelArrayList.add(new AddAddressModel("Bussiness", "Eddie Methew, 256 Near ABC LandMark, Street Line 1, Street Line 2, 45874, Bristol"));
        }

    @OnClick(R.id.loginButton)
    void setLoginButton() {
        Intent intent = new Intent(CheckOutActivity.this, LoginActivity.class);
        startActivityForResult(intent, LOGIN_CODE);
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
            chooseDeliveryRecyclerView.setAdapter(addressAdapter);

        } else {
            chooseDeliveryTypeLayout.setVisibility(View.GONE);
            loginLayout.setVisibility(View.VISIBLE);
        }
    }


}

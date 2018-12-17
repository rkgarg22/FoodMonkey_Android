package com.kartik.project.foodmonkey;

import android.content.Intent;
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
import com.kartik.project.foodmonkey.Adapters.ManageAddressAdapter;
import com.kartik.project.foodmonkey.ApiEntity.CustomerEditAddressEntity;
import com.kartik.project.foodmonkey.ApiObject.AddressObjects;
import com.kartik.project.foodmonkey.ApiObject.CustomerObject;
import com.kartik.project.foodmonkey.ApiResponse.CommonResponse;

import Infrastructure.AppCommon;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManagerAddressActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.toolbarText)
    TextView toolbarText;

    @BindView(R.id.left)
    ImageView left;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    CustomerObject customerObject;

    ManageAddressAdapter manageAddressAdapter;

    Call call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_address);
        ButterKnife.bind(this);
        left.setVisibility(View.VISIBLE);
        toolbarText.setText(getString(R.string.manageAddress));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setNestedScrollingEnabled(false);
        if (getIntent() != null) {
            customerObject = (CustomerObject) getIntent().getSerializableExtra("data");
        }
        manageAddressAdapter = new ManageAddressAdapter(this, customerObject);
        recyclerView.setAdapter(manageAddressAdapter);
    }

    @OnClick(R.id.left)
    void setLeft() {
        onBackPressed();
    }

    @OnClick(R.id.addNewAddress)
    void setAddNewAddress() {
        Intent intent=new Intent(this,AddAddressActivity.class);
        intent.putExtra("comingFrom","manageAddress");
        startActivity(intent);
    }

//    change this APi To delete Address
//    void callingEditAddress(String tokenKey, int addressId, String addressType, int mobileNumber,
//                            int houseNo, String addressNote, String streetLine1, String streetLine2,
//                            String postCode, String city) {
//        AppCommon.getInstance(this).setNonTouchableFlags(this);
//        if (AppCommon.getInstance(ManagerAddressActivity.this).isConnectingToInternet(ManagerAddressActivity.this)) {
//            progressBar.setVisibility(View.VISIBLE);
//            CustomerEditAddressEntity custProfileEditEntity = new CustomerEditAddressEntity
//                    (tokenKey, addressId, addressType, mobileNumber, houseNo, addressNote, streetLine1, streetLine2, postCode, city);
//
//            FoodMonkeyAppService foodMonkeyAppService = ServiceGenerator.createService(FoodMonkeyAppService.class);
//            call = foodMonkeyAppService.CustomerEditAddress(custProfileEditEntity);
//            call.enqueue(new Callback() {
//                @Override
//                public void onResponse(Call call, Response response) {
//                    AppCommon.getInstance(ManagerAddressActivity.this).clearNonTouchableFlags(ManagerAddressActivity.this);
//                    if (response.code() == 200) {
//                        progressBar.setVisibility(View.GONE);
//                        CommonResponse commonResponse = (CommonResponse) response.body();
//                        if (commonResponse.getCode().equals("200")) {
//                            AppCommon.showDialog(ManagerAddressActivity.this, commonResponse.getMessage());
//
//                        } else {
//                            AppCommon.showDialog(ManagerAddressActivity.this, commonResponse.getMessage());
//                        }
//                    } else {
//                        AppCommon.showDialog(ManagerAddressActivity.this, getString(R.string.serverError));
//                    }
//                }
//
//                @Override
//                public void onFailure(Call call, Throwable t) {
//                    AppCommon.getInstance(ManagerAddressActivity.this).clearNonTouchableFlags(ManagerAddressActivity.this);
//                    progressBar.setVisibility(View.GONE);
//                    if (t instanceof JsonSyntaxException) {
//                        AppCommon.showDialog(ManagerAddressActivity.this, "Something Went wrong ..Please try again.");
//                    } else {
//                        AppCommon.showDialog(ManagerAddressActivity.this, getResources().getString(R.string.network_error));
//                    }
//                }
//            });
//        } else {
//            AppCommon.getInstance(ManagerAddressActivity.this).clearNonTouchableFlags(ManagerAddressActivity.this);
//            AppCommon.showDialog(this, getResources().getString(R.string.network_error));
//        }
//    }

    public void OnEditClick(CustomerObject customerObject, int adapterPosition) {
        Intent intent = new Intent(this, EditAddressActivity.class);
        intent.putExtra("name", customerObject.getFirstName() + " " + customerObject.getSurName());
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", customerObject.getAddresses().get(adapterPosition));
        intent.putExtras(bundle);
        startActivityForResult(intent, 2000);
    }

    public void OnDeleteClick(CustomerObject customerObject, int adapterPosition) {
//        Toast.makeText(mContext, "Currently under Development", Toast.LENGTH_SHORT).show();
    }
}

package com.kartik.project.foodmonkey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.JsonSyntaxException;
import com.kartik.project.foodmonkey.API.FoodMonkeyAppService;
import com.kartik.project.foodmonkey.API.ServiceGenerator;
import com.kartik.project.foodmonkey.ApiEntity.CommonEntity;
import com.kartik.project.foodmonkey.ApiObject.CustomerObject;
import com.kartik.project.foodmonkey.ApiResponse.CustomerDetailResponse;

import java.util.ArrayList;
import java.util.List;

import Infrastructure.AppCommon;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonalAccountActivity extends AppCompatActivity {

    @BindView(R.id.left)
    ImageView left;

    @BindView(R.id.toolbarText)
    TextView toolbarText;

    @BindView(R.id.profilePic)
    SimpleDraweeView profilePic;

    @BindView(R.id.userNameText)
    TextView userNameText;

    @BindView(R.id.genderText)
    TextView genderText;

    @BindView(R.id.emailText)
    TextView emailText;

    @BindView(R.id.mobileNumber)
    TextView mobileNumber;

    @BindView(R.id.dateOfBirth)
    TextView dateOfBirth;

    @BindView(R.id.homeAddress)
    TextView homeAddress;

    @BindView(R.id.bussinessText)
    TextView bussinessText;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    Call call;

    List<CustomerObject> customerDetails=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_account);
        ButterKnife.bind(this);
        left.setVisibility(View.VISIBLE);
        left.setImageResource(R.drawable.back_icon);
        toolbarText.setText(getString(R.string.personalAccountDetail));
        callingPersonalDetail(AppCommon.getInstance(this).getDeviceToken(), AppCommon.getInstance(this).getCustomerID());
    }

    private void setData(List<CustomerObject> customerDetails) {
        this.customerDetails=customerDetails;
        userNameText.setText(customerDetails.get(0).getFirstName() + " " + customerDetails.get(0).getSurName());
        genderText.setText(customerDetails.get(0).getGender());
        emailText.setText(customerDetails.get(0).getEmail());
        mobileNumber.setText(customerDetails.get(0).getMobile());
        dateOfBirth.setText(customerDetails.get(0).getdOB());
        if (customerDetails.get(0).getAddresses().get(0) != null)
            if (customerDetails.get(0).getAddresses().get(0).getAddressName().toLowerCase().equals("home")){
                homeAddress.setText(customerDetails.get(0).getAddresses().get(0).getAddressName() + "\n"
                        + customerDetails.get(0).getAddresses().get(0).getHouseNo() + ", " + customerDetails.get(0).getAddresses().get(0).getStreetLine1()
                        + ", " + customerDetails.get(0).getAddresses().get(0).getStreetLine2() + ", " + customerDetails.get(0).getAddresses().get(0).getCity() + ", "
                        + ", " + customerDetails.get(0).getAddresses().get(0).getPostCode());
            }else {
                bussinessText.setText(customerDetails.get(0).getAddresses().get(0).getAddressName() + "\n"
                        + customerDetails.get(0).getAddresses().get(0).getHouseNo() + ", " + customerDetails.get(0).getAddresses().get(0).getStreetLine1()
                        + ", " + customerDetails.get(0).getAddresses().get(0).getStreetLine2() + ", " + customerDetails.get(0).getAddresses().get(0).getCity() + ", "
                        + ", " + customerDetails.get(0).getAddresses().get(0).getPostCode());
            }

//        bussinessText.setText(AppCommon.getInstance(this).getBussinessAddress());
        profilePic.setController(AppCommon.getDraweeController(profilePic, AppCommon.getInstance(this).getProfilePic(), 100));

    }

    @OnClick(R.id.left)
    void setLeft() {
        onBackPressed();
    }

    @OnClick(R.id.editAccount)
    void setEditAccount() {
        Intent intent=new Intent(this, EditAccountActivity.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("data",customerDetails.get(0));
        intent.putExtras(bundle);
        startActivity(intent);
    }


    void callingPersonalDetail(String tokenID, String customerID) {
        AppCommon.getInstance(this).setNonTouchableFlags(this);
        if (AppCommon.getInstance(PersonalAccountActivity.this).isConnectingToInternet(PersonalAccountActivity.this)) {
            progressBar.setVisibility(View.VISIBLE);
            CommonEntity commonEntity = new CommonEntity(tokenID, customerID);

            FoodMonkeyAppService foodMonkeyAppService = ServiceGenerator.createService(FoodMonkeyAppService.class);
            call = foodMonkeyAppService.GetCustomerDetail(commonEntity);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(PersonalAccountActivity.this).clearNonTouchableFlags(PersonalAccountActivity.this);
                    if (response.code() == 200) {
                        progressBar.setVisibility(View.GONE);
                        CustomerDetailResponse customerDetailResponse = (CustomerDetailResponse) response.body();
                        if (customerDetailResponse.getCode().equals("200")) {
                            Toast.makeText(PersonalAccountActivity.this, customerDetailResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            setData(customerDetailResponse.getCustomerDetails());
                        } else {
                            AppCommon.showDialog(PersonalAccountActivity.this, customerDetailResponse.getMessage());
                        }
                    } else {
                        AppCommon.showDialog(PersonalAccountActivity.this, getString(R.string.serverError));
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    AppCommon.getInstance(PersonalAccountActivity.this).clearNonTouchableFlags(PersonalAccountActivity.this);
                    progressBar.setVisibility(View.GONE);
                    if (t instanceof JsonSyntaxException) {
                        AppCommon.showDialog(PersonalAccountActivity.this, "Something Went wrong ..Please try again.");
                    } else {
                        AppCommon.showDialog(PersonalAccountActivity.this, getResources().getString(R.string.network_error));
                    }
                }
            });
        } else {
            AppCommon.getInstance(PersonalAccountActivity.this).clearNonTouchableFlags(PersonalAccountActivity.this);
            AppCommon.showDialog(this, getResources().getString(R.string.network_error));
        }
    }

}

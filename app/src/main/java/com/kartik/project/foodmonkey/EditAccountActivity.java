package com.kartik.project.foodmonkey;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.JsonSyntaxException;
import com.kartik.project.foodmonkey.API.FoodMonkeyAppService;
import com.kartik.project.foodmonkey.API.ServiceGenerator;
import com.kartik.project.foodmonkey.ApiEntity.CustProfileEditEntity;
import com.kartik.project.foodmonkey.ApiObject.CustomerObject;
import com.kartik.project.foodmonkey.ApiResponse.CommonResponse;

import Infrastructure.AppCommon;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditAccountActivity extends AppCompatActivity {

    @BindView(R.id.left)
    ImageView left;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.toolbarText)
    TextView toolbarText;

    @BindView(R.id.popUpLayout)
    RelativeLayout popUpLayout;

    @BindView(R.id.profilePic)
    SimpleDraweeView profilePic;

    @BindView(R.id.firstName)
    EditText firstName;

    @BindView(R.id.genderText)
    TextView genderText;

    @BindView(R.id.email)
    EditText email;

    @BindView(R.id.mobileNumber)
    EditText mobileNumber;

    @BindView(R.id.dateOfBirth)
    TextView dateOfBirth;

    @BindView(R.id.saveButton)
    Button saveButton;

    Call call;

    CustomerObject customerObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);
        ButterKnife.bind(this);
        left.setVisibility(View.VISIBLE);
        left.setImageResource(R.drawable.back_icon);
        toolbarText.setText(getString(R.string.editAccount));
        if (getIntent() != null) {
            customerObject = (CustomerObject) getIntent().getSerializableExtra("data");
            setData(customerObject);
        }
    }

    private void setData(CustomerObject customerObject) {
        firstName.setText(customerObject.getFirstName());
        genderText.setText(customerObject.getGender());
        email.setText(customerObject.getEmail());
        mobileNumber.setText(customerObject.getMobile());
        dateOfBirth.setText(customerObject.getdOB());
        profilePic.setController(AppCommon.getDraweeController(profilePic,"http://food-monkey.com"+customerObject.getImageLink(),100));
    }

    @OnClick(R.id.left)
    void setLeft() {
        onBackPressed();
    }

    @OnClick(R.id.genderText)
    void setGenderText() {
        popUpLayout.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.male)
    void setGenderMale() {
        genderText.setText(getString(R.string.male));
        popUpLayout.setVisibility(View.GONE);
    }

    @OnClick(R.id.fenale)
    void setGenderFenale() {
        genderText.setText(getString(R.string.female));
        popUpLayout.setVisibility(View.GONE);
    }

    @OnClick(R.id.cancel)
    void setCancel() {
        popUpLayout.setVisibility(View.GONE);
    }

    @OnClick(R.id.saveButton)
    void setSaveButton() {
        String firstName = this.firstName.getText().toString().trim();
        String genderText = this.genderText.getText().toString().trim();
        String dateOfBirth = this.dateOfBirth.getText().toString().trim();

        callingProfileEdit(AppCommon.getInstance(this).getDeviceToken(), AppCommon.getInstance(this).getCustomerID(), firstName, "", "",
                genderText, "", dateOfBirth, "");
    }

    void callingProfileEdit(String tokenKey, final String customerID, final String firstName, final String middleName, final String surName,
                            final String gender, final String mobile, final String dateOfBirth, String profilePic) {
        AppCommon.getInstance(this).setNonTouchableFlags(this);
        if (AppCommon.getInstance(EditAccountActivity.this).isConnectingToInternet(EditAccountActivity.this)) {
            progressBar.setVisibility(View.VISIBLE);
            //  final String token = myFirebaseInstanceIDService.getDeviceToken();
            CustProfileEditEntity custProfileEditEntity = new CustProfileEditEntity(tokenKey, customerID, firstName, middleName, surName, gender, dateOfBirth, profilePic);

            FoodMonkeyAppService foodMonkeyAppService = ServiceGenerator.createService(FoodMonkeyAppService.class);
            call = foodMonkeyAppService.ProfileEdit(custProfileEditEntity);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(EditAccountActivity.this).clearNonTouchableFlags(EditAccountActivity.this);
                    if (response.code() == 200) {
                        progressBar.setVisibility(View.GONE);
                        CommonResponse commonResponse = (CommonResponse) response.body();
                        if (commonResponse.getCode().equals("200")) {
                            Toast.makeText(EditAccountActivity.this, commonResponse.getMessage(), Toast.LENGTH_SHORT).show();

                            AppCommon.getInstance(EditAccountActivity.this).setFirstName(firstName);
                            AppCommon.getInstance(EditAccountActivity.this).setSurName(surName);
                            AppCommon.getInstance(EditAccountActivity.this).setMiddleName(middleName);
                            AppCommon.getInstance(EditAccountActivity.this).setGender(gender);
//                            AppCommon.getInstance(EditAccountActivity.this).setEmailAddress(customerSignUpObject.getEmail());
                            AppCommon.getInstance(EditAccountActivity.this).setMobileNumber(mobile);
                            AppCommon.getInstance(EditAccountActivity.this).setDateOfBirth(dateOfBirth);
//                            AppCommon.getInstance(EditAccountActivity.this).setProfilePic("http://food-monkey.com" + customerSignUpResponse.getCustomerDetail().get(0).getImageLink());
//                            AppCommon.getInstance(EditAccountActivity.this).setStripeCustID(customerSignUpObject.getStripeCustomerId());
//                            AppCommon.getInstance(EditAccountActivity.this).setReferCode(customerSignUpObject.getReferCode());

                        } else {
                            AppCommon.showDialog(EditAccountActivity.this, commonResponse.getMessage());
                        }
                    } else {
                        AppCommon.showDialog(EditAccountActivity.this, getString(R.string.serverError));
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    AppCommon.getInstance(EditAccountActivity.this).clearNonTouchableFlags(EditAccountActivity.this);
                    progressBar.setVisibility(View.GONE);
                    if (t instanceof JsonSyntaxException) {
                        AppCommon.showDialog(EditAccountActivity.this, "Something Went wrong ..Please try again.");
                    } else {
                        AppCommon.showDialog(EditAccountActivity.this, getResources().getString(R.string.network_error));
                    }
                }
            });
        } else {
            AppCommon.getInstance(EditAccountActivity.this).clearNonTouchableFlags(EditAccountActivity.this);
            AppCommon.showDialog(this, getResources().getString(R.string.network_error));
        }
    }

}

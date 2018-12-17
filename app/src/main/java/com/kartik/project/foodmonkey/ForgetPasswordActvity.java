package com.kartik.project.foodmonkey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonSyntaxException;
import com.kartik.project.foodmonkey.API.FoodMonkeyAppService;
import com.kartik.project.foodmonkey.API.ServiceGenerator;
import com.kartik.project.foodmonkey.ApiResponse.SendingOTP_Response;
import com.kartik.project.foodmonkey.ApiResponse.VaildNumberResponse;

import java.util.HashMap;

import Infrastructure.AppCommon;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPasswordActvity extends AppCompatActivity {

    @BindView(R.id.left)
    ImageView left;

    @BindView(R.id.toolbarText)
    TextView toolbarText;

    @BindView(R.id.headingText)
    TextView headingText;

    @BindView(R.id.mobileRadioBtn)
    RadioButton mobileRadioBtn;

    @BindView(R.id.emailRadioBtn)
    RadioButton emailRadioBtn;

    @BindView(R.id.emailOrNumber)
    EditText emailOrNumber;

    @BindView(R.id.choiceLayout)
    LinearLayout choiceLayout;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.emailLayout)                                    // layout for 1 step to email
            RelativeLayout emailLayout;

    Call call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password_actvity);
        ButterKnife.bind(this);
        left.setVisibility(View.VISIBLE);
        toolbarText.setText(getString(R.string.forgotPassword));

        mobileRadioBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    emailRadioBtn.setChecked(false);
                } else {
                    emailRadioBtn.setChecked(true);
                }
            }
        });

        emailRadioBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mobileRadioBtn.setChecked(false);
                } else {
                    mobileRadioBtn.setChecked(true);
                }
            }
        });
    }

    @OnClick(R.id.left)
    void setLeft() {
        onBackPressed();
    }

    @OnClick(R.id.submitChoice)
    void setSubmitChoice() {
        choiceLayout.setVisibility(View.GONE);
        emailLayout.setVisibility(View.VISIBLE);
        if (mobileRadioBtn.isChecked()) {
            headingText.setText(getString(R.string.enterYourMoileAndGetOTP));
            emailOrNumber.setHint(getString(R.string.enterYourNumber));
//            startActivity(new Intent(this, EnterOTPActivity.class));
            // move to OTP screen
        } else {
            headingText.setText(getString(R.string.forgetPasswordText));
            emailOrNumber.setHint(getString(R.string.enterYourEmail));
            // move to email screen
        }
    }

    @OnClick(R.id.nextButton)
    void setNextButton() {
        if (mobileRadioBtn.isChecked()) {
            if (emailOrNumber.getText().toString().isEmpty()) {
                Toast.makeText(this, getString(R.string.pleaseEnterMobile), Toast.LENGTH_SHORT).show();
            } else {
                callingVerifyNoOrEmail(AppCommon.getInstance(this).getDeviceToken(),
                        emailOrNumber.getText().toString().trim(), "contact");
            }
        } else {
            if (TextUtils.isEmpty(emailOrNumber.getText().toString()) ||
                    !Patterns.EMAIL_ADDRESS.matcher(emailOrNumber.getText().toString()).matches()) {
                Toast.makeText(this, getString(R.string.pleaseEnterEmail), Toast.LENGTH_SHORT).show();
            } else {
                callingVerifyNoOrEmail(AppCommon.getInstance(this).getDeviceToken(),
                        emailOrNumber.getText().toString().trim(), "email");
            }
        }
    }

    private void callingVerifyNoOrEmail(String tokenKey, final String numberOrEmail, final String comingFrom) {
        AppCommon.getInstance(this).setNonTouchableFlags(this);
        if (AppCommon.getInstance(ForgetPasswordActvity.this).isConnectingToInternet(ForgetPasswordActvity.this)) {
            progressBar.setVisibility(View.VISIBLE);
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("TokenKey", tokenKey);
            hashMap.put("EmailorMobile", numberOrEmail);

            FoodMonkeyAppService foodMonkeyAppService = ServiceGenerator.createService(FoodMonkeyAppService.class);
            call = foodMonkeyAppService.VerifyValidEmailNumber(hashMap);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(ForgetPasswordActvity.this).clearNonTouchableFlags(ForgetPasswordActvity.this);
                    if (response.code() == 200) {
                        progressBar.setVisibility(View.GONE);
                        VaildNumberResponse vaildNumberResponse = (VaildNumberResponse) response.body();
                        if (vaildNumberResponse != null) {
                            if (vaildNumberResponse.getCode().equals("200")) {
                                Intent intent = new Intent(ForgetPasswordActvity.this, EnterOTPActivity.class);
                                intent.putExtra("customerID", vaildNumberResponse.getCustomerId());
                                intent.putExtra("comingFrom", comingFrom);
                                intent.putExtra("phoneOrEmail", numberOrEmail);
                                startActivity(intent);
                            } else {
                                AppCommon.showDialog(ForgetPasswordActvity.this, vaildNumberResponse.getMessage());
                            }
                        }
                    } else {
                        AppCommon.showDialog(ForgetPasswordActvity.this, getString(R.string.serverError));
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    AppCommon.getInstance(ForgetPasswordActvity.this).clearNonTouchableFlags(ForgetPasswordActvity.this);
                    progressBar.setVisibility(View.GONE);
                    if (t instanceof JsonSyntaxException) {
                        AppCommon.showDialog(ForgetPasswordActvity.this, "No resturant found");
                    } else {
                        AppCommon.showDialog(ForgetPasswordActvity.this, getResources().getString(R.string.network_error));
                    }
                }
            });
        } else {
            AppCommon.getInstance(ForgetPasswordActvity.this).clearNonTouchableFlags(ForgetPasswordActvity.this);
            AppCommon.showDialog(this, getResources().getString(R.string.network_error));
        }
    }

//
//    private void callingOTP_API(String tokenKey, final String number) {
//        AppCommon.getInstance(this).setNonTouchableFlags(this);
//        if (AppCommon.getInstance(ForgetPasswordActvity.this).isConnectingToInternet(ForgetPasswordActvity.this)) {
//            progressBar.setVisibility(View.VISIBLE);
//            HashMap<String,String>hashMap=new HashMap<>();
//            hashMap.put("TokenKey",tokenKey);
//            hashMap.put("Mobile_number",number);
//
//            FoodMonkeyAppService foodMonkeyAppService = ServiceGenerator.createService(FoodMonkeyAppService.class);
//            call = foodMonkeyAppService.SendingOTPRequest(hashMap);
//            call.enqueue(new Callback() {
//                @Override
//                public void onResponse(Call call, Response response) {
//                    AppCommon.getInstance(ForgetPasswordActvity.this).clearNonTouchableFlags(ForgetPasswordActvity.this);
//                    if (response.code() == 200) {
//                        progressBar.setVisibility(View.GONE);
//                        SendingOTP_Response sendingOTPResponse = (SendingOTP_Response) response.body();
//                        if (sendingOTPResponse != null) {
//                            if (sendingOTPResponse.getCode().equals("200")) {
//                                Intent intent =new Intent(ForgetPasswordActvity.this,EnterOTPActivity.class);
//                                intent.putExtra("requestID",sendingOTPResponse.getResult().getRequestId());
//                                intent.putExtra("phoneNumber",number);
//                                startActivity(intent);
//                            } else {
//                                AppCommon.showDialog(ForgetPasswordActvity.this, sendingOTPResponse.getMessage());
//                            }
//                        }
//                    } else {
//                        AppCommon.showDialog(ForgetPasswordActvity.this, getString(R.string.serverError));
//                    }
//                }
//
//                @Override
//                public void onFailure(Call call, Throwable t) {
//                    AppCommon.getInstance(ForgetPasswordActvity.this).clearNonTouchableFlags(ForgetPasswordActvity.this);
//                    progressBar.setVisibility(View.GONE);
//                    if (t instanceof JsonSyntaxException) {
//                        AppCommon.showDialog(ForgetPasswordActvity.this, "No resturant found");
//                    } else {
//                        AppCommon.showDialog(ForgetPasswordActvity.this, getResources().getString(R.string.network_error));
//                    }
//                }
//            });
//        } else {
//            AppCommon.getInstance(ForgetPasswordActvity.this).clearNonTouchableFlags(ForgetPasswordActvity.this);
//            AppCommon.showDialog(this, getResources().getString(R.string.network_error));
//        }
//    }
//

}

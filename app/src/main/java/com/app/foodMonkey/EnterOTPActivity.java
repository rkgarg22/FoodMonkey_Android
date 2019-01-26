package com.app.foodMonkey;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonSyntaxException;
import com.app.foodMonkey.API.FoodMonkeyAppService;
import com.app.foodMonkey.API.ServiceGenerator;
import com.app.foodMonkey.ApiResponse.CommonResponse;
import com.app.foodMonkey.ApiResponse.SendOTPEmailResponse;
import com.app.foodMonkey.ApiResponse.SendingOTP_Response;

import java.util.HashMap;
import java.util.logging.Logger;

import Infrastructure.AppCommon;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EnterOTPActivity extends AppCompatActivity {
    @BindView(R.id.toolbarText)
    TextView toolText;

    @BindView(R.id.left)
    ImageView left;

    @BindView(R.id.editText1)
    EditText editText1;

    @BindView(R.id.editText2)
    EditText editText2;

    @BindView(R.id.editText3)
    EditText editText3;

    @BindView(R.id.editText4)
    EditText editText4;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    //    String phoneNumber = "";
    String requestID = "";
    String customerID = "";
    String phoneOrEmail = "";
    String comingFrom = "";

    Call call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_otp);
        ButterKnife.bind(this);
        left.setVisibility(View.VISIBLE);
        toolText.setText(getString(R.string.enterOTP));
//        intent.putExtra("requestID",sendingOTPResponse.getResult().getRequestId());
//        intent.putExtra("phoneNumber",number);

        if (getIntent() != null) {
            customerID = String.valueOf(getIntent().getIntExtra("customerID", -1));
            phoneOrEmail = getIntent().getStringExtra("phoneOrEmail");
            comingFrom = getIntent().getStringExtra("comingFrom");

            if (comingFrom.equals("contact")) {
                callingOTP_API(AppCommon.getInstance(this).getDeviceToken(), phoneOrEmail);
            } else {
                callingOTP_Email_API(AppCommon.getInstance(this).getDeviceToken(), phoneOrEmail);
            }
        }

        editText1.addTextChangedListener(new GenricTextWatcher(editText1));
        editText2.addTextChangedListener(new GenricTextWatcher(editText2));
        editText3.addTextChangedListener(new GenricTextWatcher(editText3));
        editText4.addTextChangedListener(new GenricTextWatcher(editText4));
    }

    @OnClick(R.id.left)
    void setLeft() {
        onBackPressed();
    }

    @OnClick(R.id.submitButton)
    void setSubmitButton() {
        if (editText1.getText().toString().isEmpty()) {
            Toast.makeText(this, getString(R.string.pleaseEnterFullOTP), Toast.LENGTH_SHORT).show();

        } else if (editText2.getText().toString().isEmpty()) {
            Toast.makeText(this, getString(R.string.pleaseEnterFullOTP), Toast.LENGTH_SHORT).show();

        } else if (editText3.getText().toString().isEmpty()) {
            Toast.makeText(this, getString(R.string.pleaseEnterFullOTP), Toast.LENGTH_SHORT).show();

        } else if (editText4.getText().toString().isEmpty()) {
            Toast.makeText(this, getString(R.string.pleaseEnterFullOTP), Toast.LENGTH_SHORT).show();

        } else {
            String otp = editText1.getText().toString() + editText2.getText().toString() + editText3.getText().toString() + editText4.getText().toString();
            if (comingFrom.equals("contact")) {
                callingReceiveOTP(AppCommon.getInstance(this).getDeviceToken(), otp, requestID);
            } else {
                if (emailOTP == Integer.parseInt(otp)) {
                    Intent intent = new Intent(EnterOTPActivity.this, CreatePasswordActivity.class);
                    intent.putExtra("customerID", customerID);
                    startActivity(intent);
                } else {
                    AppCommon.showDialog(this, "OTP not matched");
                }
            }
        }
    }

    @OnClick(R.id.resendOTP)
    void setResendOTP() {
        if (comingFrom.equals("contact")) {
            callingOTP_API(AppCommon.getInstance(this).getDeviceToken(), phoneOrEmail);
        } else {
            callingOTP_Email_API(AppCommon.getInstance(this).getDeviceToken(), phoneOrEmail);
        }
    }

    int emailOTP = -1;

    private void callingOTP_API(String tokenKey, String number) {
        AppCommon.getInstance(this).setNonTouchableFlags(this);
        if (AppCommon.getInstance(EnterOTPActivity.this).isConnectingToInternet(EnterOTPActivity.this)) {
            progressBar.setVisibility(View.VISIBLE);
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("TokenKey", tokenKey);
            hashMap.put("Mobile_number", number);

            FoodMonkeyAppService foodMonkeyAppService = ServiceGenerator.createService(FoodMonkeyAppService.class);
            call = foodMonkeyAppService.SendingOTPRequest(hashMap);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(EnterOTPActivity.this).clearNonTouchableFlags(EnterOTPActivity.this);
                    if (response.code() == 200) {
                        progressBar.setVisibility(View.GONE);
                        SendingOTP_Response sendingOTPResponse = (SendingOTP_Response) response.body();
                        if (sendingOTPResponse.getCode().equals("200")) {
                            requestID = sendingOTPResponse.getResult().getRequestId();
                            AppCommon.showDialog(EnterOTPActivity.this, sendingOTPResponse.getMessage());
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(EnterOTPActivity.this);
                            builder.setTitle(sendingOTPResponse.getMessage());
                            builder.setNegativeButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                    finish();
                                }
                            });
                            builder.show();
                        }
                    } else {
                        AppCommon.showDialog(EnterOTPActivity.this, getString(R.string.serverError));
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    AppCommon.getInstance(EnterOTPActivity.this).clearNonTouchableFlags(EnterOTPActivity.this);
                    progressBar.setVisibility(View.GONE);
                    if (t instanceof JsonSyntaxException) {
                        AppCommon.showDialog(EnterOTPActivity.this, "No resturant found");
                    } else {
                        AppCommon.showDialog(EnterOTPActivity.this, getResources().getString(R.string.network_error));
                    }
                }
            });
        } else {
            AppCommon.getInstance(EnterOTPActivity.this).clearNonTouchableFlags(EnterOTPActivity.this);
            AppCommon.showDialog(this, getResources().getString(R.string.network_error));
        }
    }

    private void callingOTP_Email_API(String tokenKey, String email) {
        AppCommon.getInstance(this).setNonTouchableFlags(this);
        if (AppCommon.getInstance(EnterOTPActivity.this).isConnectingToInternet(EnterOTPActivity.this)) {
            progressBar.setVisibility(View.VISIBLE);
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("TokenKey", tokenKey);
            hashMap.put("Email", email);

            FoodMonkeyAppService foodMonkeyAppService = ServiceGenerator.createService(FoodMonkeyAppService.class);
            call = foodMonkeyAppService.SendEmailOTP_API(hashMap);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(EnterOTPActivity.this).clearNonTouchableFlags(EnterOTPActivity.this);
                    if (response.code() == 200) {
                        progressBar.setVisibility(View.GONE);
                        SendOTPEmailResponse sendOTPEmailResponse = (SendOTPEmailResponse) response.body();
                        if (sendOTPEmailResponse != null) {
                            emailOTP = sendOTPEmailResponse.getOTPCode();
                            AppCommon.showDialog(EnterOTPActivity.this, sendOTPEmailResponse.getMessage());
                            Logger.getLogger("-->"+emailOTP);
                        }
                    } else {
                        AppCommon.showDialog(EnterOTPActivity.this, getString(R.string.serverError));
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    AppCommon.getInstance(EnterOTPActivity.this).clearNonTouchableFlags(EnterOTPActivity.this);
                    progressBar.setVisibility(View.GONE);
                    if (t instanceof JsonSyntaxException) {
                        AppCommon.showDialog(EnterOTPActivity.this, "No resturant found");
                    } else {
                        AppCommon.showDialog(EnterOTPActivity.this, getResources().getString(R.string.network_error));
                    }
                }
            });
        } else {
            AppCommon.getInstance(EnterOTPActivity.this).clearNonTouchableFlags(EnterOTPActivity.this);
            AppCommon.showDialog(this, getResources().getString(R.string.network_error));
        }
    }


    private void callingReceiveOTP(String tokenKey, String otp, String requestID) {
        AppCommon.getInstance(this).setNonTouchableFlags(this);
        if (AppCommon.getInstance(EnterOTPActivity.this).isConnectingToInternet(EnterOTPActivity.this)) {
            progressBar.setVisibility(View.VISIBLE);
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("TokenKey", tokenKey);
            hashMap.put("OTP", otp);
            hashMap.put("Request_Id", requestID);

            FoodMonkeyAppService foodMonkeyAppService = ServiceGenerator.createService(FoodMonkeyAppService.class);
            call = foodMonkeyAppService.RecieveOTPRequest(hashMap);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(EnterOTPActivity.this).clearNonTouchableFlags(EnterOTPActivity.this);
                    if (response.code() == 200) {
                        progressBar.setVisibility(View.GONE);
                        CommonResponse commonResponse = (CommonResponse) response.body();
                        if (commonResponse != null) {
                            if (commonResponse.getCode().equals("200")) {
                                Intent intent = new Intent(EnterOTPActivity.this, CreatePasswordActivity.class);
                                intent.putExtra("customerID", customerID);
                                startActivity(intent);
                            } else
                                AppCommon.showDialog(EnterOTPActivity.this, commonResponse.getMessage());
                        }
                    } else {
                        AppCommon.showDialog(EnterOTPActivity.this, getString(R.string.serverError));
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    AppCommon.getInstance(EnterOTPActivity.this).clearNonTouchableFlags(EnterOTPActivity.this);
                    progressBar.setVisibility(View.GONE);
                    if (t instanceof JsonSyntaxException) {
                        AppCommon.showDialog(EnterOTPActivity.this, "No resturant found");
                    } else {
                        AppCommon.showDialog(EnterOTPActivity.this, getResources().getString(R.string.network_error));
                    }
                }
            });
        } else {
            AppCommon.getInstance(EnterOTPActivity.this).clearNonTouchableFlags(EnterOTPActivity.this);
            AppCommon.showDialog(this, getResources().getString(R.string.network_error));
        }
    }


    public class GenricTextWatcher implements TextWatcher {
        private View view;

        private GenricTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // TODO Auto-generated method stub
            String text = editable.toString();
            switch (view.getId()) {

                case R.id.editText1:
                    if (text.length() == 1)
                        editText2.requestFocus();
                    break;
                case R.id.editText2:
                    if (text.length() == 1)
                        editText3.requestFocus();
                    break;
                case R.id.editText3:
                    if (text.length() == 1)
                        editText4.requestFocus();
                    break;
                case R.id.editText4:
                    break;
            }
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
        }
    }

}

package com.kartik.project.foodmonkey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonSyntaxException;
import com.kartik.project.foodmonkey.API.FoodMonkeyAppService;
import com.kartik.project.foodmonkey.API.ServiceGenerator;
import com.kartik.project.foodmonkey.ApiResponse.ChangePassForgotResp;
import com.kartik.project.foodmonkey.ApiResponse.CommonResponse;

import java.util.HashMap;

import Infrastructure.AppCommon;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreatePasswordActivity extends AppCompatActivity {
    @BindView(R.id.left)
    ImageView left;

    @BindView(R.id.toolbarText)
    TextView toolbarText;

    @BindView(R.id.newPassword)
    EditText newPassword;

    @BindView(R.id.confirmPassword)
    EditText confirmPassword;

    @BindView(R.id.submitChoice)
    Button submitChoice;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.passwordLayout)
    RelativeLayout passwordLayout;

    @BindView(R.id.successFullLayout)
    LinearLayout successFullLayout;

    Call call;

    String customerID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_password);
        ButterKnife.bind(this);
        left.setVisibility(View.VISIBLE);
        toolbarText.setText(getString(R.string.createPassword));
        if (getIntent() != null) {
            customerID = getIntent().getStringExtra("customerID");
        }
    }

    @OnClick(R.id.left)
    void setLeft() {
        onBackPressed();
    }

    @OnClick(R.id.submitChoice)
    void setSubmitChoice() {
        String newPassword = this.newPassword.getText().toString();
        String confirmPassword = this.confirmPassword.getText().toString();

        if (newPassword.isEmpty()) {
            Toast.makeText(this, getString(R.string.pleaseEnterNewPassword), Toast.LENGTH_SHORT).show();
        } else if (confirmPassword.isEmpty()) {
            Toast.makeText(this, getString(R.string.pleaseEnterConfirmPassword), Toast.LENGTH_SHORT).show();
        } else if (!newPassword.equals(confirmPassword)) {
            Toast.makeText(this, getString(R.string.newPasswordAndConfirmPassword), Toast.LENGTH_SHORT).show();
        } else {
            callingChangePasswordAPI(customerID, confirmPassword);
        }
    }

    private void callingChangePasswordAPI(String customerID, String newPassword) {
        AppCommon.getInstance(this).setNonTouchableFlags(this);
        if (AppCommon.getInstance(CreatePasswordActivity.this).isConnectingToInternet(CreatePasswordActivity.this)) {
            progressBar.setVisibility(View.VISIBLE);
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("TokenKey", AppCommon.getInstance(this).getDeviceToken());
            hashMap.put("Customer_id", customerID);
            hashMap.put("New_Password", newPassword);

            FoodMonkeyAppService foodMonkeyAppService = ServiceGenerator.createService(FoodMonkeyAppService.class);
            call = foodMonkeyAppService.ForgetChangePassword(hashMap);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(CreatePasswordActivity.this).clearNonTouchableFlags(CreatePasswordActivity.this);
                    if (response.code() == 200) {
                        progressBar.setVisibility(View.GONE);
                        ChangePassForgotResp commonResponse = (ChangePassForgotResp) response.body();
                        if (commonResponse != null) {
                            if (commonResponse.getMessage().equals("Your Password is changed")) {
                                toolbarText.setText(getString(R.string.passwordChanged));
                                successFullLayout.setVisibility(View.VISIBLE);
                                passwordLayout.setVisibility(View.GONE);
                            } else {
                                AppCommon.showDialog(CreatePasswordActivity.this, commonResponse.getMessage());
                            }
                        }
                    } else {
                        AppCommon.showDialog(CreatePasswordActivity.this, getString(R.string.serverError));
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    AppCommon.getInstance(CreatePasswordActivity.this).clearNonTouchableFlags(CreatePasswordActivity.this);
                    progressBar.setVisibility(View.GONE);
                    if (t instanceof JsonSyntaxException) {
                        AppCommon.showDialog(CreatePasswordActivity.this, "No resturant found");
                    } else {
                        AppCommon.showDialog(CreatePasswordActivity.this, getResources().getString(R.string.network_error));
                    }
                }
            });
        } else {
            AppCommon.getInstance(CreatePasswordActivity.this).clearNonTouchableFlags(CreatePasswordActivity.this);
            AppCommon.showDialog(this, getResources().getString(R.string.network_error));
        }
    }

    @OnClick(R.id.backToLogin)
    void setBackToLogin() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        Intent intent2 = new Intent(this, LoginActivity.class);
        startActivity(intent2);
        this.finishAffinity();
//        finish();
    }
}

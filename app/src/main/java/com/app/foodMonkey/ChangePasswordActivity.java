package com.app.foodMonkey;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.foodMonkey.API.FoodMonkeyAppService;
import com.app.foodMonkey.API.ServiceGenerator;
import com.app.foodMonkey.ApiResponse.ReferByMobileResponse;
import com.google.gson.JsonSyntaxException;

import java.util.HashMap;

import Infrastructure.AppCommon;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActivity extends AppCompatActivity {

    @BindView(R.id.left)
    ImageView left;

    @BindView(R.id.toolbarText)
    TextView toolbarText;

    @BindView(R.id.oldPasswordText)
    TextView oldPasswordText;

    @BindView(R.id.newPasswordText)
    TextView newPasswordText;

    @BindView(R.id.confirmPasswordText)
    TextView confirmPasswordText;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.submit)
    Button submit;

    Call call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        ButterKnife.bind(this);
        left.setVisibility(View.VISIBLE);
        toolbarText.setText(getString(R.string.changePassword));
    }

    @OnClick(R.id.left)
    void setLeft() {
        onBackPressed();
    }


    boolean showPasswordFlag = false;

    @OnClick(R.id.showCurrentPassword)
    void setShowPassword() {
        if (showPasswordFlag) {
            showPasswordFlag = false;
            oldPasswordText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        } else {
            showPasswordFlag = true;
            oldPasswordText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
    }

    boolean showNewPasswordFlag = false;

    @OnClick(R.id.showNewPassword)
    void setShowNewPassword() {
        if (showNewPasswordFlag) {
            showNewPasswordFlag = false;
            newPasswordText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        } else {
            showNewPasswordFlag = true;
            newPasswordText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
    }

    boolean showConfirmPasswordFlag = false;

    @OnClick(R.id.showConfirmPassword)
    void setShowConfirmPassword() {
        if (showConfirmPasswordFlag) {
            showConfirmPasswordFlag = false;
            confirmPasswordText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        } else {
            showConfirmPasswordFlag = true;
            confirmPasswordText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
    }

    @OnClick(R.id.submit)
    void submitPassword() {
        String oldPasswordText = this.oldPasswordText.getText().toString();
        String newPasswordText = this.newPasswordText.getText().toString();
        String confirmPasswordText = this.confirmPasswordText.getText().toString();
        if (oldPasswordText.isEmpty()) {
            Toast.makeText(this, getString(R.string.pleaseEnterOldPassword), Toast.LENGTH_LONG).show();
        } else if (newPasswordText.isEmpty()) {
            Toast.makeText(this, getString(R.string.pleaseEnterNewPassword), Toast.LENGTH_SHORT).show();
        } else if (confirmPasswordText.isEmpty()) {
            Toast.makeText(this, getString(R.string.pleaseEnterConfirmPassword), Toast.LENGTH_SHORT).show();
        } else if (!newPasswordText.equals(confirmPasswordText)) {
            AppCommon.showDialog(ChangePasswordActivity.this, "New password and confirmed password not matched with each other");
        } else {
            callingChangePasswordAPI(oldPasswordText, newPasswordText);
        }
    }

    private void callingChangePasswordAPI(String oldPasswordText, String newPasswordText) {
        AppCommon.getInstance(this).setNonTouchableFlags(this);
        if (AppCommon.getInstance(ChangePasswordActivity.this).isConnectingToInternet(ChangePasswordActivity.this)) {
            progressBar.setVisibility(View.VISIBLE);
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("TokenKey", AppCommon.getInstance(this).getDeviceToken());
            hashMap.put("Customer_id", AppCommon.getInstance(this).getCustomerID());
            hashMap.put("Old_Password", oldPasswordText);
            hashMap.put("New_Password", newPasswordText);
            FoodMonkeyAppService foodMonkeyAppService = ServiceGenerator.createService(FoodMonkeyAppService.class);
            call = foodMonkeyAppService.ChangePasswordAPI(hashMap);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(ChangePasswordActivity.this).clearNonTouchableFlags(ChangePasswordActivity.this);
                    if (response.code() == 200) {
                        progressBar.setVisibility(View.GONE);
                        ReferByMobileResponse changePasswordResponse = (ReferByMobileResponse) response.body();
                        if (changePasswordResponse != null) {
                            if (changePasswordResponse.getCode() != null) {
                                if (changePasswordResponse.getCode().equals("200")) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(ChangePasswordActivity.this);
                                    builder.setTitle(changePasswordResponse.getMessage());
                                    builder.setNegativeButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.dismiss();
                                            finish();
                                        }
                                    });
                                    builder.show();
                                } else {
                                    AppCommon.showDialog(ChangePasswordActivity.this, changePasswordResponse.getMessage());
                                }
                            } else {
                                AppCommon.showDialog(ChangePasswordActivity.this, changePasswordResponse.getMessage());
                            }
                        }
                    } else {
                        AppCommon.showDialog(ChangePasswordActivity.this, getString(R.string.serverError));
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    AppCommon.getInstance(ChangePasswordActivity.this).clearNonTouchableFlags(ChangePasswordActivity.this);
                    progressBar.setVisibility(View.GONE);
                    if (t instanceof JsonSyntaxException) {
                        AppCommon.showDialog(ChangePasswordActivity.this, "JSON Format Exception");
                    } else {
                        AppCommon.showDialog(ChangePasswordActivity.this, getResources().getString(R.string.network_error));

                    }
                }
            });
        } else {
            AppCommon.getInstance(ChangePasswordActivity.this).clearNonTouchableFlags(ChangePasswordActivity.this);
            AppCommon.showDialog(this, getResources().getString(R.string.network_error));
        }
    }

}

package com.kartik.project.foodmonkey;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.LinkMovementMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.kartik.project.foodmonkey.API.FoodMonkeyAppService;
import com.kartik.project.foodmonkey.API.ServiceGenerator;
import com.kartik.project.foodmonkey.ApiEntity.LoginEntity;
import com.kartik.project.foodmonkey.ApiResponse.LoginCustomerResponse;

import Infrastructure.AppCommon;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.left)
    ImageView left;

    @BindView(R.id.toolbarText)
    TextView toolbarText;

    @BindView(R.id.signUpText)
    TextView signUpText;

    @BindView(R.id.email)
    EditText email;

    @BindView(R.id.password)
    EditText password;

    @BindView(R.id.showPassword)
    ImageView showPassword;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        left.setVisibility(View.VISIBLE);
        toolbarText.setText(getString(R.string.login));
        setSignUpText();
    }

    boolean showPasswordFlag = false;

    @OnClick(R.id.showPassword)
    void setShowPassword() {
        if (showPasswordFlag) {
            showPasswordFlag = false;
            password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        } else {
            showPasswordFlag = true;
            password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
    }

    @OnClick(R.id.loginButton)
    void setLoginButton() {
        if (validations()) {
            loginAccount(AppCommon.getInstance(this).getDeviceToken(),
                    email.getText().toString().trim(),
                    password.getText().toString().trim());
        }

//        startActivity(new Intent(this,HomeActivity.class));
    }

    private void setSignUpText() {
        SpannableString spannableString = new SpannableString(getString(R.string.dontHaveAccount));
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
//                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
                ds.setColor(getResources().getColor(R.color.colorPrimary));
            }
        };
        spannableString.setSpan(clickableSpan, 24, 31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        signUpText.setText(spannableString);
        signUpText.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @OnClick(R.id.forgotPassword)
    void setForgotPassword() {
        startActivity(new Intent(this, ForgetPasswordActvity.class));
    }

    @OnClick(R.id.signUpText)
    void setSignUp() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivityForResult(intent, 200);
    }

    boolean validations() {
        boolean flag = true;
        String email = this.email.getText().toString();
        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, getString(R.string.pleaseEnterEmail), Toast.LENGTH_LONG).show();
            flag = false;
        } else if (password.getText().toString().isEmpty()) {
            Toast.makeText(this, getString(R.string.pleaseEnterPassword), Toast.LENGTH_LONG).show();
            flag = false;
        }
        return flag;
    }

    Call call;

    void loginAccount(String tokenKey, String email, String password) {
        AppCommon.getInstance(this).setNonTouchableFlags(this);
        if (AppCommon.getInstance(LoginActivity.this).isConnectingToInternet(LoginActivity.this)) {
            progressBar.setVisibility(View.VISIBLE);
            //  final String token = myFirebaseInstanceIDService.getDeviceToken();
            LoginEntity loginEntity = new LoginEntity(tokenKey, email, password, "App");
            FoodMonkeyAppService foodMonkeyAppService = ServiceGenerator.createService(FoodMonkeyAppService.class);
            call = foodMonkeyAppService.getLoginApi(loginEntity);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(LoginActivity.this).clearNonTouchableFlags(LoginActivity.this);
                    if (response.code() == 200) {
                        progressBar.setVisibility(View.GONE);
                        LoginCustomerResponse loginCustomerResponse = (LoginCustomerResponse) response.body();
                        if (response.body() != null) {
                            if (loginCustomerResponse.getCode().equals("200")) {
                                AppCommon.getInstance(LoginActivity.this).setCustomerID(String.valueOf(
                                        loginCustomerResponse.getCustomerDetails().get(0).getCustomerId()));
                                AppCommon.getInstance(LoginActivity.this).setFirstName(
                                        loginCustomerResponse.getCustomerDetails().get(0).getFirstName().trim());
                                AppCommon.getInstance(LoginActivity.this).setSurName(
                                        loginCustomerResponse.getCustomerDetails().get(0).getSurName().trim());
                                AppCommon.getInstance(LoginActivity.this).setMiddleName(
                                        loginCustomerResponse.getCustomerDetails().get(0).getMiddleIntial().trim());
                                AppCommon.getInstance(LoginActivity.this).setGender(
                                        loginCustomerResponse.getCustomerDetails().get(0).getGender().trim());
                                AppCommon.getInstance(LoginActivity.this).setEmailAddress(
                                        loginCustomerResponse.getCustomerDetails().get(0).getEmail().trim());
                                AppCommon.getInstance(LoginActivity.this).setMobileNumber(
                                        loginCustomerResponse.getCustomerDetails().get(0).getMobile().trim());
                                AppCommon.getInstance(LoginActivity.this).setDateOfBirth(
                                        loginCustomerResponse.getCustomerDetails().get(0).getDOB().trim());
                                AppCommon.getInstance(LoginActivity.this).setProfilePic(
                                        loginCustomerResponse.getCustomerDetails().get(0).getImageLink().trim());
                                AppCommon.getInstance(LoginActivity.this).setStatus(
                                        loginCustomerResponse.getCustomerDetails().get(0).getStatus());
                                AppCommon.getInstance(LoginActivity.this).setIsUserLogIn(true);

                                Intent intent = getIntent();
                                intent.putExtra("data", true);
                                setResult(RESULT_OK, intent);
                                finish();
//                            User user = registrationResponse.getUserEntity();

//                            try {
//                                AppCommon.getInstance(HomeActivity.this).setUserLatitude(Double.parseDouble(latitude));
//                                AppCommon.getInstance(HomeActivity.this).setUserLongitude(Double.parseDouble(longitude));
//                            } catch (Exception e) {
//
//                            }
                            } else {
                                AppCommon.showDialog(LoginActivity.this, loginCustomerResponse.getMessage());
                            }
                        }
                    } else {
                        AppCommon.showDialog(LoginActivity.this, getString(R.string.serverError));
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    AppCommon.getInstance(LoginActivity.this).clearNonTouchableFlags(LoginActivity.this);
                    progressBar.setVisibility(View.GONE);
                    if (t instanceof JsonSyntaxException) {
                        AppCommon.showDialog(LoginActivity.this, "No resturant found");
                    } else {
                        AppCommon.showDialog(LoginActivity.this, getResources().getString(R.string.network_error));

                    }
                }
            });
        } else {
            AppCommon.getInstance(LoginActivity.this).clearNonTouchableFlags(LoginActivity.this);
            AppCommon.showDialog(this, getResources().getString(R.string.network_error));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 200) {
                Intent intent = getIntent();
                intent.putExtra("data", true);
                setResult(RESULT_OK, intent);
                finish();
            }
        }
    }
}

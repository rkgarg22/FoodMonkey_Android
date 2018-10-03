package com.kartik.project.foodmonkey;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
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
import com.kartik.project.foodmonkey.ApiEntity.CustomerSignUpEntity;
import com.kartik.project.foodmonkey.ApiResponse.CustomerSignUpResponse;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import Infrastructure.AppCommon;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    @BindView(R.id.left)
    ImageView left;

    @BindView(R.id.toolbarText)
    TextView toolbarText;

    @BindView(R.id.loginText)
    TextView loginText;

    @BindView(R.id.firstName)
    EditText firstName;

    @BindView(R.id.middleName)
    EditText middleName;

    @BindView(R.id.surName)
    EditText surName;

    @BindView(R.id.male)
    CheckBox male;

    @BindView(R.id.female)
    CheckBox female;

    @BindView(R.id.email)
    EditText email;

    @BindView(R.id.mobile)
    EditText mobile;

    @BindView(R.id.dobLayout)
    RelativeLayout dobLayout;

    @BindView(R.id.dateOfBirth)
    TextView dateOfBirth;

    @BindView(R.id.password)
    EditText password;

    @BindView(R.id.readTerms)
    TextView readTerms;

    @BindView(R.id.termsLayout)
    LinearLayout termsLayout;

    @BindView(R.id.checkTerms)
    CheckBox checkTerms;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.createAccountButton)
    Button createAccountButton;

    Call call;

    String gender = "";

    Calendar myCalendar = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
//            myCalendar.set(Calendar.YEAR, year);
//            myCalendar.set(Calendar.MONTH, monthOfYear);
//            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel(year,(monthOfYear+1),dayOfMonth);
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        left.setVisibility(View.VISIBLE);
        toolbarText.setText(getString(R.string.signUp));
        setLoginText();
        setReadTerms();
        setGenderToogle();
    }

    private void setLoginText() {
        SpannableString spannableString = new SpannableString(getString(R.string.haveAnAccount));
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                finish();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
                ds.setColor(getResources().getColor(R.color.colorPrimary));
            }
        };
        spannableString.setSpan(clickableSpan, 17, 22, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        loginText.setText(spannableString);
        loginText.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void setReadTerms() {
        SpannableString spannableString = new SpannableString(getString(R.string.readAcceptTermsAndConditions));
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
                ds.setColor(getResources().getColor(R.color.colorPrimary));
            }
        };
        spannableString.setSpan(clickableSpan, 21, 41, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        readTerms.setText(spannableString);
        readTerms.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void setGenderToogle() {
        male.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    female.setChecked(false);
                    gender = getString(R.string.male).toLowerCase();
                }
            }
        });

        female.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    male.setChecked(false);
                    gender = getString(R.string.female).toLowerCase();
                }
            }
        });
    }

    String dateOfBirthInput = "";
    SimpleDateFormat sdf;
    private void updateLabel(int year, int monthOfYear, int dayOfMonth) {
//        String inputFormat = "yyyy/mm/dd"; //In which you need put here
//        sdf = new SimpleDateFormat(inputFormat, Locale.US);
        dateOfBirthInput = year+"/"+monthOfYear+"/"+dayOfMonth;
        dateOfBirth.setText(monthOfYear+"/"+dayOfMonth+"/"+year);

//        sdf = new SimpleDateFormat("mm/dd/yyyy", Locale.US);

//        dateOfBirth.setText(sdf.format(myCalendar.getTime()));
    }

    @OnClick(R.id.left)
    void setLeft() {
        onBackPressed();
    }

    @OnClick(R.id.dobLayout)
    void setDobLayout() {
        new DatePickerDialog(SignUpActivity.this, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    @OnClick(R.id.createAccountButton)
    void setCreateAccountButton() {
        if (validation()) {
//         callingSignUpApi();
        }
    }

    boolean validation() {
        boolean status = true;
        String firstName = this.firstName.getText().toString().trim();
        String middleName = this.middleName.getText().toString().trim();
        String surName = this.surName.getText().toString().trim();
        String email = this.email.getText().toString().trim();
        String mobile = this.mobile.getText().toString().trim();
        String dateOfBirth = this.dateOfBirth.getText().toString().trim();
        String password = this.password.getText().toString().trim();

        if (firstName.isEmpty()) {
            this.firstName.setError(getString(R.string.pleaseEnterFirstName));
            status = false;
        } else if (middleName.isEmpty()) {
            this.middleName.setError(getString(R.string.pleaseEnterMiddleName));
            status = false;
        } else if (surName.isEmpty()) {
            this.surName.setError(getString(R.string.pleaseEnterSurName));
            status = false;
        } else if (!AppCommon.getInstance(this).isEmailValid(email)) {
            this.email.setError(getString(R.string.pleaseEnterEmail));
            status = false;
        } else if (mobile.isEmpty()) {
            this.mobile.setError(getString(R.string.pleaseEnterMobile));
            status = false;
        } else if (dateOfBirth.isEmpty()) {
            AppCommon.showDialog(this, getString(R.string.pleaseSelectDateOfBirth));
            status = false;
        } else if (password.isEmpty()) {
            this.password.setError(getString(R.string.pleaseEnterPassword));
            status = false;
        }

        return status;
    }


    void callingSignUpApi(String tokenKey, String firstName, String middleName, String surName,
                          String gender, String email, String mobile, String dateOfBirth,
                          String password, String profilePic, String channelCalling) {
        AppCommon.getInstance(this).setNonTouchableFlags(this);
        if (AppCommon.getInstance(SignUpActivity.this).isConnectingToInternet(SignUpActivity.this)) {
            progressBar.setVisibility(View.VISIBLE);
            //  final String token = myFirebaseInstanceIDService.getDeviceToken();
            CustomerSignUpEntity customerSignUpEntity = new CustomerSignUpEntity(tokenKey, firstName, middleName, surName, gender,
                    email, mobile, dateOfBirth, password, profilePic, channelCalling);

            FoodMonkeyAppService foodMonkeyAppService = ServiceGenerator.createService(FoodMonkeyAppService.class);
            call = foodMonkeyAppService.customerSignUp(customerSignUpEntity);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(SignUpActivity.this).clearNonTouchableFlags(SignUpActivity.this);
                    if (response.code() == 200) {
                        progressBar.setVisibility(View.GONE);
                        CustomerSignUpResponse customerSignUpResponse = (CustomerSignUpResponse) response.body();
                        if (customerSignUpResponse.getCode().equals("200")) {
                            Toast.makeText(SignUpActivity.this, customerSignUpResponse.getMessage(), Toast.LENGTH_LONG).show();
//                            User user = registrationResponse.getUserEntity();

//                            try {
//                                AppCommon.getInstance(HomeActivity.this).setUserLatitude(Double.parseDouble(latitude));
//                                AppCommon.getInstance(HomeActivity.this).setUserLongitude(Double.parseDouble(longitude));
//                            } catch (Exception e) {
//
//                            }
                        } else {
                            AppCommon.showDialog(SignUpActivity.this, customerSignUpResponse.getMessage());
                        }
                    } else {
                        AppCommon.showDialog(SignUpActivity.this, getString(R.string.serverError));
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    AppCommon.getInstance(SignUpActivity.this).clearNonTouchableFlags(SignUpActivity.this);
                    progressBar.setVisibility(View.GONE);
                    if (t instanceof JsonSyntaxException) {
                        AppCommon.showDialog(SignUpActivity.this, "Something Went wrong ..Please try again.");
                    } else {
                        AppCommon.showDialog(SignUpActivity.this, getResources().getString(R.string.network_error));

                    }
                }
            });
        } else {
            AppCommon.getInstance(SignUpActivity.this).clearNonTouchableFlags(SignUpActivity.this);
            AppCommon.showDialog(this, getResources().getString(R.string.network_error));
        }
    }


}

package com.kartik.project.foodmonkey;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.LinkMovementMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ClickableSpan;
import android.util.Base64;
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

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.JsonSyntaxException;
import com.kartik.project.foodmonkey.API.FoodMonkeyAppService;
import com.kartik.project.foodmonkey.API.ServiceGenerator;
import com.kartik.project.foodmonkey.ApiEntity.CustomerSignUpEntity;
import com.kartik.project.foodmonkey.ApiResponse.CustomerSignUpResponse;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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

    private static final int SELECT_FILE = 1;

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

    @BindView(R.id.profilePic)
    SimpleDraweeView profilePic;

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
            updateLabel(year, (monthOfYear + 1), dayOfMonth);
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

    @OnClick(R.id.profilePic)
    void setProfilePicture() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.choose_option_dialog);
        dialog.setTitle(getResources().getString(R.string.app_name));
        TextView camera = (TextView) dialog.findViewById(R.id.camera);
        TextView gallery = (TextView) dialog.findViewById(R.id.gallery);
        TextView textViewCancel = (TextView) dialog.findViewById(R.id.cancel);

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestCameraPermission();
                dialog.dismiss();
            }
        });
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestGalleryPermission();
                dialog.dismiss();
            }
        });
        textViewCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
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
        dateOfBirthInput = year + "-" + monthOfYear + "-" + dayOfMonth;
        dateOfBirth.setText(monthOfYear + "/" + dayOfMonth + "/" + year);

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

    String base64Pic = "";

    @OnClick(R.id.createAccountButton)
    void setCreateAccountButton() {
        if (validation()) {
            callingSignUpApi(AppCommon.getInstance(this).getDeviceToken(), this.firstName.getText().toString().trim(), this.middleName.getText().toString().trim(),
                    this.surName.getText().toString().trim(), gender, this.email.getText().toString().trim(), this.mobile.getText().toString().trim(),
                    dateOfBirthInput, this.password.getText().toString().trim(),
                    base64Pic, "App");
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

    void callingSignUpApi(String tokenKey, final String firstName, final String middleName, final String surName,
                          final String gender, final String email, final String mobile, final String dateOfBirth,
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
                            AppCommon.getInstance(SignUpActivity.this).setCustomerID(customerSignUpResponse.getCustomerId());

                            AppCommon.getInstance(SignUpActivity.this).setFirstName(firstName);
                            AppCommon.getInstance(SignUpActivity.this).setSurName(surName);
                            AppCommon.getInstance(SignUpActivity.this).setMiddleName(middleName);
                            AppCommon.getInstance(SignUpActivity.this).setGender(gender);
                            AppCommon.getInstance(SignUpActivity.this).setEmailAddress(email);
                            AppCommon.getInstance(SignUpActivity.this).setMobileNumber(mobile);
                            AppCommon.getInstance(SignUpActivity.this).setDateOfBirth(dateOfBirth);
//                            AppCommon.getInstance(SignUpActivity.this).setProfilePic();
//                            AppCommon.getInstance(SignUpActivity.this).setStatus(
//                                    loginCustomerResponse.getCustomerDetails().get(0).getStatus());
                            AppCommon.getInstance(SignUpActivity.this).setIsUserLogIn(true);

                            Intent intent = getIntent();
                            intent.putExtra("data", true);
                            setResult(RESULT_OK, intent);
                            finish();

                            Toast.makeText(SignUpActivity.this, customerSignUpResponse.getMessage(), Toast.LENGTH_LONG).show();
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

    public void requestCameraPermission() {
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(SignUpActivity.this,
                    new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.CAMERA},
                    200);
        } else {
            startCameraIntent();
        }
    }

    public void startCameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(Environment.getExternalStorageDirectory(),
                "attachment.jpg");
        outPutfileUri = FileProvider.getUriForFile(SignUpActivity.this,
                BuildConfig.APPLICATION_ID + ".provider",
                file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outPutfileUri);
        startActivityForResult(intent, REQUEST_CAMERA);

    }

    public void requestGalleryPermission() {
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(SignUpActivity.this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(SignUpActivity.this,
                    new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE},
                    201);
        } else {
            startGalleryIntent();
        }
    }


    public void startGalleryIntent() {
        Intent galleryIntent = new Intent();
        galleryIntent.setType("image/*");
//        galleryIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(galleryIntent, "Select Picture"), SELECT_FILE);
    }

    private static final int REQUEST_CAMERA = 0;
    Uri outPutfileUri = Uri.parse("");
    String isMedia = "0";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE) {
                outPutfileUri = data.getData();
                profilePic.setImageURI(outPutfileUri);

                final InputStream imageStream;
                try {
                    imageStream = getContentResolver().openInputStream(outPutfileUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    selectedImage.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    byte[] b = baos.toByteArray();
                    base64Pic = Base64.encodeToString(b, Base64.DEFAULT);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            } else if (requestCode == REQUEST_CAMERA) {
                Bitmap bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), outPutfileUri);
                    String url = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "attachment", null);
                    outPutfileUri = Uri.parse(url);
                    profilePic.setImageURI(outPutfileUri);
                    base64Pic = AppCommon.getInstance(this).getFileToBase64(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            isMedia = "1";
        }
    }
}

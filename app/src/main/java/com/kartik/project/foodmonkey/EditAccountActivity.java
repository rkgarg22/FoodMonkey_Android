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
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
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

public class EditAccountActivity extends AppCompatActivity {

    private final int SELECT_FILE = 1;

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

    @BindView(R.id.dateOfBirthLayout)
    RelativeLayout dateOfBirthLayout;

    Call call;

    CustomerObject customerObject;

    private static final int REQUEST_CAMERA = 0;
    Uri outPutfileUri = Uri.parse("");
    String isMedia = "0";
    String base64Pic = "";

    final Calendar myCalendar = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

    private void updateLabel() {
        String myFormat = "yyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        dateOfBirth.setText(sdf.format(myCalendar.getTime()));
    }

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

    String profileUrl;

    private void setData(CustomerObject customerObject) {
        firstName.setText(customerObject.getFirstName());
        genderText.setText(customerObject.getGender());
        email.setText(customerObject.getEmail());
        if (!customerObject.getMobile().isEmpty())
            mobileNumber.setText(customerObject.getMobile());
        dateOfBirth.setText(customerObject.getdOB());
        profileUrl = customerObject.getImageLink();
        profilePic.setController(AppCommon.getDraweeController(profilePic, "http://food-monkey.com" + profileUrl, 200));
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

    @OnClick(R.id.dateOfBirthLayout)
    void setDateOfBirthLayout() {
        new DatePickerDialog(EditAccountActivity.this, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    @OnClick(R.id.cancel)
    void setCancel() {
        popUpLayout.setVisibility(View.GONE);
    }

    @OnClick(R.id.profilePic)
    void setOnProfile() {
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

    @OnClick(R.id.saveButton)
    void setSaveButton() {
        String firstName = this.firstName.getText().toString().trim();
        String genderText = this.genderText.getText().toString().trim();
        String dateOfBirth = this.dateOfBirth.getText().toString().trim();
        String profileInput = "data:image/jpg;base64," + base64Pic;

        if (profileUrl.isEmpty()) {
            profileInput = profileInput.replaceAll("\\s", "");
        }

        callingProfileEdit(AppCommon.getInstance(this).getDeviceToken(), AppCommon.getInstance(this).getCustomerID(), firstName, "", "",
                genderText, "", dateOfBirth, profileInput);
    }

    void callingProfileEdit(String tokenKey, final String customerID, final String firstName, final String middleName, final String surName,
                            final String gender, final String mobile, final String dateOfBirth, String profilePic) {
        AppCommon.getInstance(this).setNonTouchableFlags(this);
        if (AppCommon.getInstance(EditAccountActivity.this).isConnectingToInternet(EditAccountActivity.this)) {
            progressBar.setVisibility(View.VISIBLE);
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

    @OnClick(R.id.manageAddress)
    void setOnManageAddress() {
        Intent intent = new Intent(this, ManagerAddressActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", customerObject);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void requestCameraPermission() {
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(EditAccountActivity.this,
                    new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.CAMERA},
                    200);
        } else {
            startCameraIntent();
        }
    }

    public void requestGalleryPermission() {
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(EditAccountActivity.this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(EditAccountActivity.this,
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

    public void startCameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(Environment.getExternalStorageDirectory(),
                "attachment.jpg");
        outPutfileUri = FileProvider.getUriForFile(EditAccountActivity.this,
                BuildConfig.APPLICATION_ID + ".provider",
                file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outPutfileUri);
        startActivityForResult(intent, REQUEST_CAMERA);

    }

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

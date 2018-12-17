package com.kartik.project.foodmonkey;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;
import com.kartik.project.foodmonkey.API.FoodMonkeyAppService;
import com.kartik.project.foodmonkey.API.ServiceGenerator;
import com.kartik.project.foodmonkey.ApiEntity.CustomerEditAddressEntity;
import com.kartik.project.foodmonkey.ApiObject.AddressObjects;
import com.kartik.project.foodmonkey.ApiResponse.CommonResponse;

import Infrastructure.AppCommon;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditAddressActivity extends AppCompatActivity {

    @BindView(R.id.toolbarText)
    TextView toolbarText;

    @BindView(R.id.left)
    ImageView left;

    @BindView(R.id.name)
    EditText name;

    @BindView(R.id.houseNumber)
    EditText houseNumber;

    @BindView(R.id.streetLine)
    EditText streetLine;

    @BindView(R.id.streetLine2)
    EditText streetLine2;

    @BindView(R.id.pincode)
    EditText pincode;

    @BindView(R.id.city)
    EditText city;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    Call call;
    AddressObjects addressObjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_address);
        ButterKnife.bind(this);
        left.setVisibility(View.VISIBLE);
        toolbarText.setText(getString(R.string.editAddress));

        if (getIntent() != null) {

            addressObjects = (AddressObjects) getIntent().getSerializableExtra("data");
            String name = getIntent().getStringExtra("name");
            setData(name);
        }

    }

    private void setData(String name) {
        this.name.setText(name);
        this.houseNumber.setText(addressObjects.getHouseNo());
        this.streetLine.setText(addressObjects.getStreetLine1());
        this.streetLine2.setText(addressObjects.getStreetLine2());
        this.pincode.setText(addressObjects.getPostCode());
        this.city.setText(addressObjects.getCity());

    }

    @OnClick(R.id.left)
    void setLeft() {
        onBackPressed();
    }

    @OnClick(R.id.doneButton)
    void sedoneButton() {
        if (name.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please Enter name", Toast.LENGTH_SHORT).show();
        } else if (houseNumber.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please Enter House Number", Toast.LENGTH_SHORT).show();
        } else if (streetLine.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please Enter Street Line 1", Toast.LENGTH_SHORT).show();
        } else if (streetLine2.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please Enter Street Line 2", Toast.LENGTH_SHORT).show();
        } else if (pincode.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please Enter postal Code", Toast.LENGTH_SHORT).show();
        } else if (city.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please Enter city", Toast.LENGTH_SHORT).show();
        } else {
            addressObjects.setCity(city.getText().toString().trim());
            addressObjects.setPostCode(pincode.getText().toString().trim());
            addressObjects.setStreetLine1(streetLine.getText().toString().trim());
            addressObjects.setStreetLine2(streetLine2.getText().toString().trim());

            callingEditAddress(AppCommon.getInstance(this).getDeviceToken(), addressObjects.getAddressId(),
                    addressObjects.getAddressName(), addressObjects.getMobileNumber(),
                    Integer.parseInt(houseNumber.getText().toString().trim()), addressObjects.getAddressNote(),
                    streetLine.getText().toString().trim(), streetLine2.getText().toString().trim(),
                    pincode.getText().toString().trim(), city.getText().toString().trim());
        }
    }


    void callingEditAddress(String tokenKey, int addressId, String addressType, long mobileNumber,
                            int houseNo, String addressNote, String streetLine1, String streetLine2,
                            String postCode, String city) {
        AppCommon.getInstance(this).setNonTouchableFlags(this);
        if (AppCommon.getInstance(EditAddressActivity.this).isConnectingToInternet(EditAddressActivity.this)) {
            progressBar.setVisibility(View.VISIBLE);
            CustomerEditAddressEntity custProfileEditEntity = new CustomerEditAddressEntity
                    (tokenKey, addressId, addressType, mobileNumber, houseNo, addressNote, streetLine1, streetLine2, postCode, city);

            FoodMonkeyAppService foodMonkeyAppService = ServiceGenerator.createService(FoodMonkeyAppService.class);
            call = foodMonkeyAppService.CustomerEditAddress(custProfileEditEntity);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(EditAddressActivity.this).clearNonTouchableFlags(EditAddressActivity.this);
                    if (response.code() == 200) {
                        progressBar.setVisibility(View.GONE);
                        CommonResponse commonResponse = (CommonResponse) response.body();
                        if (commonResponse.getCode().equals("200")) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(EditAddressActivity.this);
                            builder.setTitle(commonResponse.getMessage());
                            builder.setNegativeButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                    finish();
                                }
                            });
                            builder.show();
                        } else {
                            AppCommon.showDialog(EditAddressActivity.this, commonResponse.getMessage());
                        }
                    } else {
                        AppCommon.showDialog(EditAddressActivity.this, getString(R.string.serverError));
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    AppCommon.getInstance(EditAddressActivity.this).clearNonTouchableFlags(EditAddressActivity.this);
                    progressBar.setVisibility(View.GONE);
                    if (t instanceof JsonSyntaxException) {
                        AppCommon.showDialog(EditAddressActivity.this, "Something Went wrong ..Please try again.");
                    } else {
                        AppCommon.showDialog(EditAddressActivity.this, getResources().getString(R.string.network_error));
                    }
                }
            });
        } else {
            AppCommon.getInstance(EditAddressActivity.this).clearNonTouchableFlags(EditAddressActivity.this);
            AppCommon.showDialog(this, getResources().getString(R.string.network_error));
        }
    }

}

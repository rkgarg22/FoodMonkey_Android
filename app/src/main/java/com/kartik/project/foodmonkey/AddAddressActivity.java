package com.kartik.project.foodmonkey;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonSyntaxException;
import com.kartik.project.foodmonkey.API.FoodMonkeyAppService;
import com.kartik.project.foodmonkey.API.ServiceGenerator;
import com.kartik.project.foodmonkey.ApiEntity.CustAddAddressEntity;
import com.kartik.project.foodmonkey.ApiResponse.CustomerAddressResponse;

import Infrastructure.AppCommon;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddAddressActivity extends AppCompatActivity {

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.left)
    ImageView left;

    @BindView(R.id.toolbarText)
    TextView toolbarText;

    @BindView(R.id.goToPayment)
    Button goToPayment;

    @BindView(R.id.mobileNumber)
    EditText mobileNumber;

    @BindView(R.id.houseNumber)
    EditText houseNumber;

    @BindView(R.id.streetLine)
    EditText streetLine;

    @BindView(R.id.streetLine2)
    EditText streetLine2;

    @BindView(R.id.postCode)
    EditText postCode;

    @BindView(R.id.city)
    EditText city;

    @BindView(R.id.addressNote)
    EditText addressNote;

    @BindView(R.id.spinner)
    Spinner spinner;

    Call call;

    String[] tabs = {getString(R.string.selectAddressType), getString(R.string.home), getString(R.string.office)};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        ButterKnife.bind(this);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this, R.layout.simple_spinner_item, tabs);
        adapter.setDropDownViewResource(R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
    }

    @OnClick(R.id.goToPayment)
    void setGoToPayment() {
        if (vaidation()) {
            callingAddCustAddress(AppCommon.getInstance(this).getDeviceToken(), AppCommon.getInstance(this).getCustomerID(), spinner.getSelectedItem().toString().trim(),
                    mobileNumber.getText().toString().trim(), houseNumber.getText().toString().trim(),
                    addressNote.getText().toString().trim(), streetLine.getText().toString().trim(), streetLine2.getText().toString().trim(),
                    postCode.getText().toString().trim(), city.getText().toString().trim());
        }
    }

    private boolean vaidation() {
        boolean flag = true;
        String mobileNumber = this.mobileNumber.getText().toString().trim();
        String houseNumber = this.houseNumber.getText().toString().trim();
        String streetLine = this.streetLine.getText().toString().trim();
        String streetLine2 = this.streetLine2.getText().toString().trim();
        String postCode = this.postCode.getText().toString().trim();
        String city = this.city.getText().toString().trim();
        String spinnerText = this.spinner.getSelectedItem().toString().trim();
        String addressNote = this.addressNote.getText().toString().trim();

        if (mobileNumber.isEmpty()) {
            Toast.makeText(this, getString(R.string.pleaseEnterMobile), Toast.LENGTH_SHORT).show();
            flag = false;
        } else if (houseNumber.isEmpty()) {
            flag = false;
            Toast.makeText(this, getString(R.string.pleaseEnterHouseNumber), Toast.LENGTH_SHORT).show();
        } else if (streetLine.isEmpty()) {
            flag = false;
            Toast.makeText(this, getString(R.string.pleaseEnterStreetLine), Toast.LENGTH_SHORT).show();
        } else if (streetLine2.isEmpty()) {
            flag = false;
            Toast.makeText(this, getString(R.string.pleaseEnterStreetLine2), Toast.LENGTH_SHORT).show();
        } else if (postCode.isEmpty()) {
            flag = false;
            Toast.makeText(this, getString(R.string.pleaseEnterPostCode), Toast.LENGTH_SHORT).show();
        } else if (city.isEmpty()) {
            flag = false;
            Toast.makeText(this, getString(R.string.pleaseEnterCity), Toast.LENGTH_SHORT).show();
        } else if (city.isEmpty()) {
            flag = false;
            Toast.makeText(this, getString(R.string.pleaseEnterCity), Toast.LENGTH_SHORT).show();
        } else if (spinnerText.equals(getString(R.string.selectAddressType))) {
            flag = false;
            Toast.makeText(this, getString(R.string.pleaseSelectAddressType), Toast.LENGTH_SHORT).show();
        } else if (addressNote.isEmpty()) {
            flag = false;
            Toast.makeText(this, getString(R.string.pleaseSelectAddressNote), Toast.LENGTH_SHORT).show();
        }

        return flag;

    }

    void callingAddCustAddress(String tokenKey, String customerId, String addressType, String mobileNumber, String houseNo,
                               String addressNote, String streetLine1, String streetLine2, String postCode, String city) {
        AppCommon.getInstance(this).setNonTouchableFlags(this);
        if (AppCommon.getInstance(AddAddressActivity.this).isConnectingToInternet(AddAddressActivity.this)) {
            progressBar.setVisibility(View.VISIBLE);
            //  final String token = myFirebaseInstanceIDService.getDeviceToken();
            CustAddAddressEntity customerHomeEntity = new CustAddAddressEntity(tokenKey, customerId, addressType, mobileNumber, houseNo, addressNote,
                    streetLine1, streetLine2, postCode, city);
            FoodMonkeyAppService foodMonkeyAppService = ServiceGenerator.createService(FoodMonkeyAppService.class);
            call = foodMonkeyAppService.AddCustomerNewAddress(customerHomeEntity);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(AddAddressActivity.this).clearNonTouchableFlags(AddAddressActivity.this);
                    if (response.code() == 200) {
                        progressBar.setVisibility(View.GONE);
                        CustomerAddressResponse customerHomeResponse = (CustomerAddressResponse) response.body();
                        if (response.body() != null) {
                            if (customerHomeResponse.getCode().equals("200")) {
                            } else {
                                AppCommon.showDialog(AddAddressActivity.this, customerHomeResponse.getMessage());
                            }
                        }
                    } else {
                        AppCommon.showDialog(AddAddressActivity.this, getString(R.string.serverError));
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    AppCommon.getInstance(AddAddressActivity.this).clearNonTouchableFlags(AddAddressActivity.this);
                    progressBar.setVisibility(View.GONE);
                    if (t instanceof JsonSyntaxException) {
                        AppCommon.showDialog(AddAddressActivity.this, "No resturant found");
                    } else {
                        AppCommon.showDialog(AddAddressActivity.this, getResources().getString(R.string.network_error));

                    }
                }
            });
        } else {
            AppCommon.getInstance(AddAddressActivity.this).clearNonTouchableFlags(AddAddressActivity.this);
            AppCommon.showDialog(this, getResources().getString(R.string.network_error));
        }
    }


}

package com.kartik.project.foodmonkey;

import android.content.Intent;
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
import com.kartik.project.foodmonkey.ApiEntity.AddToCardEntity;
import com.kartik.project.foodmonkey.ApiResponse.AddToCardResponse;

import java.util.ArrayList;

import Infrastructure.AppCommon;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddNewCardActivity extends AppCompatActivity {

    @BindView(R.id.left)
    ImageView left;

    @BindView(R.id.toolbarText)
    TextView toolbarText;

    @BindView(R.id.addToCartBtn)
    Button addToCartBtn;

    @BindView(R.id.nameOnCard)
    EditText nameOnCard;

    @BindView(R.id.cardNumber)
    EditText cardNumber;

    @BindView(R.id.cvvEditText)
    EditText cvvEditText;

    @BindView(R.id.dateSpinner)
    Spinner monthSpinner;

    @BindView(R.id.yearSpinner)
    Spinner yearSpinner;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    ArrayList<String> monthList = new ArrayList<>();
    ArrayList<String> yearList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_card);
        ButterKnife.bind(this);
        left.setVisibility(View.VISIBLE);
        toolbarText.setText(getString(R.string.addNewCreditAndDebitCard));

        monthList.clear();
        for (int i = 1; i <= 30; i++) {
            monthList.add("" + i);
        }
        for (int i = 1; i <= 15; i++) {
            yearList.add("" + (2017 + i));
        }
        ArrayAdapter<String> monthAdapter = new ArrayAdapter<String>(this,
                R.layout.simple_spinner_item, monthList);
        ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(this,
                R.layout.simple_spinner_item, yearList);
        monthSpinner.setAdapter(monthAdapter);
        yearSpinner.setAdapter(yearAdapter);

    }

    @OnClick(R.id.left)
    void setLeft() {
        onBackPressed();
    }

    @OnClick(R.id.addToCartBtn)
    void setAddToCartBtn() {
        if (validation()) {
            callingAddCard(AppCommon.getInstance(this).getDeviceToken(), Integer.parseInt(AppCommon.getInstance(this).getCustomerID()),
                    nameOnCard.getText().toString().trim(), Long.valueOf(cardNumber.getText().toString().trim()),
                    Integer.valueOf(monthSpinner.getSelectedItem().toString()), Integer.valueOf(yearSpinner.getSelectedItem().toString()),
                    Integer.valueOf(cvvEditText.getText().toString().trim()));
        }
    }

    Call call;

    private void callingAddCard(String tokenKey, int customerID, String nameOnCard, long cardNumber,
                                int expDate, int expYear, int cvv) {
        AppCommon.getInstance(this).setNonTouchableFlags(this);
        if (AppCommon.getInstance(AddNewCardActivity.this).isConnectingToInternet(AddNewCardActivity.this)) {
            progressBar.setVisibility(View.VISIBLE);
            //  final String token = myFirebaseInstanceIDService.getDeviceToken();
            final AddToCardEntity customerHomeEntity = new AddToCardEntity(tokenKey, customerID, nameOnCard, cardNumber,
                    expDate, expYear, cvv);
            FoodMonkeyAppService foodMonkeyAppService = ServiceGenerator.createService(FoodMonkeyAppService.class);
            call = foodMonkeyAppService.AddToCard(customerHomeEntity);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(AddNewCardActivity.this).clearNonTouchableFlags(AddNewCardActivity.this);
                    if (response.code() == 200) {
                        progressBar.setVisibility(View.GONE);
                        if (response.body() != null) {
                            AddToCardResponse addToCardResponse = (AddToCardResponse) response.body();
                            if (addToCardResponse.getCode().equals("200")) {
                                Toast.makeText(AddNewCardActivity.this, addToCardResponse.getMessage(),
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent();
                                setResult(RESULT_OK,intent);
                                finish();
                            } else {
                                AppCommon.showDialog(AddNewCardActivity.this, addToCardResponse.getMessage());
                            }
                        }
                    } else {
                        AppCommon.showDialog(AddNewCardActivity.this, getString(R.string.serverError));
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    AppCommon.getInstance(AddNewCardActivity.this).clearNonTouchableFlags(AddNewCardActivity.this);
                    progressBar.setVisibility(View.GONE);
                    if (t instanceof JsonSyntaxException) {
                        AppCommon.showDialog(AddNewCardActivity.this, "Something went wrong please try Again");
                    } else {
                        AppCommon.showDialog(AddNewCardActivity.this, getResources().getString(R.string.network_error));

                    }
                }
            });
        } else {
            AppCommon.getInstance(AddNewCardActivity.this).clearNonTouchableFlags(AddNewCardActivity.this);
            AppCommon.showDialog(this, getResources().getString(R.string.network_error));
        }
    }

    private boolean validation() {
        boolean flag = true;
        String nameOnCard = this.nameOnCard.getText().toString().trim();
        String cardNumber = this.cardNumber.getText().toString().trim();
        String cvvEditText = this.cvvEditText.getText().toString().trim();
        String selectedMonth = monthSpinner.getSelectedItem().toString();
        String selectedyear = yearSpinner.getSelectedItem().toString();

        if (nameOnCard.isEmpty()) {
            Toast.makeText(this, getString(R.string.pleaseEnterCardName), Toast.LENGTH_SHORT).show();
            flag = false;
        } else if (cardNumber.isEmpty() || cardNumber.length() < 16) {
            Toast.makeText(this, getString(R.string.pleaseEnterValidCardNumber), Toast.LENGTH_SHORT).show();
            flag = false;
        } else if (cvvEditText.isEmpty()) {
            Toast.makeText(this, getString(R.string.pleaseEnterCVV), Toast.LENGTH_SHORT).show();
            flag = false;
        } else if (selectedMonth.isEmpty()) {
            Toast.makeText(this, getString(R.string.pleaseEnterSelectedMonth), Toast.LENGTH_SHORT).show();
            flag = false;
        } else if (selectedyear.isEmpty()) {
            Toast.makeText(this, getString(R.string.pleaseEnterSelectedYear), Toast.LENGTH_SHORT).show();
            flag = false;
        }

        return flag;
    }
}

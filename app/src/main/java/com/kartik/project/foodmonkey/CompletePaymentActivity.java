package com.kartik.project.foodmonkey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonSyntaxException;
import com.kartik.project.foodmonkey.API.FoodMonkeyAppService;
import com.kartik.project.foodmonkey.API.ServiceGenerator;
import com.kartik.project.foodmonkey.Adapters.AddCardAdapter;
import com.kartik.project.foodmonkey.ApiEntity.CardListingEntity;
import com.kartik.project.foodmonkey.ApiEntity.CustAddAddressEntity;
import com.kartik.project.foodmonkey.ApiObject.CardListObject;
import com.kartik.project.foodmonkey.ApiResponse.CardListingResponse;

import Infrastructure.AppCommon;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompletePaymentActivity extends AppCompatActivity {

    @BindView(R.id.placeOrderRecyclerView)
    RecyclerView placeOrderRecyclerView;

    @BindView(R.id.left)
    ImageView left;

    @BindView(R.id.toolbarText)
    TextView toolbarText;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.parentSelectedCardLayout)
    RelativeLayout parentSelectedCardLayout;

    @BindView(R.id.cardNumberText)
    TextView cardNumberText;

    @BindView(R.id.payPalCheckBox)
    ImageView payPalCheckBox;

    @BindView(R.id.cvvNumberText)
    TextView cvvNumberText;

    @BindView(R.id.codCheckBox)
    ImageView codCheckBox;

    @BindView(R.id.cardCheckBox)
    ImageView cardCheckBox;

    AddCardAdapter addCardAdapter;

    String orderID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_payment);
        ButterKnife.bind(this);
        left.setVisibility(View.VISIBLE);
        toolbarText.setText(getString(R.string.completeYourPayment));
        if (getIntent() != null) {
            orderID = getIntent().getStringExtra("orderID");
        }

        placeOrderRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        placeOrderRecyclerView.setNestedScrollingEnabled(false);
        callingCardListing();

    }

    @OnClick(R.id.left)
    void setLeft() {
        onBackPressed();
    }

    @OnClick(R.id.placeMyOrderBtn)
    void setPlaceMyOrderBtn() {
        Toast.makeText(this, "Currently Under Development ", Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.addNewCard)
    void setAddNewCard() {
        Intent intent = new Intent(this, AddNewCardActivity.class);
        startActivityForResult(intent, 2000);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK)
            switch (requestCode) {
                case 2000:
                    // call here refresh listing of card
                    callingCardListing();
                    break;
            }
    }

    Call call;

    private void callingCardListing() {
        AppCommon.getInstance(CompletePaymentActivity.this).setNonTouchableFlags(CompletePaymentActivity.this);
        if (AppCommon.getInstance(CompletePaymentActivity.this).isConnectingToInternet(CompletePaymentActivity.this)) {
            progressBar.setVisibility(View.VISIBLE);
            //  final String token = myFirebaseInstanceIDService.getDeviceToken();
            CardListingEntity cardListingEntity = new CardListingEntity(AppCommon.getInstance(this).getDeviceToken(),
                    AppCommon.getInstance(this).getCustomerID());
            FoodMonkeyAppService foodMonkeyAppService = ServiceGenerator.createService(FoodMonkeyAppService.class);
            call = foodMonkeyAppService.CardListing(cardListingEntity);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(CompletePaymentActivity.this).clearNonTouchableFlags(CompletePaymentActivity.this);
                    if (response.code() == 200) {
                        progressBar.setVisibility(View.GONE);
                        CardListingResponse cardListingResponse = (CardListingResponse) response.body();
                        if (response.body() != null) {
                            if (cardListingResponse.getCode().equals("200")) {
                                addCardAdapter = new AddCardAdapter(CompletePaymentActivity.this, cardListingResponse.getCardList());
                                placeOrderRecyclerView.setAdapter(addCardAdapter);
//                                Toast.makeText(CompletePaymentActivity.this, "Order Added Successfully", Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(CompletePaymentActivity.this, CompletePaymentActivity.class);
//                                intent.putExtra("orderID", orderCheckOutResponse.getOrderId());
//                                startActivity(intent);
//                                setAdapter(restDetailMenuResponse.getResturantFeedbackList());

                            } else {
                                AppCommon.showDialog(CompletePaymentActivity.this, cardListingResponse.getMessage());
                            }
                        }
                    } else {
                        AppCommon.showDialog(CompletePaymentActivity.this, getString(R.string.serverError));
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    AppCommon.getInstance(CompletePaymentActivity.this).clearNonTouchableFlags(CompletePaymentActivity.this);
                    progressBar.setVisibility(View.GONE);
                    if (t instanceof JsonSyntaxException) {
                        AppCommon.showDialog(CompletePaymentActivity.this, "No Rating found");
                    } else {
                        AppCommon.showDialog(CompletePaymentActivity.this, getResources().getString(R.string.network_error));

                    }
                }
            });
        } else {
            AppCommon.getInstance(CompletePaymentActivity.this).clearNonTouchableFlags(CompletePaymentActivity.this);
            AppCommon.showDialog(CompletePaymentActivity.this, getResources().getString(R.string.network_error));
        }
    }

    public void setCardValues(CardListObject cardListObject) {
        parentSelectedCardLayout.setVisibility(View.VISIBLE);
        cardCheckBox.setImageResource(R.drawable.checkbox_click);
        payPalCheckBox.setImageResource(R.drawable.check_box);
        codCheckBox.setImageResource(R.drawable.check_box);
        cardNumberText.setText("****-****-****-" + String.valueOf(cardListObject.getCardNumber()).substring(11, 15));
        cvvNumberText.setText(""+cardListObject.getCVV());
    }

    @OnClick(R.id.payPalLayout)
    void setPayPalLayout()
    {
        payPalCheckBox.setImageResource(R.drawable.checkbox_click);
        codCheckBox.setImageResource(R.drawable.check_box);
        cardCheckBox.setImageResource(R.drawable.check_box);
    }
    @OnClick(R.id.codLayout)
    void setCodLayout()
    {
        payPalCheckBox.setImageResource(R.drawable.check_box);
        codCheckBox.setImageResource(R.drawable.checkbox_click);
        cardCheckBox.setImageResource(R.drawable.check_box);
    }
}

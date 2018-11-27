package com.kartik.project.foodmonkey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.braintreepayments.api.BraintreeFragment;
import com.braintreepayments.api.dropin.DropInActivity;
import com.braintreepayments.api.dropin.DropInRequest;
import com.braintreepayments.api.dropin.DropInResult;
import com.braintreepayments.api.exceptions.InvalidArgumentException;
import com.braintreepayments.api.models.PaymentMethodNonce;
import com.google.gson.JsonSyntaxException;
import com.kartik.project.foodmonkey.API.FoodMonkeyAppService;
import com.kartik.project.foodmonkey.API.ServiceGenerator;
import com.kartik.project.foodmonkey.Adapters.AddCardAdapter;
import com.kartik.project.foodmonkey.ApiEntity.AddTokenEntity;
import com.kartik.project.foodmonkey.ApiEntity.BrainTreeEntity;
import com.kartik.project.foodmonkey.ApiEntity.CardListingEntity;
import com.kartik.project.foodmonkey.ApiEntity.CustAddAddressEntity;
import com.kartik.project.foodmonkey.ApiObject.CardListObject;
import com.kartik.project.foodmonkey.ApiObject.StripeDataObject;
import com.kartik.project.foodmonkey.ApiResponse.AddPaymentsEntity;
import com.kartik.project.foodmonkey.ApiResponse.AddToCardResponse;
import com.kartik.project.foodmonkey.ApiResponse.BrainTreePaymentResponse;
import com.kartik.project.foodmonkey.ApiResponse.CardListingResponse;
import com.kartik.project.foodmonkey.ApiResponse.GetBrainTreeResponse;
import com.kartik.project.foodmonkey.ApiResponse.PaymentMethodResponse;
import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Customer;
import com.stripe.model.CustomerCardCollection;

import java.util.HashMap;
import java.util.Map;

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
    /*--------------Brain TreeStuff----------*/
    final int REQUEST_CODE = 1000;
    boolean payPalFlag = false;
    boolean COD = false;
    String token, amount;
    HashMap<String, String> paramsHash = new HashMap<>();

    Call call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_payment);
        ButterKnife.bind(this);
        left.setVisibility(View.VISIBLE);
        toolbarText.setText(getString(R.string.completeYourPayment));
        if (getIntent() != null) {
            orderID = getIntent().getStringExtra("orderID");
            amount = String.valueOf(getIntent().getFloatExtra("totalAmount", 0));
        }

        placeOrderRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        placeOrderRecyclerView.setNestedScrollingEnabled(false);
//        fetchCardList();
        callingCardListing();

    }

    @OnClick(R.id.left)
    void setLeft() {
        onBackPressed();
    }

    @OnClick(R.id.placeMyOrderBtn)
    void setPlaceMyOrderBtn() {
        String paymentMethod;
        if (payPalFlag) {
            paymentMethod = "Paypal";
//            callingBrainTreeToken();
        } else if (COD) {
            paymentMethod = "Cash";
        } else {
            paymentMethod = "Card";
        }
        callingAddMethodAPI(orderID, paymentMethod);
//        Toast.makeText(this, "Currently Under Development ", Toast.LENGTH_LONG).show();
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
//                    callingCardListing();
                    break;
                case REQUEST_CODE:
                    DropInResult result = data.getParcelableExtra(DropInResult.EXTRA_DROP_IN_RESULT);
                    PaymentMethodNonce nonce = result.getPaymentMethodNonce();
                    String strNonce = nonce.getNonce();

                    if (amount != null) {
                        callingBrainPaymentAPI(strNonce, amount, orderID, AppCommon.getInstance(this).getFirstName(),
                                AppCommon.getInstance(this).getSurName(), AppCommon.getInstance(this).getEmailAddress());
//                        sendPayments();// call BraintreePayment here----------
                    } else {
                        Toast.makeText(this, "Please enter valid amount!", Toast.LENGTH_SHORT).show();
                    }

                    break;
            }

        if (REQUEST_CODE == REQUEST_CODE) {
            if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "User Canceled Payment", Toast.LENGTH_SHORT).show();
            } else {
                Exception exception = (Exception) data.getSerializableExtra(DropInActivity.EXTRA_ERROR);
                Log.d("error_Exception-->", "" + exception);
            }
        }
    }

    private void callingAddMethodAPI(String orderId, String paymentMethod) {
        AppCommon.getInstance(CompletePaymentActivity.this).setNonTouchableFlags(CompletePaymentActivity.this);
        if (AppCommon.getInstance(CompletePaymentActivity.this).isConnectingToInternet(CompletePaymentActivity.this)) {
            progressBar.setVisibility(View.VISIBLE);
            FoodMonkeyAppService foodMonkeyAppService = ServiceGenerator.createService(FoodMonkeyAppService.class);
            call = foodMonkeyAppService.AddPaymentMethods(new AddPaymentsEntity(
                    AppCommon.getInstance(CompletePaymentActivity.this).getDeviceToken(), orderId,
                    AppCommon.getInstance(this).getStripeCustID(), paymentMethod));
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(CompletePaymentActivity.this).clearNonTouchableFlags(CompletePaymentActivity.this);
                    if (response.code() == 200) {
                        progressBar.setVisibility(View.GONE);
                        PaymentMethodResponse paymentMethodResponse = (PaymentMethodResponse) response.body();
                        if (paymentMethodResponse != null) {
                            if (paymentMethodResponse.getCode().equals("200")) {
                                orderID = paymentMethodResponse.getOrderId();
                                if (payPalFlag) {
//                                    paymentMethod = "Paypal";
                                    callingBrainTreeToken();
                                } else if (COD) {
//                                    paymentMethod = "Cash";
                                } else {
//                                    paymentMethod = "Card";
                                    // add Stripe payment via card here-----------------
                                }
//                                submitPaymentPaypal();
                            } else {
                                AppCommon.showDialog(CompletePaymentActivity.this, paymentMethodResponse.getMessage());
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


   /*   --------------------Calling BrainTreeMethods-----------------   */

    private void callingBrainTreeToken() {
        AppCommon.getInstance(CompletePaymentActivity.this).setNonTouchableFlags(CompletePaymentActivity.this);
        if (AppCommon.getInstance(CompletePaymentActivity.this).isConnectingToInternet(CompletePaymentActivity.this)) {
            progressBar.setVisibility(View.VISIBLE);
            FoodMonkeyAppService foodMonkeyAppService = ServiceGenerator.createService(FoodMonkeyAppService.class);
            call = foodMonkeyAppService.GetBrainTreeToken(
                    new AddTokenEntity(AppCommon.getInstance(CompletePaymentActivity.this).getDeviceToken()));
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(CompletePaymentActivity.this).clearNonTouchableFlags(CompletePaymentActivity.this);
                    if (response.code() == 200) {
                        progressBar.setVisibility(View.GONE);
                        GetBrainTreeResponse brainTreeResponse = (GetBrainTreeResponse) response.body();
                        if (response.body() != null) {
                            if (brainTreeResponse.getCode().equals("200")) {
                                AppCommon.getInstance(CompletePaymentActivity.this).setBrainTreeToken(brainTreeResponse.getBraintreeToken());
                                submitPaymentPaypal();
                            } else {
                                AppCommon.showDialog(CompletePaymentActivity.this, brainTreeResponse.getMessage());
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

    private void callingBrainPaymentAPI(String paymentNonce, String amount, String orderID,
                                        String customerName, String customerLastName, String customerEmail) {
        AppCommon.getInstance(CompletePaymentActivity.this).setNonTouchableFlags(CompletePaymentActivity.this);
        if (AppCommon.getInstance(CompletePaymentActivity.this).isConnectingToInternet(CompletePaymentActivity.this)) {
            progressBar.setVisibility(View.VISIBLE);
            FoodMonkeyAppService foodMonkeyAppService = ServiceGenerator.createService(FoodMonkeyAppService.class);
            BrainTreeEntity brainTreeEntity = new BrainTreeEntity(AppCommon.getInstance(this).getDeviceToken(), paymentNonce, amount
                    , orderID, customerName, customerLastName, customerEmail);
            call = foodMonkeyAppService.GetBrainTreePayment(brainTreeEntity);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(CompletePaymentActivity.this).clearNonTouchableFlags(CompletePaymentActivity.this);
                    if (response.code() == 200) {
                        progressBar.setVisibility(View.GONE);
                        BrainTreePaymentResponse paymentResponse = (BrainTreePaymentResponse) response.body();
                        if (response.body() != null) {
//                            if (!paymentResponse.getCode().equals("400")) {
                            try {
                                AppCommon.getInstance(CompletePaymentActivity.this).setBrainTreeToken(paymentResponse.getResultOutput());

                            } catch (NullPointerException e) {
                                e.printStackTrace();
                            }
//                            } else {
//                                AppCommon.showDialog(CompletePaymentActivity.this, paymentResponse.getMessage());
//                            }
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

    private void submitPaymentPaypal() {
        DropInRequest dropInRequest = new DropInRequest().clientToken("sandbox_q26yd5r3_k2p3y9xmmm3hvhpb");
        startActivityForResult(dropInRequest.getIntent(this), REQUEST_CODE);
    }

    /*   ------------------- Fetching list of Card from Stripe -   ----- */

//    void fetchCardList() {
//        Stripe.apiKey = getString(R.string.stripeSecKey);
////        final Map<String, Object> cardParams = new HashMap<String, Object>();
////        cardParams.put("limit", 3);
////        cardParams.put("object", "card");
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Map<String, Object> cardParams = new HashMap<String, Object>();
//                    cardParams.put("limit", 3);
//                    cardParams.put("object", "card");
//                    Customer.retrieve(AppCommon.getInstance(CompletePaymentActivity.this).getStripeCustID()).getSources().all(cardParams);
//                } catch (AuthenticationException e) {
//                    e.printStackTrace();
//                } catch (InvalidRequestException e) {
//                    e.printStackTrace();
//                } catch (APIConnectionException e) {
//                    e.printStackTrace();
//                } catch (CardException e) {
//                    e.printStackTrace();
//                } catch (APIException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//    }


    private void callingCardListing() {
        AppCommon.getInstance(CompletePaymentActivity.this).setNonTouchableFlags(CompletePaymentActivity.this);
        if (AppCommon.getInstance(CompletePaymentActivity.this).isConnectingToInternet(CompletePaymentActivity.this)) {
            progressBar.setVisibility(View.VISIBLE);
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
                        AddToCardResponse addToCardResponse = (AddToCardResponse) response.body();
                        if (addToCardResponse != null) {
                            if (addToCardResponse.getMessage().getData()!=null) {

                                addCardAdapter = new AddCardAdapter(CompletePaymentActivity.this, addToCardResponse.getMessage().getData());
                                placeOrderRecyclerView.setAdapter(addCardAdapter);
//                                Toast.makeText(CompletePaymentActivity.this, "Order Added Successfully", Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(CompletePaymentActivity.this, CompletePaymentActivity.class);
//                                intent.putExtra("orderID", orderCheckOutResponse.getOrderId());
//                                startActivity(intent);
//                                setAdapter(restDetailMenuResponse.getResturantFeedbackList());

                            } else {
                                AppCommon.showDialog(CompletePaymentActivity.this, "something went wrong, try again!!");
                            }
                        }
                    } else {
                        progressBar.setVisibility(View.GONE);
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

    public void setCardValues(StripeDataObject cardListObject) {
        parentSelectedCardLayout.setVisibility(View.VISIBLE);
        cardCheckBox.setImageResource(R.drawable.checkbox_click);
        payPalCheckBox.setImageResource(R.drawable.check_box);
        codCheckBox.setImageResource(R.drawable.check_box);
        cardNumberText.setText(cardListObject.getBrand() + "****" + cardListObject.getLast4() + " exp"
                + cardListObject.getExpMonth() + "/" +cardListObject.getExpYear());
//        cvvNumberText.setText("" + cardListObject.getCVV());
    }

    @OnClick(R.id.payPalLayout)
    void setPayPalLayout() {
        payPalCheckBox.setImageResource(R.drawable.checkbox_click);
        codCheckBox.setImageResource(R.drawable.check_box);
        cardCheckBox.setImageResource(R.drawable.check_box);
        payPalFlag = true;
        COD = false;
    }

    @OnClick(R.id.codLayout)
    void setCodLayout() {
        payPalCheckBox.setImageResource(R.drawable.check_box);
        codCheckBox.setImageResource(R.drawable.checkbox_click);
        cardCheckBox.setImageResource(R.drawable.check_box);
        payPalFlag = false;
        COD = true;
    }
}

package com.app.foodMonkey;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.app.foodMonkey.API.FoodMonkeyAppService;
import com.app.foodMonkey.API.ServiceGenerator;
import com.app.foodMonkey.ApiEntity.OrderCheckOutEntity;
import com.app.foodMonkey.ApiResponse.OrderCheckOutResponse;
import com.google.gson.JsonSyntaxException;

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

public class DeliveryTimeActivity extends AppCompatActivity {
    @BindView(R.id.left)
    ImageView left;

    @BindView(R.id.toolbarText)
    TextView toolbarText;

    @BindView(R.id.dateForOrder)
    TextView dateForOrder;

    @BindView(R.id.deliverTimeText)
    TextView deliverTimeText;

    @BindView(R.id.noteForRest)
    EditText noteForRest;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    String itemID = "", quanities = "", addOnIDs = "", deliveryOption = "";
    int restID = 0;
    float totalAmount;
    int addressID = -1;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_time);
        ButterKnife.bind(this);
        left.setVisibility(View.VISIBLE);
        toolbarText.setText(getString(R.string.deliveryTime));
        if (getIntent() != null) {
            restID = getIntent().getIntExtra("restID", -1);
            addressID = getIntent().getIntExtra("addressID", -1);
            totalAmount = getIntent().getFloatExtra("totalAmount", 0);
            deliveryOption = getIntent().getStringExtra("deliveryOption");
            itemID = getIntent().getStringExtra("itemID");
            quanities = getIntent().getStringExtra("quanities");
            addOnIDs = getIntent().getStringExtra("addOnIDs");
        }
    }

    private void updateLabel() {
        String myFormat = "dd MMM, yyyy EEEE"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        dateForOrder.setText(sdf.format(myCalendar.getTime()));
    }

    @OnClick(R.id.left)
    void setLeft() {
        onBackPressed();
    }

    @OnClick(R.id.dateForOrder)
    void setDateForOrder() {
        new DatePickerDialog(this, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    @OnClick(R.id.deliverTimeText)
    void setDeliverTimeText() {
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                deliverTimeText.setText(selectedHour + ":" + selectedMinute);
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }

    String dateAndTime;

    @OnClick(R.id.payButton)
    void setPayButton() {
        if (deliverTimeText.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please select time for delivery", Toast.LENGTH_SHORT).show();
        } else if (dateForOrder.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please select date", Toast.LENGTH_SHORT).show();
        } else
            dateAndTime = deliverTimeText.getText().toString() + " " + dateForOrder.getText().toString();
        callingOrderCheckOut(AppCommon.getInstance(DeliveryTimeActivity.this).getDeviceToken(),
                Integer.parseInt(AppCommon.getInstance(DeliveryTimeActivity.this).getCustomerID()),
                restID, addressID, totalAmount,
                deliveryOption, itemID, quanities, addOnIDs, "", dateAndTime, noteForRest.getText().toString());
    }

    Call call;

    private void callingOrderCheckOut(String tokenKey, Integer customerId, Integer resturantId, Integer addressId, final float orderAmount,
                                      String deliveryOption, String itemId, String quantity, String addonId,
                                      String couponCode, String preOrderDateTime, String preOrderComments) {
        AppCommon.getInstance(DeliveryTimeActivity.this).setNonTouchableFlags(DeliveryTimeActivity.this);
        if (AppCommon.getInstance(DeliveryTimeActivity.this).isConnectingToInternet(DeliveryTimeActivity.this)) {
            progressBar.setVisibility(View.VISIBLE);
            //  final String token = myFirebaseInstanceIDService.getDeviceToken();
            OrderCheckOutEntity orderCheckOutEntity = new OrderCheckOutEntity(tokenKey, customerId, resturantId, addressId, orderAmount,
                    deliveryOption, itemId, quantity, addonId, couponCode, preOrderDateTime, preOrderComments);
            FoodMonkeyAppService foodMonkeyAppService = ServiceGenerator.createService(FoodMonkeyAppService.class);
            call = foodMonkeyAppService.OrderCheckOut(orderCheckOutEntity);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(DeliveryTimeActivity.this).clearNonTouchableFlags(DeliveryTimeActivity.this);
                    if (response.code() == 200) {
                        progressBar.setVisibility(View.GONE);
                        OrderCheckOutResponse orderCheckOutResponse = (OrderCheckOutResponse) response.body();
                        if (response.body() != null) {
                            if (orderCheckOutResponse.getCode().equals("200")) {
                                Toast.makeText(DeliveryTimeActivity.this, "Order Added Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(DeliveryTimeActivity.this, CompletePaymentActivity.class);
                                intent.putExtra("orderID", orderCheckOutResponse.getOrderId());
                                intent.putExtra("totalAmount", orderAmount);
                                startActivity(intent);
//                                setAdapter(restDetailMenuResponse.getResturantFeedbackList());

                            } else {
                                AppCommon.showDialog(DeliveryTimeActivity.this, orderCheckOutResponse.getMessage());
                            }
                        }
                    } else {
                        AppCommon.showDialog(DeliveryTimeActivity.this, getString(R.string.serverError));
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    AppCommon.getInstance(DeliveryTimeActivity.this).clearNonTouchableFlags(DeliveryTimeActivity.this);
                    progressBar.setVisibility(View.GONE);
                    if (t instanceof JsonSyntaxException) {
                        AppCommon.showDialog(DeliveryTimeActivity.this, "No Rating found");
                    } else {
                        AppCommon.showDialog(DeliveryTimeActivity.this, getResources().getString(R.string.network_error));

                    }
                }
            });
        } else {
            AppCommon.getInstance(DeliveryTimeActivity.this).clearNonTouchableFlags(DeliveryTimeActivity.this);
            AppCommon.showDialog(DeliveryTimeActivity.this, getResources().getString(R.string.network_error));
        }
    }


}

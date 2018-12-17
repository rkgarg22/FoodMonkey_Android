package com.kartik.project.foodmonkey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonSyntaxException;
import com.kartik.project.foodmonkey.API.FoodMonkeyAppService;
import com.kartik.project.foodmonkey.API.ServiceGenerator;
import com.kartik.project.foodmonkey.Adapters.AddressAdapter;
import com.kartik.project.foodmonkey.Adapters.OrderListAdapter;
import com.kartik.project.foodmonkey.ApiEntity.CustAddAddressEntity;
import com.kartik.project.foodmonkey.ApiEntity.OrderCheckOutEntity;
import com.kartik.project.foodmonkey.ApiObject.CustomerAddressObject;
import com.kartik.project.foodmonkey.ApiResponse.CustomerAddressResponse;
import com.kartik.project.foodmonkey.ApiResponse.ListCustAddResponse;
import com.kartik.project.foodmonkey.ApiResponse.OrderCheckOutResponse;
import com.kartik.project.foodmonkey.Models.AddAddressModel;
import com.kartik.project.foodmonkey.Models.AddItemsToCartModel;
import com.kartik.project.foodmonkey.Models.InfoModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Infrastructure.AppCommon;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckOutActivity extends AppCompatActivity {

    @BindView(R.id.left)
    ImageView left;

    @BindView(R.id.toolbarText)
    TextView toolbarText;

    @BindView(R.id.orderListRecyclerView)
    RecyclerView orderListRecyclerView;

    @BindView(R.id.chooseDeliveryRecyclerView)
    RecyclerView chooseDeliveryRecyclerView;

    @BindView(R.id.loginLayout)
    RelativeLayout loginLayout;

    @BindView(R.id.chooseDeliveryTypeLayout)
    RelativeLayout chooseDeliveryTypeLayout;

    @BindView(R.id.subTotalText)
    TextView subTotalText;

    @BindView(R.id.totalBill)
    TextView totalBill;

    @BindView(R.id.deliveryCharges)
    TextView deliveryCharges;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.addNewAddress)
    TextView addNewAddress;

    @BindView(R.id.editOrder)
    TextView editOrder;

    @BindView(R.id.deliveryOneHr)
    RadioButton deliveryOneHr;

    @BindView(R.id.bestMatchedRadio)
    RadioButton bestMatchedRadio;

    OrderListAdapter orderListAdapter;

    AddressAdapter addressAdapter;

    ArrayList<InfoModel> infoOfOrder = new ArrayList<>();
    ArrayList<AddAddressModel> addAddressModelArrayList = new ArrayList<>();

    public final int LOGIN_CODE = 1000;
    public final int UPDATE_IN_VALUE_CODE = 2000;
    public final int ADD_NEW_ADDRESS_CODE = 3000;

    List<AddItemsToCartModel> addItemsToCartModelArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        ButterKnife.bind(this);
        left.setVisibility(View.VISIBLE);
        toolbarText.setText(getString(R.string.checkOut));
        deliveryOneHr.setChecked(true);

//        toolbarText.setGravity(Gravity.START | Gravity.CENTER_VERTICAL);

        chooseDeliveryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        chooseDeliveryRecyclerView.setNestedScrollingEnabled(false);

        orderListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        orderListRecyclerView.setNestedScrollingEnabled(false);

        setOrderListData();
//        orderListAdapter = new OrderListAdapter(CheckOutActivity.this, infoOfOrder);
//        orderListRecyclerView.setAdapter(orderListAdapter);

        if (addItemsToCartModelArrayList.size() == 0) {
            editOrder.setVisibility(View.GONE);
        } else {
            editOrder.setVisibility(View.VISIBLE);
        }

        if (AppCommon.getInstance(this).isUserLogIn()) {
            setLayoutVisiblity(true);
        } else {
//            setLayoutVisiblity(false);
        }

    }

    void setOrderListAdapter(ArrayList<InfoModel> infoOfOrder) {
        orderListAdapter = new OrderListAdapter(CheckOutActivity.this, infoOfOrder);
        orderListRecyclerView.setAdapter(orderListAdapter);
    }

    @OnClick(R.id.left)
    void setLeft() {
        onBackPressed();
    }

    @OnClick(R.id.editOrder)
    void setEditOrder() {
        Intent intent = new Intent(CheckOutActivity.this, EditCartActivity.class);
        intent.putExtra("cardArray", (Serializable) addItemsToCartModelArrayList);
        startActivityForResult(intent, UPDATE_IN_VALUE_CODE);
    }

    @OnClick(R.id.addNewAddress)
    void setAddNewAddress() {
        Intent intent = new Intent(CheckOutActivity.this, AddAddressActivity.class);
        intent.putExtra("comingFrom","checkOutPage");
        startActivityForResult(intent, ADD_NEW_ADDRESS_CODE);
//        startActivity(new Intent(CheckOutActivity.this, AddAddressActivity.class));
    }

    float totalAmount = 0;
    float deliveryFee = 1;

    void setOrderListData() {
        infoOfOrder.clear();
        addItemsToCartModelArrayList.clear();
        totalAmount = 0;
        addItemsToCartModelArrayList = AppCommon.getInstance(this).getAddToCartObject();
        for (int i = 0; i < addItemsToCartModelArrayList.size(); i++) {
            infoOfOrder.add(new InfoModel(addItemsToCartModelArrayList.get(i).getId(), addItemsToCartModelArrayList.get(i).getRestName(),
                    addItemsToCartModelArrayList.get(i).getName(), "$" + addItemsToCartModelArrayList.get(i).getPrice(),
                    "x " + addItemsToCartModelArrayList.get(i).getQuantity()));

            float price = Float.parseFloat(addItemsToCartModelArrayList.get(i).getPrice());
            int noOfQuantity = Integer.parseInt(addItemsToCartModelArrayList.get(i).getQuantity());
            totalAmount = totalAmount + (price * noOfQuantity);
        }

        setOrderListAdapter(infoOfOrder);
        subTotalText.setText("$" + totalAmount);
        deliveryCharges.setText("$" + deliveryFee);
        totalBill.setText("$" + (totalAmount + deliveryFee));

//        addAddressModelArrayList.clear();
//        addAddressModelArrayList.add(new AddAddressModel("Home", "Eddie Methew, 256 Near ABC LandMark, Street Line 1, Street Line 2, 45874, Bristol"));
//        addAddressModelArrayList.add(new AddAddressModel("Bussiness", "Eddie Methew, 256 Near ABC LandMark, Street Line 1, Street Line 2, 45874, Bristol"));
    }

    @OnClick(R.id.loginButton)
    void setLoginButton() {
        if (!AppCommon.getInstance(CheckOutActivity.this).isUserLogIn()) {
            Intent intent = new Intent(CheckOutActivity.this, LoginActivity.class);
            startActivityForResult(intent, LOGIN_CODE);
        }
    }

    @OnClick(R.id.proceedToPayBtn)
    void setProceedToPayBtn() {
        validationToProceedPay();
    }

    private void callingOrderCheckOut(String tokenKey, Integer customerId, Integer resturantId, Integer addressId, final float orderAmount,
                                      String deliveryOption, String itemId, String quantity, String addonId,
                                      String couponCode, String preOrderDateTime, String preOrderComments) {
        AppCommon.getInstance(CheckOutActivity.this).setNonTouchableFlags(CheckOutActivity.this);
        if (AppCommon.getInstance(CheckOutActivity.this).isConnectingToInternet(CheckOutActivity.this)) {
            progressBar.setVisibility(View.VISIBLE);
            //  final String token = myFirebaseInstanceIDService.getDeviceToken();
            OrderCheckOutEntity orderCheckOutEntity = new OrderCheckOutEntity(tokenKey, customerId, resturantId, addressId, orderAmount,
                    deliveryOption, itemId, quantity, addonId, couponCode,preOrderDateTime,preOrderComments);
            FoodMonkeyAppService foodMonkeyAppService = ServiceGenerator.createService(FoodMonkeyAppService.class);
            call = foodMonkeyAppService.OrderCheckOut(orderCheckOutEntity);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(CheckOutActivity.this).clearNonTouchableFlags(CheckOutActivity.this);
                    if (response.code() == 200) {
                        progressBar.setVisibility(View.GONE);
                        OrderCheckOutResponse orderCheckOutResponse = (OrderCheckOutResponse) response.body();
                        if (response.body() != null) {
                            if (orderCheckOutResponse.getCode().equals("200")) {
                                Toast.makeText(CheckOutActivity.this, "Order Added Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(CheckOutActivity.this, CompletePaymentActivity.class);
                                intent.putExtra("orderID", orderCheckOutResponse.getOrderId());
                                intent.putExtra("totalAmount",orderAmount);
                                startActivity(intent);
//                                setAdapter(restDetailMenuResponse.getResturantFeedbackList());

                            } else {
                                AppCommon.showDialog(CheckOutActivity.this, orderCheckOutResponse.getMessage());
                            }
                        }
                    } else {
                        AppCommon.showDialog(CheckOutActivity.this, getString(R.string.serverError));
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    AppCommon.getInstance(CheckOutActivity.this).clearNonTouchableFlags(CheckOutActivity.this);
                    progressBar.setVisibility(View.GONE);
                    if (t instanceof JsonSyntaxException) {
                        AppCommon.showDialog(CheckOutActivity.this, "No Rating found");
                    } else {
                        AppCommon.showDialog(CheckOutActivity.this, getResources().getString(R.string.network_error));

                    }
                }
            });
        } else {
            AppCommon.getInstance(CheckOutActivity.this).clearNonTouchableFlags(CheckOutActivity.this);
            AppCommon.showDialog(CheckOutActivity.this, getResources().getString(R.string.network_error));
        }
    }

    void validationToProceedPay() {
        String deliveryOption = "";
        if (deliveryOneHr.isChecked()) {
            deliveryOption = "Delivery";
        } else {
            deliveryOption = "Takeaway";
        }


        String itemID = "", quanities = "", addOnIDs = "";
        int restID = 0;
        float totalAmount = this.totalAmount + deliveryFee;
        for (int i = 0; i < addItemsToCartModelArrayList.size(); i++) {
            if (itemID.isEmpty()) {
                itemID = addItemsToCartModelArrayList.get(i).getItemID();
            } else {
                itemID = itemID + "," + addItemsToCartModelArrayList.get(i).getItemID();
            }
            if (quanities.isEmpty()) {
                quanities = addItemsToCartModelArrayList.get(i).getQuantity();
            } else {
                quanities = quanities + "," + addItemsToCartModelArrayList.get(i).getQuantity();
            }
            if (addOnIDs.isEmpty()) {
                addOnIDs = addItemsToCartModelArrayList.get(i).getAddOnID();
            } else {
                addOnIDs = addOnIDs + "," + addItemsToCartModelArrayList.get(i).getAddOnID();
            }
            restID = Integer.parseInt(addItemsToCartModelArrayList.get(i).getResturantID());
        }

        if (addressID == -1) {
            Toast.makeText(this, "Please select Address to deliver", Toast.LENGTH_SHORT).show();
        } else {
            callingOrderCheckOut(AppCommon.getInstance(CheckOutActivity.this).getDeviceToken(),
                    Integer.parseInt(AppCommon.getInstance(CheckOutActivity.this).getCustomerID()),
                    restID, addressID, totalAmount,
                    deliveryOption, itemID, quanities, addOnIDs,"","","");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == LOGIN_CODE) {
                boolean status = data.getBooleanExtra("data", false);
                setLayoutVisiblity(status);
            } else if (requestCode == UPDATE_IN_VALUE_CODE) {
                float totalValue = data.getFloatExtra("totalValue", 0);
                if (totalValue != 0) {
                    setOrderListData();
                }
            } else if (requestCode == ADD_NEW_ADDRESS_CODE) {
                callingAddressList();
            }
        }
    }

    void setLayoutVisiblity(boolean status) {
        if (status) {
            chooseDeliveryTypeLayout.setVisibility(View.VISIBLE);
            loginLayout.setVisibility(View.GONE);
            callingAddressList();
            // call address API here---
        } else {
            chooseDeliveryTypeLayout.setVisibility(View.GONE);
            loginLayout.setVisibility(View.VISIBLE);
        }
    }

    void setAddressListingAdapter(List<CustomerAddressObject> customerAddresses) {
        addressAdapter = new AddressAdapter(this, customerAddresses);
        chooseDeliveryRecyclerView.setAdapter(addressAdapter);

    }

    Call call;

    private void callingAddressList() {
        AppCommon.getInstance(this).setNonTouchableFlags(this);
        if (AppCommon.getInstance(CheckOutActivity.this).isConnectingToInternet(CheckOutActivity.this)) {
            progressBar.setVisibility(View.VISIBLE);
//            ResturantListEnity resturantListEnity = new ResturantListEnity(tokenKey, resturantID);
            CustAddAddressEntity custAddAddressEntity = new CustAddAddressEntity(AppCommon.getInstance(CheckOutActivity.this).getDeviceToken(),
                    AppCommon.getInstance(CheckOutActivity.this).getCustomerID());
            FoodMonkeyAppService foodMonkeyAppService = ServiceGenerator.createService(FoodMonkeyAppService.class);
            call = foodMonkeyAppService.ListCustomerAddress(custAddAddressEntity);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(CheckOutActivity.this).clearNonTouchableFlags(CheckOutActivity.this);
                    if (response.code() == 200) {
                        progressBar.setVisibility(View.GONE);
                        ListCustAddResponse listCustAddResponse = (ListCustAddResponse) response.body();
                        if (response.body() != null) {
                            try {
                                if (listCustAddResponse.getCode().equals("200")) {
                                    setAddressListingAdapter(listCustAddResponse.getCustomerAddresses());
                                } else {
                                    AppCommon.showDialog(CheckOutActivity.this, listCustAddResponse.getMessage());
                                }
                            } catch (NullPointerException e) {
                                AppCommon.showDialog(CheckOutActivity.this, listCustAddResponse.getMessage());
                            }

                        }
                    } else {
                        AppCommon.showDialog(CheckOutActivity.this, getString(R.string.serverError));
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    AppCommon.getInstance(CheckOutActivity.this).clearNonTouchableFlags(CheckOutActivity.this);
                    progressBar.setVisibility(View.GONE);
                    if (t instanceof JsonSyntaxException) {
                        AppCommon.showDialog(CheckOutActivity.this, "No Address found");
                    } else {
                        AppCommon.showDialog(CheckOutActivity.this, getResources().getString(R.string.network_error));

                    }
                }
            });
        } else {
            AppCommon.getInstance(CheckOutActivity.this).clearNonTouchableFlags(CheckOutActivity.this);
            AppCommon.showDialog(this, getResources().getString(R.string.network_error));
        }
    }

    int addressID = -1;

    public void setAddressID(Integer addressId) {
        this.addressID = addressId;
    }
}

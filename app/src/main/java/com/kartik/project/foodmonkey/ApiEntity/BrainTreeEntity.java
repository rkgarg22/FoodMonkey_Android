package com.kartik.project.foodmonkey.ApiEntity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kartikeya on 25/11/2018.
 */

public class BrainTreeEntity {
    @SerializedName("TokenKey")
    String tokenKey;
    @SerializedName("Payment_Method_Nonce")
    String paymentMethodNonce;
    @SerializedName("Amount")
    String amount;
    @SerializedName("orderId")
    String orderId;
    @SerializedName("Customer_First_Name")
    String customerFirstName;
    @SerializedName("Customer_Last_Name")
    String customerLastName;
    @SerializedName("Customer_Email")
    String customerEmail;

    public BrainTreeEntity(String tokenKey, String paymentMethodNonce, String amount, String orderId,
                           String customerFirstName, String customerLastName, String customerEmail) {
        this.tokenKey = tokenKey;
        this.paymentMethodNonce = paymentMethodNonce;
        this.amount = amount;
        this.orderId = orderId;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.customerEmail = customerEmail;
    }

    public String getTokenKey() {
        return tokenKey;
    }

    public void setTokenKey(String tokenKey) {
        this.tokenKey = tokenKey;
    }

    public String getPaymentMethodNonce() {
        return paymentMethodNonce;
    }

    public void setPaymentMethodNonce(String paymentMethodNonce) {
        this.paymentMethodNonce = paymentMethodNonce;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
}

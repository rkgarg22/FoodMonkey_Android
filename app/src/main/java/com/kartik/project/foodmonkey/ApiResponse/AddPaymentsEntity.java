package com.kartik.project.foodmonkey.ApiResponse;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kartikeya on 30/10/2018.
 */

public class AddPaymentsEntity {
    @SerializedName("TokenKey")
    String tokenKey;

    @SerializedName("Order_id")
    String orderId;

    @SerializedName("Cardid")
    String cardId;

    @SerializedName("Payment_Method")
    String paymentMethod;


    public AddPaymentsEntity(String tokenKey, String orderId, String cardId, String paymentMethod) {
        this.tokenKey = tokenKey;
        this.orderId = orderId;
        this.cardId = cardId;
        this.paymentMethod = paymentMethod;
    }

    public String getTokenKey() {
        return tokenKey;
    }

    public void setTokenKey(String tokenKey) {
        this.tokenKey = tokenKey;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}

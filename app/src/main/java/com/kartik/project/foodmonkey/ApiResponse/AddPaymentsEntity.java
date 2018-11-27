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

    @SerializedName("Payment_Method_Nonce")
    String paymentMethodNonce;

    @SerializedName("Stripe_Cardid")
    String stripCardId;

    @SerializedName("Payment_Method")
    String paymentMethod;


    public AddPaymentsEntity(String tokenKey, String orderId, String stripCardId, String paymentMethod) {
        this.tokenKey = tokenKey;
        this.orderId = orderId;
        this.stripCardId = stripCardId;
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentMethodNonce() {
        return paymentMethodNonce;
    }

    public void setPaymentMethodNonce(String paymentMethodNonce) {
        this.paymentMethodNonce = paymentMethodNonce;
    }

    public String getStripCardId() {
        return stripCardId;
    }

    public void setStripCardId(String stripCardId) {
        this.stripCardId = stripCardId;
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

    public String getStripeCardId() {
        return stripCardId;
    }

    public void setStripeCardId(String cardId) {
        this.stripCardId = cardId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}

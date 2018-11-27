package com.kartik.project.foodmonkey.ApiEntity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kartikeya on 29/10/2018.
 */

public class AddToCardEntity {
    @SerializedName("TokenKey")
    String tokenKey;

    @SerializedName("Stripe_Customer_Id")
    String stripeCustomerId ;

    @SerializedName("Stripe_Token")
    String stripeToken;

    public AddToCardEntity(String tokenKey, String stripeCustomerId, String stripeToken) {
        this.tokenKey = tokenKey;
        this.stripeCustomerId = stripeCustomerId;
        this.stripeToken = stripeToken;
    }

    public String getTokenKey() {
        return tokenKey;
    }

    public void setTokenKey(String tokenKey) {
        this.tokenKey = tokenKey;
    }

    public String getStripeCustomerId() {
        return stripeCustomerId;
    }

    public void setStripeCustomerId(String stripeCustomerId) {
        this.stripeCustomerId = stripeCustomerId;
    }

    public String getStripeToken() {
        return stripeToken;
    }

    public void setStripeToken(String stripeToken) {
        this.stripeToken = stripeToken;
    }
}

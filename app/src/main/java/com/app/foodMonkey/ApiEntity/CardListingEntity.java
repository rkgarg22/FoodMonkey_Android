package com.app.foodMonkey.ApiEntity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kartikeya on 30/10/2018.
 */

public class CardListingEntity {
    @SerializedName("TokenKey")
    private String tokenKey;
    @SerializedName("Stripe_Customer_Id")
    private String stripeCustomerId;

    public CardListingEntity(String tokenKey, String customerId) {
        this.tokenKey = tokenKey;
        this.stripeCustomerId = customerId;
    }

    public String getTokenKey() {
        return tokenKey;
    }

    public void setTokenKey(String tokenKey) {
        this.tokenKey = tokenKey;
    }

    public String getCustomerId() {
        return stripeCustomerId;
    }

    public void setCustomerId(String customerId) {
        this.stripeCustomerId = customerId;
    }
}

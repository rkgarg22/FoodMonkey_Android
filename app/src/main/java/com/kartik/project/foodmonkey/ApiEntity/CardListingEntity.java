package com.kartik.project.foodmonkey.ApiEntity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kartikeya on 30/10/2018.
 */

public class CardListingEntity {
    @SerializedName("TokenKey")
    private String tokenKey;
    @SerializedName("Customer_id")
    private String customerId;

    public CardListingEntity(String tokenKey, String customerId) {
        this.tokenKey = tokenKey;
        this.customerId = customerId;
    }

    public String getTokenKey() {
        return tokenKey;
    }

    public void setTokenKey(String tokenKey) {
        this.tokenKey = tokenKey;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}

package com.app.foodMonkey.ApiEntity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kartikeya on 26/11/2018.
 */

public class CommonEntity {

    @SerializedName("TokenKey")
    String tokenKey;

    @SerializedName("Customer_id")
    String customerId;

    public CommonEntity(String tokenKey, String customerId) {
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

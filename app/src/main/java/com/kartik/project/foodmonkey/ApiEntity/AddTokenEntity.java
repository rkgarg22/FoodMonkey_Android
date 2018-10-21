package com.kartik.project.foodmonkey.ApiEntity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kartikeya on 05/10/2018.
 */

public class AddTokenEntity {
    @SerializedName("TokenKey")
    String tokenKey;

    @SerializedName("Customer_Type")
    String customerType;

    @SerializedName("CallingChannel")
    String callingChannel;

    @SerializedName("Resturant_id")
    int resturantId;

    public AddTokenEntity(String tokenKey) {
        this.tokenKey = tokenKey;
    }

    public AddTokenEntity(String tokenKey, int resturantId) {
        this.tokenKey = tokenKey;
        this.resturantId = resturantId;
    }

    public AddTokenEntity(String tokenKey, String customerType, String callingChannel) {
        this.tokenKey = tokenKey;
        this.customerType = customerType;
        this.callingChannel = callingChannel;
    }

    public String getTokenKey() {
        return tokenKey;
    }

    public void setTokenKey(String tokenKey) {
        this.tokenKey = tokenKey;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getCallingChannel() {
        return callingChannel;
    }

    public void setCallingChannel(String callingChannel) {
        this.callingChannel = callingChannel;
    }

    public int getResturantId() {
        return resturantId;
    }

    public void setResturantId(int resturantId) {
        this.resturantId = resturantId;
    }
}

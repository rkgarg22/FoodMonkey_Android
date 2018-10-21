package com.kartik.project.foodmonkey.ApiEntity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kartikeya on 10/10/2018.
 */

public class CustomerHomeEntity {
    @SerializedName("TokenKey")
    String tokenKey;

    @SerializedName("Customer_id")
    String customerID;

    public CustomerHomeEntity(String tokenKey, String customerID) {
        this.tokenKey = tokenKey;
        this.customerID = customerID;
    }

    public String getTokenKey() {
        return tokenKey;
    }

    public void setTokenKey(String tokenKey) {
        this.tokenKey = tokenKey;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }
}

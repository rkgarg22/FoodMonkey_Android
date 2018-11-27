package com.kartik.project.foodmonkey.ApiResponse;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kartikeya on 25/11/2018.
 */

public class GetBrainTreeResponse {
    @SerializedName("Code")
    private String code;
    @SerializedName("Message")
    private String message;
    @SerializedName("Braintree_token")
    private String braintreeToken;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getBraintreeToken() {
        return braintreeToken;
    }

    public void setBraintreeToken(String braintreeToken) {
        this.braintreeToken = braintreeToken;
    }
}

package com.kartik.project.foodmonkey.ApiResponse;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kartikeya on 16/10/2018.
 */

public class SignUpResponse {
//    { "Code": "200", "Message": "SignUp data is added in DB", "Customer_id": "87" }
    @SerializedName("Code")
    String code;

    @SerializedName("Message")
    String message;

    @SerializedName("Customer_id")
    String customerId;

    public SignUpResponse(String code, String message, String customerId) {
        this.code = code;
        this.message = message;
        this.customerId = customerId;
    }

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

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}

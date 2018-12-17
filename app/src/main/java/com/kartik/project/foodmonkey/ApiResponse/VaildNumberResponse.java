package com.kartik.project.foodmonkey.ApiResponse;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kartikeya on 12/12/2018.
 */

public class VaildNumberResponse {

    @SerializedName("Code")
    private String code;
    @SerializedName("Message")
    private String message;
    @SerializedName("Customer_id")
    private Integer customerId;

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

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}

package com.kartik.project.foodmonkey.ApiResponse;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kartikeya on 25/10/2018.
 */

public class CustomerAddressResponse {
    @SerializedName("Code")
    private String code;
    @SerializedName("Message")
    private String message;

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
}

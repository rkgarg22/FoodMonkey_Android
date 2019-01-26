package com.app.foodMonkey.ApiResponse;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kartikeya on 30/12/2018.
 */

public class ReferByMobileResponse {
    @SerializedName("Code")
    String code;

    @SerializedName("Message")
    String message;

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

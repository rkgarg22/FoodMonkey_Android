package com.kartik.project.foodmonkey.ApiResponse;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kartikeya on 14/12/2018.
 */

public class ChangePassForgotResp {
    @SerializedName("Status")
    String status;

    @SerializedName("Message")
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

package com.app.foodMonkey.ApiObject;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kartikeya on 11/12/2018.
 */

public class SendingOtpObject {
    @SerializedName("request_id")
    private String requestId;
    @SerializedName("status")
    private String status;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

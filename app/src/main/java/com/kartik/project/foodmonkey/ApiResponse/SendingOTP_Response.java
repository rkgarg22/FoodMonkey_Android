package com.kartik.project.foodmonkey.ApiResponse;

import com.google.gson.annotations.SerializedName;
import com.kartik.project.foodmonkey.ApiObject.SendingOtpObject;

/**
 * Created by kartikeya on 11/12/2018.
 */

public class SendingOTP_Response {
    @SerializedName("Code")
    private String code;
    @SerializedName("Message")
    private String message;
    @SerializedName("Result")
    private SendingOtpObject result;

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

    public SendingOtpObject getResult() {
        return result;
    }

    public void setResult(SendingOtpObject result) {
        this.result = result;
    }

}

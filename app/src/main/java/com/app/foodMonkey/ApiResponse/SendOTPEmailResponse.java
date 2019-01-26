package com.app.foodMonkey.ApiResponse;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kartikeya on 14/12/2018.
 */

public class SendOTPEmailResponse {
    @SerializedName("Code")
    private String code;
    @SerializedName("Message")
    private String message;
    @SerializedName("OTP_Code")
    private Integer oTPCode;

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

    public Integer getOTPCode() {
        return oTPCode;
    }

    public void setOTPCode(Integer oTPCode) {
        this.oTPCode = oTPCode;
    }

}

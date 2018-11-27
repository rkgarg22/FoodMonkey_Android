package com.kartik.project.foodmonkey.ApiResponse;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kartikeya on 25/11/2018.
 */

public class BrainTreePaymentResponse {
//    @SerializedName("Code")
//    String Code;
    @SerializedName("Message")
    String message;
    @SerializedName("Result_Output")
    String resultOutput;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResultOutput() {
        return resultOutput;
    }

    public void setResultOutput(String resultOutput) {
        this.resultOutput = resultOutput;
    }

//    public String getCode() {
//        return Code;
//    }
//
//    public void setCode(String code) {
//        Code = code;
//    }
}

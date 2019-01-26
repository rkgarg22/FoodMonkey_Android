package com.app.foodMonkey.ApiResponse;

import com.app.foodMonkey.ApiObject.FaqDetailsObject;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kartikeya on 23/01/2019.
 */

public class HelpResponse {
    @SerializedName("Code")
    private String code;
    @SerializedName("Message")
    private String message;
    @SerializedName("Faq_Details")
    private FaqDetailsObject faqDetails;

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

    public FaqDetailsObject getFaqDetails() {
        return faqDetails;
    }

    public void setFaqDetails(FaqDetailsObject faqDetails) {
        this.faqDetails = faqDetails;
    }
}

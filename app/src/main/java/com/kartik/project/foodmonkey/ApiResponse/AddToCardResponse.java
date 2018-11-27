package com.kartik.project.foodmonkey.ApiResponse;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kartikeya on 29/10/2018.
 */

public class AddToCardResponse {
    @SerializedName("Message")
    private StripeMessage message;

    public StripeMessage getMessage() {
        return message;
    }

    public void setMessage(StripeMessage message) {
        this.message = message;
    }
}

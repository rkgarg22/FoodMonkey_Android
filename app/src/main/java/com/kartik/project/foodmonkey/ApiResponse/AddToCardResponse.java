package com.kartik.project.foodmonkey.ApiResponse;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kartikeya on 29/10/2018.
 */

public class AddToCardResponse {
    @SerializedName("Code")
    String code;

    @SerializedName("Message")
    String message;

    @SerializedName("Card_id")
    String cardId;

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

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
}

package com.app.foodMonkey.ApiResponse;

import com.google.gson.annotations.SerializedName;
import com.app.foodMonkey.ApiObject.CardListObject;

import java.util.List;

/**
 * Created by kartikeya on 30/10/2018.
 */

public class CardListingResponse
{
    @SerializedName("Code")
    private String code;
    @SerializedName("Message")
    private String message;
    @SerializedName("Card_list")
    private List<CardListObject> cardList = null;

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

    public List<CardListObject> getCardList() {
        return cardList;
    }

    public void setCardList(List<CardListObject> cardList) {
        this.cardList = cardList;
    }
}

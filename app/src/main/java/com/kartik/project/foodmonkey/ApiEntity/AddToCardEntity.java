package com.kartik.project.foodmonkey.ApiEntity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kartikeya on 29/10/2018.
 */

public class AddToCardEntity {
    @SerializedName("TokenKey")
    String tokenKey;

    @SerializedName("Customer_id")
    int customerId;

    @SerializedName("NameOnCard")
    String nameOnCard;

    @SerializedName("CardNumber")
    long cardNumber;

    @SerializedName("ExpDate")
    int expDate;

    @SerializedName("ExpYear")
    int expYear;

    @SerializedName("CVV")
    int CVV;

    public AddToCardEntity(String tokenKey, int customerId, String nameOnCard, long cardNumber, int expDate, int expYear, int CVV) {
        this.tokenKey = tokenKey;
        this.customerId = customerId;
        this.nameOnCard = nameOnCard;
        this.cardNumber = cardNumber;
        this.expDate = expDate;
        this.expYear = expYear;
        this.CVV = CVV;
    }

    public String getTokenKey() {
        return tokenKey;
    }

    public void setTokenKey(String tokenKey) {
        this.tokenKey = tokenKey;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getExpDate() {
        return expDate;
    }

    public void setExpDate(int expDate) {
        this.expDate = expDate;
    }

    public int getExpYear() {
        return expYear;
    }

    public void setExpYear(int expYear) {
        this.expYear = expYear;
    }

    public int getCVV() {
        return CVV;
    }

    public void setCVV(int CVV) {
        this.CVV = CVV;
    }
}

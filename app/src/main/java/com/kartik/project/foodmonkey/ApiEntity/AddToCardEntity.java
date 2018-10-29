package com.kartik.project.foodmonkey.ApiEntity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kartikeya on 29/10/2018.
 */

public class AddToCardEntity {
    @SerializedName("TokenKey")
    String tokenKey;

    @SerializedName("Customer_id")
    String customerId;

    @SerializedName("NameOnCard")
    String nameOnCard;

    @SerializedName("CardNumber")
    String cardNumber;

    @SerializedName("ExpDate")
    String expDate;

    @SerializedName("ExpYear")
    String expYear;

    @SerializedName("CVV")
    String CVV;

    public AddToCardEntity(String tokenKey, String customerId, String nameOnCard, String cardNumber, String expDate, String expYear, String CVV) {
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

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getExpYear() {
        return expYear;
    }

    public void setExpYear(String expYear) {
        this.expYear = expYear;
    }

    public String getCVV() {
        return CVV;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }
}

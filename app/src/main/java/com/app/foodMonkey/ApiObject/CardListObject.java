package com.app.foodMonkey.ApiObject;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kartikeya on 30/10/2018.
 */

public class CardListObject {
    @SerializedName("Cardid")
    private Integer cardid;
    @SerializedName("Customer_id")
    private Integer customerId;
    @SerializedName("NameOnCard")
    private String nameOnCard;
    @SerializedName("CardNumber")
    private long cardNumber;
    @SerializedName("Exp_Date")
    private String expDate;
    @SerializedName("CVV")
    private Integer cVV;
    boolean isSelected;

    public Integer getcVV() {
        return cVV;
    }

    public void setcVV(Integer cVV) {
        this.cVV = cVV;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public Integer getCardid() {
        return cardid;
    }

    public void setCardid(Integer cardid) {
        this.cardid = cardid;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
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

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public Integer getCVV() {
        return cVV;
    }

    public void setCVV(Integer cVV) {
        this.cVV = cVV;
    }
}

package com.app.foodMonkey.ApiEntity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kartikeya on 25/10/2018.
 */

public class CustAddAddressEntity
{

    @SerializedName("TokenKey")
    private String tokenKey;
    @SerializedName("Customer_id")
    private String customerId;
    @SerializedName("Address_type")
    private String addressType;
    @SerializedName("Mobile_Number")
    private String mobileNumber;
    @SerializedName("House_No")
    private String houseNo;
    @SerializedName("Address_Note")
    private String addressNote;
    @SerializedName("Street_Line1")
    private String streetLine1;
    @SerializedName("Street_Line2")
    private String streetLine2;
    @SerializedName("Post_Code")
    private String postCode;
    @SerializedName("City")
    private String city;

    public CustAddAddressEntity(String tokenKey, String customerId, String addressType, String mobileNumber, String houseNo, String addressNote, String streetLine1, String streetLine2, String postCode, String city) {
        this.tokenKey = tokenKey;
        this.customerId = customerId;
        this.addressType = addressType;
        this.mobileNumber = mobileNumber;
        this.houseNo = houseNo;
        this.addressNote = addressNote;
        this.streetLine1 = streetLine1;
        this.streetLine2 = streetLine2;
        this.postCode = postCode;
        this.city = city;
    }

    public CustAddAddressEntity(String tokenKey, String customerId) {
        this.tokenKey = tokenKey;
        this.customerId = customerId;
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

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getAddressNote() {
        return addressNote;
    }

    public void setAddressNote(String addressNote) {
        this.addressNote = addressNote;
    }

    public String getStreetLine1() {
        return streetLine1;
    }

    public void setStreetLine1(String streetLine1) {
        this.streetLine1 = streetLine1;
    }

    public String getStreetLine2() {
        return streetLine2;
    }

    public void setStreetLine2(String streetLine2) {
        this.streetLine2 = streetLine2;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
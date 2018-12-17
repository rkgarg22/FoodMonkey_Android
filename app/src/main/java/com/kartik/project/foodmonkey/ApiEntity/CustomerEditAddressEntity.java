package com.kartik.project.foodmonkey.ApiEntity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kartikeya on 03/12/2018.
 */

public class CustomerEditAddressEntity {

    @SerializedName("TokenKey")
    String tokenKey;

    @SerializedName("Address_id")
    int addressId;

    @SerializedName("Address_type")
    String addressType;

    @SerializedName("Mobile_Number")
    long mobileNumber;

    @SerializedName("House_No")
    int houseNo;

    @SerializedName("Address_Note")
    String addressNote;

    @SerializedName("Street_Line1")
    String streetLine1;

    @SerializedName("Street_Line2")
    String streetLine2;

    @SerializedName("Post_Code")
    String postCode;

    @SerializedName("City")
    String city;

    public CustomerEditAddressEntity(String tokenKey, int addressId, String addressType, long mobileNumber,
                                     int houseNo, String addressNote, String streetLine1, String streetLine2,
                                     String postCode, String city) {
        this.tokenKey = tokenKey;
        this.addressId = addressId;
        this.addressType = addressType;
        this.mobileNumber = mobileNumber;
        this.houseNo = houseNo;
        this.addressNote = addressNote;
        this.streetLine1 = streetLine1;
        this.streetLine2 = streetLine2;
        this.postCode = postCode;
        this.city = city;
    }

    public String getTokenKey() {
        return tokenKey;
    }

    public void setTokenKey(String tokenKey) {
        this.tokenKey = tokenKey;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public int getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(int houseNo) {
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

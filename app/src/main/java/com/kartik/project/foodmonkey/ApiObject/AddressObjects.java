package com.kartik.project.foodmonkey.ApiObject;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by kartikeya on 26/11/2018.
 */

public class AddressObjects implements Serializable
{
    @SerializedName("Address_Id")
    private Integer addressId;
    @SerializedName("Address_Name")
    private String addressName;
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
    @SerializedName("Mobile_Number")
    private Integer mobileNumber;

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
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

    public Integer getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Integer mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

}

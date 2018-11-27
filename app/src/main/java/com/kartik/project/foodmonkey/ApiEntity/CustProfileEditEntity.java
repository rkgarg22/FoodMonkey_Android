package com.kartik.project.foodmonkey.ApiEntity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kartikeya on 20/11/2018.
 */

public class CustProfileEditEntity
{
    @SerializedName("TokenKey")
    private String tokenKey;
    @SerializedName("Customer_id")
    private String customerId;
    @SerializedName("First_Name")
    private String firstName;
    @SerializedName("Middle_Intial")
    private String middleIntial;
    @SerializedName("Sur_Name")
    private String surName;
    @SerializedName("Gender")
    private String gender;
    @SerializedName("DOB")
    private String dOB;
    @SerializedName("ProfilePic")
    private String profilePic;

    public CustProfileEditEntity(String tokenKey, String customerId,
                                 String firstName, String middleIntial, String surName,
                                 String gender, String dOB, String profilePic) {
        this.tokenKey = tokenKey;
        this.customerId = customerId;
        this.firstName = firstName;
        this.middleIntial = middleIntial;
        this.surName = surName;
        this.gender = gender;
        this.dOB = dOB;
        this.profilePic = profilePic;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleIntial() {
        return middleIntial;
    }

    public void setMiddleIntial(String middleIntial) {
        this.middleIntial = middleIntial;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDOB() {
        return dOB;
    }

    public void setDOB(String dOB) {
        this.dOB = dOB;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

}
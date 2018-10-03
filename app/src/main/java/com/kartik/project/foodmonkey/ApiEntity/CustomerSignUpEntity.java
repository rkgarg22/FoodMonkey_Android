package com.kartik.project.foodmonkey.ApiEntity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kartikeya on 02/10/2018.
 */

public class CustomerSignUpEntity {
    @SerializedName("TokenKey")
    String tokenKey;

    @SerializedName("First_Name")
    String firstName;

    @SerializedName("Middle_Intial")
    String middleName;

    @SerializedName("Sur_Name")
    String surName;

    @SerializedName("Gender")
    String gender;

    @SerializedName("Email")
    String email;

    @SerializedName("Mobile")
    String mobile;

    @SerializedName("DOB")
    String dateOfBirth;

    @SerializedName("Password")
    String password;

    @SerializedName("ProfilePic")
    String profilePic;

    @SerializedName("CallingChannel")
    String channelCalling;

    public CustomerSignUpEntity(String tokenKey, String firstName, String middleName, String surName,
                                String gender, String email, String mobile, String dateOfBirth,
                                String password, String profilePic, String channelCalling) {
        this.tokenKey = tokenKey;
        this.firstName = firstName;
        this.middleName = middleName;
        this.surName = surName;
        this.gender = gender;
        this.email = email;
        this.mobile = mobile;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
        this.profilePic = profilePic;
        this.channelCalling = channelCalling;
    }

    public String getTokenKey() {
        return tokenKey;
    }

    public void setTokenKey(String tokenKey) {
        this.tokenKey = tokenKey;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getChannelCalling() {
        return channelCalling;
    }

    public void setChannelCalling(String channelCalling) {
        this.channelCalling = channelCalling;
    }
}

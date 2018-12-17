package com.kartik.project.foodmonkey.ApiObject;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kartikeya on 22/10/2018.
 */

public class CustomerSignUpObject {
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
    @SerializedName("Email")
    private String email;
    @SerializedName("Mobile")
    private String mobile;
    @SerializedName("DOB")
    private String dOB;
    @SerializedName("Password")
    private String password;
    @SerializedName("Image_Link")
    private String imageLink;
    @SerializedName("Status")
    private Integer status;
    @SerializedName("Registration_date")
    private String registrationDate;
    @SerializedName("Stripe_Customer_Id")
    private String stripeCustomerId;
    @SerializedName("Refer_Code")
    private String referCode;
    @SerializedName("ProfilePic_Url")
    private String profileUrl;

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

    public String getdOB() {
        return dOB;
    }

    public void setdOB(String dOB) {
        this.dOB = dOB;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getStripeCustomerId() {
        return stripeCustomerId;
    }

    public void setStripeCustomerId(String stripeCustomerId) {
        this.stripeCustomerId = stripeCustomerId;
    }

    public String getReferCode() {
        return referCode;
    }

    public void setReferCode(String referCode) {
        this.referCode = referCode;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }
}

package com.kartik.project.foodmonkey.ApiObject;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kartikeya on 02/10/2018.
 */

public class RestutantListObject {

    @SerializedName("Rest_Id")
    private Integer restId;

    @SerializedName("Rest_Name")
    private String restName;

    @SerializedName("Email")
    private String email;

    @SerializedName("Min_Spend")
    private String minSpend;

    @SerializedName("Delivery")
    private String delivery;

    @SerializedName("Rest_Street_Line1")
    private String restStreetLine1;

    @SerializedName("Rest_Street_Line2")
    private String restStreetLine2;

    @SerializedName("Rest_Post_Code")
    private String restPostCode;

    @SerializedName("Rest_City")
    private String restCity;

    @SerializedName("Owner_Name")
    private String ownerName;

    @SerializedName("Owner_Mobile")
    private String ownerMobile;

    @SerializedName("Owner_Landline")
    private String ownerLandline;

    @SerializedName("Owner_Street_Line1")
    private String ownerStreetLine1;

    @SerializedName("Owner_Street_Line2")
    private String ownerStreetLine2;

    @SerializedName("Owner_Post_Code")
    private String ownerPostCode;

    @SerializedName("Owner_City")
    private String ownerCity;

    @SerializedName("Emerg_Contact_Name")
    private String emergContactName;

    @SerializedName("Emerg_Contact_Number")
    private String emergContactNumber;

    @SerializedName("Opening_Hours")
    private String openingHours;

    @SerializedName("Closing_Hours")
    private String closingHours;

    @SerializedName("Password")
    private String password;

    @SerializedName("DeliveryOption")
    private String deliveryOption;

    @SerializedName("IsSponsoredRest")
    private Integer isSponsoredRest;

    @SerializedName("Image_Link")
    private String imageLink;

    @SerializedName("AggregateFeedback")
    private String aggregateFeedback;

    @SerializedName("NumberOfReviews")
    private Integer numberOfReviews;

    @SerializedName("IsCurrentlyOnline")
    private Integer isCurrentlyOnline;

    @SerializedName("DiscountOffer")
    private String discountOffer;

    public Integer getRestId() {
        return restId;
    }

    public void setRestId(Integer restId) {
        this.restId = restId;
    }

    public String getRestName() {
        return restName;
    }

    public void setRestName(String restName) {
        this.restName = restName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMinSpend() {
        return minSpend;
    }

    public void setMinSpend(String minSpend) {
        this.minSpend = minSpend;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getRestStreetLine1() {
        return restStreetLine1;
    }

    public void setRestStreetLine1(String restStreetLine1) {
        this.restStreetLine1 = restStreetLine1;
    }

    public String getRestStreetLine2() {
        return restStreetLine2;
    }

    public void setRestStreetLine2(String restStreetLine2) {
        this.restStreetLine2 = restStreetLine2;
    }

    public String getRestPostCode() {
        return restPostCode;
    }

    public void setRestPostCode(String restPostCode) {
        this.restPostCode = restPostCode;
    }

    public String getRestCity() {
        return restCity;
    }

    public void setRestCity(String restCity) {
        this.restCity = restCity;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerMobile() {
        return ownerMobile;
    }

    public void setOwnerMobile(String ownerMobile) {
        this.ownerMobile = ownerMobile;
    }

    public String getOwnerLandline() {
        return ownerLandline;
    }

    public void setOwnerLandline(String ownerLandline) {
        this.ownerLandline = ownerLandline;
    }

    public String getOwnerStreetLine1() {
        return ownerStreetLine1;
    }

    public void setOwnerStreetLine1(String ownerStreetLine1) {
        this.ownerStreetLine1 = ownerStreetLine1;
    }

    public String getOwnerStreetLine2() {
        return ownerStreetLine2;
    }

    public void setOwnerStreetLine2(String ownerStreetLine2) {
        this.ownerStreetLine2 = ownerStreetLine2;
    }

    public String getOwnerPostCode() {
        return ownerPostCode;
    }

    public void setOwnerPostCode(String ownerPostCode) {
        this.ownerPostCode = ownerPostCode;
    }

    public String getOwnerCity() {
        return ownerCity;
    }

    public void setOwnerCity(String ownerCity) {
        this.ownerCity = ownerCity;
    }

    public String getEmergContactName() {
        return emergContactName;
    }

    public void setEmergContactName(String emergContactName) {
        this.emergContactName = emergContactName;
    }

    public String getEmergContactNumber() {
        return emergContactNumber;
    }

    public void setEmergContactNumber(String emergContactNumber) {
        this.emergContactNumber = emergContactNumber;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    public String getClosingHours() {
        return closingHours;
    }

    public void setClosingHours(String closingHours) {
        this.closingHours = closingHours;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDeliveryOption() {
        return deliveryOption;
    }

    public void setDeliveryOption(String deliveryOption) {
        this.deliveryOption = deliveryOption;
    }

    public Integer getIsSponsoredRest() {
        return isSponsoredRest;
    }

    public void setIsSponsoredRest(Integer isSponsoredRest) {
        this.isSponsoredRest = isSponsoredRest;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getAggregateFeedback() {
        return aggregateFeedback;
    }

    public void setAggregateFeedback(String aggregateFeedback) {
        this.aggregateFeedback = aggregateFeedback;
    }

    public Integer getNumberOfReviews() {
        return numberOfReviews;
    }

    public void setNumberOfReviews(Integer numberOfReviews) {
        this.numberOfReviews = numberOfReviews;
    }

    public Integer getIsCurrentlyOnline() {
        return isCurrentlyOnline;
    }

    public void setIsCurrentlyOnline(Integer isCurrentlyOnline) {
        this.isCurrentlyOnline = isCurrentlyOnline;
    }

    public String getDiscountOffer() {
        return discountOffer;
    }

    public void setDiscountOffer(String discountOffer) {
        this.discountOffer = discountOffer;
    }
}

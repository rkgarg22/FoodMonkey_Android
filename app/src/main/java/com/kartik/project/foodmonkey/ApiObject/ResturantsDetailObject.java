package com.kartik.project.foodmonkey.ApiObject;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by kartikeya on 14/10/2018.
 */

public class ResturantsDetailObject implements Serializable {
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
    @SerializedName("Rest_Info")
    private String restInfo;
    @SerializedName("DeliveryStartTime")
    private String deliveryStartTime;
    @SerializedName("DeliveryEndTime")
    private String deliveryEndTime;
    @SerializedName("DeliveryAreaCovered")
    private Integer deliveryAreaCovered;
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
    private Object discountOffer;
    @SerializedName("IsHalal")
    private Integer isHalal;
    @SerializedName("IsPreorder")
    private Integer isPreorder;
    @SerializedName("Cousine_List")
    private String cousineList;
    @SerializedName("Cousine1")
    private String cousine1;
    @SerializedName("Registeration_Date")
    private String registerationDate;
    @SerializedName("is_menuadded")
    private Integer isMenuadded;

    @SerializedName("Monday_open")
    private String mondayOpen;
    @SerializedName("Monday_close")
    private String mondayClose;
    @SerializedName("Tuesday_open")
    private String tuesdayOpen;

    @SerializedName("Tuesday_close")
    private String tuesdayClose;
    @SerializedName("Wednesday_open")
    private String wednesdayOpen;
    @SerializedName("Wednesday_close")
    private String wednesdayClose;
    @SerializedName("Thursday_open")
    private String thursdayOpen;
    @SerializedName("Thursday_close")
    private String thursdayClose;
    @SerializedName("Friday_open")
    private String fridayOpen;
    @SerializedName("Friday_close")
    private String fridayClose;
    @SerializedName("Saturday_open")
    private String saturdayOpen;

    @SerializedName("Saturday_close")
    private String saturdayClose;

    @SerializedName("Sunday_open")
    private String Sunday_open;

    @SerializedName("Sunday_close")
    private String sundayClose;

    @SerializedName("Delivery_Time")
    private String deliveryTime;

    @SerializedName("Rest_Telephone")
    private String restTelephone;

    @SerializedName("Collection_From_Time")
    private String collectionFromTime;

    @SerializedName("Delivery_From_Time")
    private String deliveryFromTime;

    @SerializedName("Cousine2")
    private String Cousine2;

    @SerializedName("cordinate_latitude")
    private Double cordinateLatitude;

    @SerializedName("cordinate_longitude")
    private Double cordinateLongitude;

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

    public String getRestInfo() {
        return restInfo;
    }

    public void setRestInfo(String restInfo) {
        this.restInfo = restInfo;
    }

    public String getDeliveryStartTime() {
        return deliveryStartTime;
    }

    public void setDeliveryStartTime(String deliveryStartTime) {
        this.deliveryStartTime = deliveryStartTime;
    }

    public String getDeliveryEndTime() {
        return deliveryEndTime;
    }

    public void setDeliveryEndTime(String deliveryEndTime) {
        this.deliveryEndTime = deliveryEndTime;
    }

    public Integer getDeliveryAreaCovered() {
        return deliveryAreaCovered;
    }

    public void setDeliveryAreaCovered(Integer deliveryAreaCovered) {
        this.deliveryAreaCovered = deliveryAreaCovered;
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

    public Object getDiscountOffer() {
        return discountOffer;
    }

    public void setDiscountOffer(Object discountOffer) {
        this.discountOffer = discountOffer;
    }

    public Integer getIsHalal() {
        return isHalal;
    }

    public void setIsHalal(Integer isHalal) {
        this.isHalal = isHalal;
    }

    public Integer getIsPreorder() {
        return isPreorder;
    }

    public void setIsPreorder(Integer isPreorder) {
        this.isPreorder = isPreorder;
    }

    public String getCousineList() {
        return cousineList;
    }

    public void setCousineList(String cousineList) {
        this.cousineList = cousineList;
    }

    public String getRegisterationDate() {
        return registerationDate;
    }

    public void setRegisterationDate(String registerationDate) {
        this.registerationDate = registerationDate;
    }

    public Integer getIsMenuadded() {
        return isMenuadded;
    }

    public void setIsMenuadded(Integer isMenuadded) {
        this.isMenuadded = isMenuadded;
    }

    public String getMondayOpen() {
        return mondayOpen;
    }

    public void setMondayOpen(String mondayOpen) {
        this.mondayOpen = mondayOpen;
    }

    public String getMondayClose() {
        return mondayClose;
    }

    public void setMondayClose(String mondayClose) {
        this.mondayClose = mondayClose;
    }

    public String getTuesdayOpen() {
        return tuesdayOpen;
    }

    public void setTuesdayOpen(String tuesdayOpen) {
        this.tuesdayOpen = tuesdayOpen;
    }

    public String getTuesdayClose() {
        return tuesdayClose;
    }

    public void setTuesdayClose(String tuesdayClose) {
        this.tuesdayClose = tuesdayClose;
    }

    public String getWednesdayOpen() {
        return wednesdayOpen;
    }

    public void setWednesdayOpen(String wednesdayOpen) {
        this.wednesdayOpen = wednesdayOpen;
    }

    public String getWednesdayClose() {
        return wednesdayClose;
    }

    public void setWednesdayClose(String wednesdayClose) {
        this.wednesdayClose = wednesdayClose;
    }

    public String getThursdayOpen() {
        return thursdayOpen;
    }

    public void setThursdayOpen(String thursdayOpen) {
        this.thursdayOpen = thursdayOpen;
    }

    public String getThursdayClose() {
        return thursdayClose;
    }

    public void setThursdayClose(String thursdayClose) {
        this.thursdayClose = thursdayClose;
    }

    public String getFridayOpen() {
        return fridayOpen;
    }

    public void setFridayOpen(String fridayOpen) {
        this.fridayOpen = fridayOpen;
    }

    public String getFridayClose() {
        return fridayClose;
    }

    public void setFridayClose(String fridayClose) {
        this.fridayClose = fridayClose;
    }

    public String getSaturdayOpen() {
        return saturdayOpen;
    }

    public void setSaturdayOpen(String saturdayOpen) {
        this.saturdayOpen = saturdayOpen;
    }

    public String getSaturdayClose() {
        return saturdayClose;
    }

    public void setSaturdayClose(String saturdayClose) {
        this.saturdayClose = saturdayClose;
    }

    public String getSunday_open() {
        return Sunday_open;
    }

    public void setSunday_open(String sunday_open) {
        Sunday_open = sunday_open;
    }

    public String getSundayClose() {
        return sundayClose;
    }

    public void setSundayClose(String sundayClose) {
        this.sundayClose = sundayClose;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getRestTelephone() {
        return restTelephone;
    }

    public void setRestTelephone(String restTelephone) {
        this.restTelephone = restTelephone;
    }

    public String getCollectionFromTime() {
        return collectionFromTime;
    }

    public void setCollectionFromTime(String collectionFromTime) {
        this.collectionFromTime = collectionFromTime;
    }

    public String getDeliveryFromTime() {
        return deliveryFromTime;
    }

    public void setDeliveryFromTime(String deliveryFromTime) {
        this.deliveryFromTime = deliveryFromTime;
    }

    public String getCousine2() {
        return Cousine2;
    }

    public void setCousine2(String cousine2) {
        Cousine2 = cousine2;
    }

    public String getCousine1() {
        return cousine1;
    }

    public void setCousine1(String cousine1) {
        this.cousine1 = cousine1;
    }
}

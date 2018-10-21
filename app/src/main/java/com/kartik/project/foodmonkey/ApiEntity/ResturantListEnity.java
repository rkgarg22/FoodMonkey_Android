package com.kartik.project.foodmonkey.ApiEntity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kartikeya on 02/10/2018.
 */

public class ResturantListEnity {
    @SerializedName("TokenKey")
    private String tokenKey;
    @SerializedName("SearchBy")
    private String searchBy;
    @SerializedName("PostCode")
    private String postCode;
    @SerializedName("DeliveryOptions")
    private String deliveryOptions;
    @SerializedName("ListBy")
    private String listBy;
    @SerializedName("Cuisines")
    private String cuisines;
    @SerializedName("PageNumber")
    private String pageNumber;
    @SerializedName("CallingChannel")
    private String callingChannel;
    @SerializedName("Resturant_id")
    private String resturantID;

    public ResturantListEnity(String tokenKey, String searchBy, String postCode, String deliveryOptions, String listBy, String cuisines, String pageNumber, String callingChannel) {
        this.tokenKey = tokenKey;
        this.searchBy = searchBy;
        this.postCode = postCode;
        this.deliveryOptions = deliveryOptions;
        this.listBy = listBy;
        this.cuisines = cuisines;
        this.pageNumber = pageNumber;
        this.callingChannel = callingChannel;
    }

    public ResturantListEnity(String tokenKey, String resturantID) {
        this.tokenKey = tokenKey;
        this.resturantID = resturantID;
    }

    public String getTokenKey() {
        return tokenKey;
    }

    public void setTokenKey(String tokenKey) {
        this.tokenKey = tokenKey;
    }

    public String getSearchBy() {
        return searchBy;
    }

    public void setSearchBy(String searchBy) {
        this.searchBy = searchBy;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getDeliveryOptions() {
        return deliveryOptions;
    }

    public void setDeliveryOptions(String deliveryOptions) {
        this.deliveryOptions = deliveryOptions;
    }

    public String getListBy() {
        return listBy;
    }

    public void setListBy(String listBy) {
        this.listBy = listBy;
    }

    public String getCuisines() {
        return cuisines;
    }

    public void setCuisines(String cuisines) {
        this.cuisines = cuisines;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getCallingChannel() {
        return callingChannel;
    }

    public void setCallingChannel(String callingChannel) {
        this.callingChannel = callingChannel;
    }

    public String getResturantID() {
        return resturantID;
    }

    public void setResturantID(String resturantID) {
        this.resturantID = resturantID;
    }
}

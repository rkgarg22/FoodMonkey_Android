package com.kartik.project.foodmonkey.ApiEntity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kartikeya on 28/10/2018.
 */

public class OrderCheckOutEntity {

    @SerializedName("TokenKey")
    private String tokenKey;
    @SerializedName("Customer_Id")
    private Integer customerId;
    @SerializedName("Resturant_id")
    private Integer resturantId;
    @SerializedName("Address_id")
    private Integer addressId;
    @SerializedName("Order_Amount")
    private float orderAmount;
    @SerializedName("DeliveryOption")
    private String deliveryOption;
    @SerializedName("Item_Id")
    private String itemId;
    @SerializedName("Quantity")
    private String quantity;
    @SerializedName("Addon_Id")
    private String addonId;
    @SerializedName("CouponCode")
    private String couponCode;
    @SerializedName("Preorder_DateTime")
    private String preorderDateTime;
    @SerializedName("Preorder_Comments")
    private String preorderComments;

    public OrderCheckOutEntity(String tokenKey, Integer customerId, Integer resturantId, Integer addressId, float orderAmount,
                               String deliveryOption, String itemId, String quantity, String addonId, String couponCode,
                               String preorderDateTime, String preorderComments) {
        this.tokenKey = tokenKey;
        this.customerId = customerId;
        this.resturantId = resturantId;
        this.addressId = addressId;
        this.orderAmount = orderAmount;
        this.deliveryOption = deliveryOption;
        this.itemId = itemId;
        this.quantity = quantity;
        this.addonId = addonId;
        this.couponCode = couponCode;
        this.preorderDateTime = preorderDateTime;
        this.preorderComments = preorderComments;
    }

    public String getTokenKey() {
        return tokenKey;
    }

    public void setTokenKey(String tokenKey) {
        this.tokenKey = tokenKey;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getResturantId() {
        return resturantId;
    }

    public void setResturantId(Integer resturantId) {
        this.resturantId = resturantId;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public float getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(float orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getDeliveryOption() {
        return deliveryOption;
    }

    public void setDeliveryOption(String deliveryOption) {
        this.deliveryOption = deliveryOption;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getAddonId() {
        return addonId;
    }

    public void setAddonId(String addonId) {
        this.addonId = addonId;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getPreorderDateTime() {
        return preorderDateTime;
    }

    public void setPreorderDateTime(String preorderDateTime) {
        this.preorderDateTime = preorderDateTime;
    }

    public String getPreorderComments() {
        return preorderComments;
    }

    public void setPreorderComments(String preorderComments) {
        this.preorderComments = preorderComments;
    }
}

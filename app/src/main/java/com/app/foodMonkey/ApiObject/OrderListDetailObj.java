package com.app.foodMonkey.ApiObject;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kartikeya on 22/12/2018.
 */

public class OrderListDetailObj implements Serializable {
    @SerializedName("Order_Id")
    private Integer orderId;
    @SerializedName("Order_Amount")
    private String orderAmount;
    @SerializedName("Order_Date_Time")
    private String orderDateTime;
    @SerializedName("Order_Status")
    private String orderStatus;
    @SerializedName("IsPreOrder")
    private Integer isPreOrder;
    @SerializedName("PreOrderDeliveryDayTime")
    private String preOrderDeliveryDayTime;
    @SerializedName("PreOrderComments")
    private String preOrderComments;
    @SerializedName("Coupon_Code")
    private String couponCode;
    @SerializedName("PercentageDiscount")
    private String percentageDiscount;
    @SerializedName("Rest_Id")
    private Integer restId;
    @SerializedName("Rest_Name")
    private String restName;
    @SerializedName("Rest_Email")
    private String restEmail;
    @SerializedName("DeliveryCharges")
    private String deliveryCharges;
    @SerializedName("Rest_Street_Line1")
    private String restStreetLine1;
    @SerializedName("Rest_Street_Line2")
    private String restStreetLine2;
    @SerializedName("Rest_Post_Code")
    private String restPostCode;
    @SerializedName("Rest_City")
    private String restCity;
    @SerializedName("IsSponsoredRest")
    private Integer isSponsoredRest;
    @SerializedName("Rest_Image_Link")
    private String restImageLink;
    @SerializedName("AggregateFeedback")
    private String aggregateFeedback;
    @SerializedName("NumberOfReviews")
    private Integer numberOfReviews;
    @SerializedName("IsCurrentlyOnline")
    private Integer isCurrentlyOnline;
    @SerializedName("DiscountOffer")
    private Integer discountOffer;
    @SerializedName("Cousine1")
    private String cousine1;
    @SerializedName("Cousine2")
    private String cousine2;
    @SerializedName("DeliveryOption")
    private String deliveryOption;
    @SerializedName("Delivery_Address_Name")
    private String deliveryAddressName;
    @SerializedName("Delivery_House_No")
    private Object deliveryHouseNo;
    @SerializedName("Delivery_Street_Line1")
    private Object deliveryStreetLine1;
    @SerializedName("Delivery_Street_Line2")
    private Object deliveryStreetLine2;
    @SerializedName("Delivery_Post_Code")
    private Object deliveryPostCode;
    @SerializedName("Delivery_City")
    private Object deliveryCity;
    @SerializedName("Additional_Notes")
    private String additionalNotes;
   @SerializedName("Rest_Telephone")
    private String restTelephone;
    @SerializedName("Menu_Order")
    private List<MenuOrderList> menuOrder = null;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(String orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getIsPreOrder() {
        return isPreOrder;
    }

    public void setIsPreOrder(Integer isPreOrder) {
        this.isPreOrder = isPreOrder;
    }

    public String getPreOrderDeliveryDayTime() {
        return preOrderDeliveryDayTime;
    }

    public void setPreOrderDeliveryDayTime(String preOrderDeliveryDayTime) {
        this.preOrderDeliveryDayTime = preOrderDeliveryDayTime;
    }

    public String getPreOrderComments() {
        return preOrderComments;
    }

    public void setPreOrderComments(String preOrderComments) {
        this.preOrderComments = preOrderComments;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getPercentageDiscount() {
        return percentageDiscount;
    }

    public void setPercentageDiscount(String percentageDiscount) {
        this.percentageDiscount = percentageDiscount;
    }

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

    public String getRestEmail() {
        return restEmail;
    }

    public void setRestEmail(String restEmail) {
        this.restEmail = restEmail;
    }

    public String getDeliveryCharges() {
        return deliveryCharges;
    }

    public void setDeliveryCharges(String deliveryCharges) {
        this.deliveryCharges = deliveryCharges;
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

    public Integer getIsSponsoredRest() {
        return isSponsoredRest;
    }

    public void setIsSponsoredRest(Integer isSponsoredRest) {
        this.isSponsoredRest = isSponsoredRest;
    }

    public String getRestImageLink() {
        return restImageLink;
    }

    public void setRestImageLink(String restImageLink) {
        this.restImageLink = restImageLink;
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

    public Integer getDiscountOffer() {
        return discountOffer;
    }

    public void setDiscountOffer(Integer discountOffer) {
        this.discountOffer = discountOffer;
    }

    public String getCousine1() {
        return cousine1;
    }

    public void setCousine1(String cousine1) {
        this.cousine1 = cousine1;
    }

    public String getCousine2() {
        return cousine2;
    }

    public void setCousine2(String cousine2) {
        this.cousine2 = cousine2;
    }

    public String getDeliveryOption() {
        return deliveryOption;
    }

    public void setDeliveryOption(String deliveryOption) {
        this.deliveryOption = deliveryOption;
    }

    public String getDeliveryAddressName() {
        return deliveryAddressName;
    }

    public void setDeliveryAddressName(String deliveryAddressName) {
        this.deliveryAddressName = deliveryAddressName;
    }

    public Object getDeliveryHouseNo() {
        return deliveryHouseNo;
    }

    public void setDeliveryHouseNo(Object deliveryHouseNo) {
        this.deliveryHouseNo = deliveryHouseNo;
    }

    public Object getDeliveryStreetLine1() {
        return deliveryStreetLine1;
    }

    public void setDeliveryStreetLine1(Object deliveryStreetLine1) {
        this.deliveryStreetLine1 = deliveryStreetLine1;
    }

    public Object getDeliveryStreetLine2() {
        return deliveryStreetLine2;
    }

    public void setDeliveryStreetLine2(Object deliveryStreetLine2) {
        this.deliveryStreetLine2 = deliveryStreetLine2;
    }

    public Object getDeliveryPostCode() {
        return deliveryPostCode;
    }

    public void setDeliveryPostCode(Object deliveryPostCode) {
        this.deliveryPostCode = deliveryPostCode;
    }

    public Object getDeliveryCity() {
        return deliveryCity;
    }

    public void setDeliveryCity(Object deliveryCity) {
        this.deliveryCity = deliveryCity;
    }

    public String getAdditionalNotes() {
        return additionalNotes;
    }

    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }

    public List<MenuOrderList> getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(List<MenuOrderList> menuOrder) {
        this.menuOrder = menuOrder;
    }

    public String getRestTelephone() {
        return restTelephone;
    }

    public void setRestTelephone(String restTelephone) {
        this.restTelephone = restTelephone;
    }
}

package com.app.foodMonkey.ApiObject;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kartikeya on 26/01/2019.
 */

public class CouponDescObject {
    @SerializedName("Coupon_Code")
    private String couponCode;
    @SerializedName("PercentageDiscount")
    private Integer percentageDiscount;
    @SerializedName("ExpireDate")
    private String expireDate;

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public Integer getPercentageDiscount() {
        return percentageDiscount;
    }

    public void setPercentageDiscount(Integer percentageDiscount) {
        this.percentageDiscount = percentageDiscount;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }
}

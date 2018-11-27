package com.kartik.project.foodmonkey.ApiEntity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kartikeya on 26/11/2018.
 */

public class AddFeedBackEnitity
{
    @SerializedName("TokenKey")
    String tokenKey;

    @SerializedName("Resturant_id")
    int resturantId;

    @SerializedName("Customer_id")
    int customerId;

    @SerializedName("Number_of_Stars")
    int numberOfStars;

    @SerializedName("Comment")
    String comment;

    public AddFeedBackEnitity(String tokenKey, int resturantId, int customerId, int numberOfStars, String comment) {
        this.tokenKey = tokenKey;
        this.resturantId = resturantId;
        this.customerId = customerId;
        this.numberOfStars = numberOfStars;
        this.comment = comment;
    }

    public String getTokenKey() {
        return tokenKey;
    }

    public void setTokenKey(String tokenKey) {
        this.tokenKey = tokenKey;
    }

    public int getResturantId() {
        return resturantId;
    }

    public void setResturantId(int resturantId) {
        this.resturantId = resturantId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getNumberOfStars() {
        return numberOfStars;
    }

    public void setNumberOfStars(int numberOfStars) {
        this.numberOfStars = numberOfStars;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

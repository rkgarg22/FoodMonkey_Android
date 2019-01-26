package com.app.foodMonkey.ApiObject;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kartikeya on 18/10/2018.
 */

public class ResturantFeedbackList {
    @SerializedName("Rating_Id")
    private Integer ratingId;
    @SerializedName("Number_Of_Stars")
    private String numberOfStars;
    @SerializedName("Comments")
    private String comments;
    @SerializedName("Rating_Date")
    private String ratingDate;
    @SerializedName("Customer_Name")
    private String customerName;

    public Integer getRatingId() {
        return ratingId;
    }

    public void setRatingId(Integer ratingId) {
        this.ratingId = ratingId;
    }

    public String getNumberOfStars() {
        return numberOfStars;
    }

    public void setNumberOfStars(String numberOfStars) {
        this.numberOfStars = numberOfStars;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getRatingDate() {
        return ratingDate;
    }

    public void setRatingDate(String ratingDate) {
        this.ratingDate = ratingDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

}
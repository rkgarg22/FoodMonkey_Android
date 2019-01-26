package com.app.foodMonkey.ApiObject;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kartikeya on 11/10/2018.
 */

public class CuisinesListObject {
    @SerializedName("Cuisine_Id")
    private Integer cuisineId;
    @SerializedName("Cuisine_Name")
    private String cuisineName;
    @SerializedName("Resturant_Count")
    private Integer resturantCount;

    boolean isSelected;

    public CuisinesListObject(String cuisineName) {
        this.cuisineName = cuisineName;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public Integer getCuisineId() {
        return cuisineId;
    }

    public void setCuisineId(Integer cuisineId) {
        this.cuisineId = cuisineId;
    }

    public String getCuisineName() {
        return cuisineName;
    }

    public void setCuisineName(String cuisineName) {
        this.cuisineName = cuisineName;
    }

    public Integer getResturantCount() {
        return resturantCount;
    }

    public void setResturantCount(Integer resturantCount) {
        this.resturantCount = resturantCount;
    }
}

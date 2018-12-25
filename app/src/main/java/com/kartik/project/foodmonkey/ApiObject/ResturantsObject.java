package com.kartik.project.foodmonkey.ApiObject;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by kartikeya on 14/10/2018.
 */

public class ResturantsObject  {
    @SerializedName("Restaurant_Details")
    private ArrayList<ResturantsDetailObject> restaurantDetails = new ArrayList<>();
    @SerializedName("Menu_Category")
    private ArrayList<MenuDetailCategoryObject> menuCategory = new ArrayList<>();
    @SerializedName("Delivery_Postcodes")
    private ArrayList<String> deliveryPostCode = new ArrayList<>();

    public ArrayList<ResturantsDetailObject> getRestaurantDetails() {
        return restaurantDetails;
    }

    public void setRestaurantDetails(ArrayList<ResturantsDetailObject> restaurantDetails) {
        this.restaurantDetails = restaurantDetails;
    }

    public ArrayList<MenuDetailCategoryObject> getMenuCategory() {
        return menuCategory;
    }

    public void setMenuCategory(ArrayList<MenuDetailCategoryObject> menuCategory) {
        this.menuCategory = menuCategory;
    }

    public ArrayList<String> getDeliveryPostCode() {
        return deliveryPostCode;
    }

    public void setDeliveryPostCode(ArrayList<String> deliveryPostCode) {
        this.deliveryPostCode = deliveryPostCode;
    }
}
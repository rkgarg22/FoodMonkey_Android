package com.kartik.project.foodmonkey.ApiObject;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by kartikeya on 10/10/2018.
 */

public class HomeRestutantObject {


    @SerializedName("Popular_Restaurants")
    private ArrayList<HomePopularObject> popularRestaurants = null;

    @SerializedName("Viewed_Restaurants")
    private ArrayList<HomeViewedObject> viewedRestaurants = null;

    @SerializedName("Ordered_Restaurants")
    private ArrayList<HomeOrderedObject> orderedRestaurants = null;

    public ArrayList<HomePopularObject> getPopularRestaurants() {
        return popularRestaurants;
    }

    public void setPopularRestaurants(ArrayList<HomePopularObject> popularRestaurants) {
        this.popularRestaurants = popularRestaurants;
    }

    public ArrayList<HomeViewedObject> getViewedRestaurants() {
        return viewedRestaurants;
    }

    public void setViewedRestaurants(ArrayList<HomeViewedObject> viewedRestaurants) {
        this.viewedRestaurants = viewedRestaurants;
    }

    public ArrayList<HomeOrderedObject> getOrderedRestaurants() {
        return orderedRestaurants;
    }

    public void setOrderedRestaurants(ArrayList<HomeOrderedObject> orderedRestaurants) {
        this.orderedRestaurants = orderedRestaurants;
    }

}

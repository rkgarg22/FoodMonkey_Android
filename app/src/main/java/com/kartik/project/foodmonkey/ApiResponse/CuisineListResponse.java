package com.kartik.project.foodmonkey.ApiResponse;

import com.google.gson.annotations.SerializedName;
import com.kartik.project.foodmonkey.ApiObject.CuisinesListObject;

import java.util.ArrayList;

/**
 * Created by kartikeya on 11/10/2018.
 */

public class CuisineListResponse
{
    @SerializedName("Code")
    private String code;
    @SerializedName("Message")
    private String message;
    @SerializedName("Cuisines_List")
    private ArrayList<CuisinesListObject> cuisinesList = null;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<CuisinesListObject> getCuisinesList() {
        return cuisinesList;
    }

    public void setCuisinesList(ArrayList<CuisinesListObject> cuisinesList) {
        this.cuisinesList = cuisinesList;
    }
}

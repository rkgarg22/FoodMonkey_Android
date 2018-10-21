package com.kartik.project.foodmonkey.ApiResponse;

import com.google.gson.annotations.SerializedName;
import com.kartik.project.foodmonkey.ApiObject.ResturantsDetailObject;
import com.kartik.project.foodmonkey.ApiObject.ResturantsObject;

/**
 * Created by kartikeya on 14/10/2018.
 */

public class RestDetailMenuResponse
{
    @SerializedName("Code")
    private String code;
    @SerializedName("Message")
    private String message;
    @SerializedName("Resturants")
    private ResturantsObject resturants;

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

    public ResturantsObject getResturants() {
        return resturants;
    }

    public void setResturants(ResturantsObject resturants) {
        this.resturants = resturants;
    }

}
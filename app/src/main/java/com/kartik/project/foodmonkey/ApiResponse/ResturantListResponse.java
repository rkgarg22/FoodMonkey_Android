package com.kartik.project.foodmonkey.ApiResponse;

import com.google.gson.annotations.SerializedName;
import com.kartik.project.foodmonkey.ApiObject.RestutantListObject;

import java.util.ArrayList;

/**
 * Created by kartikeya on 02/10/2018.
 */

public class ResturantListResponse
{
    @SerializedName("Code")
    private String code;
    @SerializedName("Message")
    private String message;
    @SerializedName("Restutant_list")
    private ArrayList<RestutantListObject> restutantList = null;

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

    public ArrayList<RestutantListObject> getRestutantList() {
        return restutantList;
    }

    public void setRestutantList(ArrayList<RestutantListObject> restutantList) {
        this.restutantList = restutantList;
    }
}

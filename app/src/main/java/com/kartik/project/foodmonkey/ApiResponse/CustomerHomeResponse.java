package com.kartik.project.foodmonkey.ApiResponse;

import com.google.gson.annotations.SerializedName;
import com.kartik.project.foodmonkey.ApiObject.HomeRestutantObject;

/**
 * Created by kartikeya on 10/10/2018.
 */

public class CustomerHomeResponse {
    @SerializedName("Code")
    private String code;

    @SerializedName("Message")
    private String message;

    @SerializedName("Restutant_list")
    private HomeRestutantObject restutantList;

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

    public HomeRestutantObject getRestutantList() {
        return restutantList;
    }

    public void setRestutantList(HomeRestutantObject restutantList) {
        this.restutantList = restutantList;
    }
}

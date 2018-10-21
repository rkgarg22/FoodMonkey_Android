package com.kartik.project.foodmonkey.ApiResponse;

import com.google.gson.annotations.SerializedName;
import com.kartik.project.foodmonkey.ApiObject.ResturantFeedbackList;

import java.util.List;

/**
 * Created by kartikeya on 18/10/2018.
 */

public class ResturantFeedBackResponse
{
    @SerializedName("Code")
    private String code;
    @SerializedName("Message")
    private String message;
    @SerializedName("Resturant_Feedback_List")
    private List<ResturantFeedbackList> resturantFeedbackList = null;

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

    public List<ResturantFeedbackList> getResturantFeedbackList() {
        return resturantFeedbackList;
    }

    public void setResturantFeedbackList(List<ResturantFeedbackList> resturantFeedbackList) {
        this.resturantFeedbackList = resturantFeedbackList;
    }
}

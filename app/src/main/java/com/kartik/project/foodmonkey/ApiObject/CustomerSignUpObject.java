package com.kartik.project.foodmonkey.ApiObject;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kartikeya on 22/10/2018.
 */

public class CustomerSignUpObject
{
    @SerializedName("Customer_id")
    private String customerId;
    @SerializedName("ProfilePic_Url")
    private String profilePicUrl;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }
}

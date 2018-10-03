package com.kartik.project.foodmonkey.ApiEntity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kartikeya on 02/10/2018.
 */

public class ResturantListEnity {
    @SerializedName("TokenKey")
    String tokenKey;

    @SerializedName("Search_By")
    String searchBy;

    @SerializedName("Post_Code")
    String postCode;

    public ResturantListEnity(String tokenKey, String searchBy, String postCode) {
        this.tokenKey = tokenKey;
        this.searchBy = searchBy;
        this.postCode = postCode;
    }

    public String getTokenKey() {
        return tokenKey;
    }

    public void setTokenKey(String tokenKey) {
        this.tokenKey = tokenKey;
    }

    public String getSearchBy() {
        return searchBy;
    }

    public void setSearchBy(String searchBy) {
        this.searchBy = searchBy;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }
}

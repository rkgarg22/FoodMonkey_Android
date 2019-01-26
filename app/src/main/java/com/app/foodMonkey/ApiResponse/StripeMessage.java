package com.app.foodMonkey.ApiResponse;

import com.google.gson.annotations.SerializedName;
import com.app.foodMonkey.ApiObject.StripeDataObject;

import java.util.List;

/**
 * Created by kartikeya on 27/11/2018.
 */

public class StripeMessage {
    @SerializedName("object")
    private String object;
    @SerializedName("data")
    private List<StripeDataObject> data = null;
    @SerializedName("has_more")
    private Boolean hasMore;
    @SerializedName("url")
    private String url;

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public List<StripeDataObject> getData() {
        return data;
    }

    public void setData(List<StripeDataObject> data) {
        this.data = data;
    }

    public Boolean getHasMore() {
        return hasMore;
    }

    public void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
}

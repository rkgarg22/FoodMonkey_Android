package com.app.foodMonkey.ApiResponse.StripeResponsePack;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kartikeya on 04/01/2019.
 */

public class RefundsObjects {
    @SerializedName("object")
    private String object;
    @SerializedName("data")
    private List<Object> data = null;
    @SerializedName("has_more")
    private Boolean hasMore;
    @SerializedName("total_count")
    private Integer totalCount;
    @SerializedName("url")
    private String url;

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }

    public Boolean getHasMore() {
        return hasMore;
    }

    public void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

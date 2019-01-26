package com.app.foodMonkey.ApiObject.BrainTreeObjects;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kartikeya on 05/01/2019.
 */

public class Options {

    @SerializedName("submitForSettlement")
    private String submitForSettlement;

    public String getSubmitForSettlement() {
        return submitForSettlement;
    }

    public void setSubmitForSettlement(String submitForSettlement) {
        this.submitForSettlement = submitForSettlement;
    }
}

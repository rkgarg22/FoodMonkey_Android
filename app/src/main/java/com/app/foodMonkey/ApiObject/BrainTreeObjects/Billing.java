package com.app.foodMonkey.ApiObject.BrainTreeObjects;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kartikeya on 05/01/2019.
 */

public class Billing {
    @SerializedName("countryCodeAlpha3")
    private String countryCodeAlpha3;

    public String getCountryCodeAlpha3() {
        return countryCodeAlpha3;
    }

    public void setCountryCodeAlpha3(String countryCodeAlpha3) {
        this.countryCodeAlpha3 = countryCodeAlpha3;
    }
}

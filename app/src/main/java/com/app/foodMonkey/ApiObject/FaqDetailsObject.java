package com.app.foodMonkey.ApiObject;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kartikeya on 23/01/2019.
 */

public class FaqDetailsObject {
    @SerializedName("FAQ")
    private List<FAQListObj> fAQ = null;

    public List<FAQListObj> getFAQ() {
        return fAQ;
    }

    public void setFAQ(List<FAQListObj> fAQ) {
        this.fAQ = fAQ;
    }
}

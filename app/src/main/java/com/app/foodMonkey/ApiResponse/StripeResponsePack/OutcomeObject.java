package com.app.foodMonkey.ApiResponse.StripeResponsePack;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kartikeya on 04/01/2019.
 */

public class OutcomeObject {
    @SerializedName("network_status")
    private String networkStatus;
    @SerializedName("reason")
    private Object reason;
    @SerializedName("risk_level")
    private String riskLevel;
    @SerializedName("risk_score")
    private Integer riskScore;
    @SerializedName("seller_message")
    private String sellerMessage;
    @SerializedName("type")
    private String type;

    public String getNetworkStatus() {
        return networkStatus;
    }

    public void setNetworkStatus(String networkStatus) {
        this.networkStatus = networkStatus;
    }

    public Object getReason() {
        return reason;
    }

    public void setReason(Object reason) {
        this.reason = reason;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public Integer getRiskScore() {
        return riskScore;
    }

    public void setRiskScore(Integer riskScore) {
        this.riskScore = riskScore;
    }

    public String getSellerMessage() {
        return sellerMessage;
    }

    public void setSellerMessage(String sellerMessage) {
        this.sellerMessage = sellerMessage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

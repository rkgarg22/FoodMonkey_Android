package com.app.foodMonkey.ApiObject.BrainTreeObjects;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kartikeya on 05/01/2019.
 */

public class BrainTreeResultObject {
    @SerializedName("errors")
    private Errors errors;
    @SerializedName("params")
    private Params params;
    @SerializedName("message")
    private String message;
    @SerializedName("creditCardVerification")
    private Object creditCardVerification;
    @SerializedName("transaction")
    private Object transaction;
    @SerializedName("subscription")
    private Object subscription;
    @SerializedName("merchantAccount")
    private Object merchantAccount;
    @SerializedName("verification")
    private Object verification;

    public Errors getErrors() {
        return errors;
    }

    public void setErrors(Errors errors) {
        this.errors = errors;
    }

    public Params getParams() {
        return params;
    }

    public void setParams(Params params) {
        this.params = params;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getCreditCardVerification() {
        return creditCardVerification;
    }

    public void setCreditCardVerification(Object creditCardVerification) {
        this.creditCardVerification = creditCardVerification;
    }

    public Object getTransaction() {
        return transaction;
    }

    public void setTransaction(Object transaction) {
        this.transaction = transaction;
    }

    public Object getSubscription() {
        return subscription;
    }

    public void setSubscription(Object subscription) {
        this.subscription = subscription;
    }

    public Object getMerchantAccount() {
        return merchantAccount;
    }

    public void setMerchantAccount(Object merchantAccount) {
        this.merchantAccount = merchantAccount;
    }

    public Object getVerification() {
        return verification;
    }

    public void setVerification(Object verification) {
        this.verification = verification;
    }
}

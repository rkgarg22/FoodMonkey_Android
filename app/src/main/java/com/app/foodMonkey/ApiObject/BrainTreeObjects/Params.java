package com.app.foodMonkey.ApiObject.BrainTreeObjects;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kartikeya on 05/01/2019.
 */

public class Params {
    @SerializedName("transaction")
    private Transaction transaction;

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

}

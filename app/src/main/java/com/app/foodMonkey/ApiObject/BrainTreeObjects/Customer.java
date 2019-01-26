package com.app.foodMonkey.ApiObject.BrainTreeObjects;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kartikeya on 05/01/2019.
 */

public class Customer {
    @SerializedName("firstName")
    private String firstName;
    @SerializedName("email")
    private String email;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

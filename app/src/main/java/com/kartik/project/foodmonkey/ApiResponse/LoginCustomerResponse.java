package com.kartik.project.foodmonkey.ApiResponse;

import com.google.gson.annotations.SerializedName;
import com.kartik.project.foodmonkey.ApiObject.CustomerDetailObject;

import java.util.List;

/**
 * Created by kartikeya on 16/10/2018.
 */

public class LoginCustomerResponse {
    @SerializedName("Code")
    private String code;
    @SerializedName("Message")
    private String message;
    @SerializedName("Customer_details")
    private List<CustomerDetailObject> customerDetails = null;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<CustomerDetailObject> getCustomerDetails() {
        return customerDetails;
    }

    public void setCustomerDetails(List<CustomerDetailObject> customerDetails) {
        this.customerDetails = customerDetails;
    }
}

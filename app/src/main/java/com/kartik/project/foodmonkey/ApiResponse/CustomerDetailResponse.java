package com.kartik.project.foodmonkey.ApiResponse;

import com.google.gson.annotations.SerializedName;
import com.kartik.project.foodmonkey.ApiObject.CustomerObject;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kartikeya on 26/11/2018.
 */

public class CustomerDetailResponse {
    @SerializedName("Code")
    private String code;
    @SerializedName("Message")
    private String message;
    @SerializedName("Customer_details")
    private List<CustomerObject> customerDetails = null;

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

    public List<CustomerObject> getCustomerDetails() {
        return customerDetails;
    }

    public void setCustomerDetails(List<CustomerObject> customerDetails) {
        this.customerDetails = customerDetails;
    }
}

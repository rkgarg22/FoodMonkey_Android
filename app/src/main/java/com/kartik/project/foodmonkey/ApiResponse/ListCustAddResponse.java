package com.kartik.project.foodmonkey.ApiResponse;

import com.google.gson.annotations.SerializedName;
import com.kartik.project.foodmonkey.ApiObject.CustomerAddressObject;

import java.util.List;

/**
 * Created by kartikeya on 26/10/2018.
 */

public class ListCustAddResponse {
    @SerializedName("Code")
    private String code;
    @SerializedName("Message")
    private String message;
    @SerializedName("Customer_addresses")
    private List<CustomerAddressObject> customerAddresses = null;

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

    public List<CustomerAddressObject> getCustomerAddresses() {
        return customerAddresses;
    }

    public void setCustomerAddresses(List<CustomerAddressObject> customerAddresses) {
        this.customerAddresses = customerAddresses;
    }
}

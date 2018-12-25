package com.kartik.project.foodmonkey.ApiResponse;

import com.google.gson.annotations.SerializedName;
import com.kartik.project.foodmonkey.ApiObject.CustomerOrdersList;

/**
 * Created by kartikeya on 21/12/2018.
 */

public class CustomerOrderListResponse {
    @SerializedName("Code")
    private String code;
    @SerializedName("Message")
    private String message;
    @SerializedName("Customer_orders")
    private CustomerOrdersList customerOrders;

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

    public CustomerOrdersList getCustomerOrders() {
        return customerOrders;
    }

    public void setCustomerOrders(CustomerOrdersList customerOrders) {
        this.customerOrders = customerOrders;
    }
}


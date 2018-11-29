package com.kartik.project.foodmonkey.ApiResponse;

import com.google.gson.annotations.SerializedName;
import com.kartik.project.foodmonkey.ApiObject.OrderStatusObject;

/**
 * Created by kartikeya on 27/11/2018.
 */

public class OrderStatusResponse {
    @SerializedName("Code")
    private String code;
    @SerializedName("Message")
    private String message;
    @SerializedName("Order_Status")
    private OrderStatusObject orderStatusObject;

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

    public OrderStatusObject getOrderStatusObject() {
        return orderStatusObject;
    }

    public void setOrderStatusObject(OrderStatusObject orderStatusObject) {
        this.orderStatusObject = orderStatusObject;
    }
}

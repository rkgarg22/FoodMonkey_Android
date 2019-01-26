package com.app.foodMonkey.ApiResponse;

import com.google.gson.annotations.SerializedName;
import com.app.foodMonkey.ApiObject.CustomerSignUpObject;

import java.util.List;

/**
 * Created by kartikeya on 02/10/2018.
 */

public class CustomerSignUpResponse {
    @SerializedName("Code")
    private String code;
    @SerializedName("Message")
    private String message;
    //    @SerializedName("Customer_detail")
//    private CustomerSignUpObject customerDetail;
    @SerializedName("Customer_detail")
    private List<CustomerSignUpObject> customerDetail = null;

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

    public List<CustomerSignUpObject> getCustomerDetail() {
        return customerDetail;
    }

    public void setCustomerDetail(List<CustomerSignUpObject> customerDetail) {
        this.customerDetail = customerDetail;
    }

    //    public CustomerSignUpObject getCustomerDetail() {
//        return customerDetail;
//    }
//
//    public void setCustomerDetail(CustomerSignUpObject customerDetail) {
//        this.customerDetail = customerDetail;
//    }

}

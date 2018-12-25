package com.kartik.project.foodmonkey.ApiObject;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kartikeya on 22/12/2018.
 */

public class CustomerOrdersList {

    @SerializedName("Order_Detail")
    private ArrayList<OrderListDetailObj> orderDetail = null;
    @SerializedName("Rejected_Orders")
    private List<Object> rejectedOrders = null;
    @SerializedName("Pre_Orders")
    private List<Object> preOrders = null;

    public ArrayList<OrderListDetailObj> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(ArrayList<OrderListDetailObj> orderDetail) {
        this.orderDetail = orderDetail;
    }

    public List<Object> getRejectedOrders() {
        return rejectedOrders;
    }

    public void setRejectedOrders(List<Object> rejectedOrders) {
        this.rejectedOrders = rejectedOrders;
    }

    public List<Object> getPreOrders() {
        return preOrders;
    }

    public void setPreOrders(List<Object> preOrders) {
        this.preOrders = preOrders;
    }
}

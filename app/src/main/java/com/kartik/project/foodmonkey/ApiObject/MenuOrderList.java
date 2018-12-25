package com.kartik.project.foodmonkey.ApiObject;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kartikeya on 22/12/2018.
 */

public class MenuOrderList implements Serializable {

    @SerializedName("Order_Id")
    private Integer orderId;
    @SerializedName("Item_id")
    private Integer itemId;
    @SerializedName("Item_Name")
    private String itemName;
    @SerializedName("Item_Price")
    private String itemPrice;
    @SerializedName("Quantity")
    private Integer quantity;
    @SerializedName("Addon_Order")
    private List<Object> addonOrder = null;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<Object> getAddonOrder() {
        return addonOrder;
    }

    public void setAddonOrder(List<Object> addonOrder) {
        this.addonOrder = addonOrder;
    }
}

package com.kartik.project.foodmonkey.Models;

import java.io.Serializable;

/**
 * Created by kartikeya on 22/10/2018.
 */

public class AddItemsToCartModel implements Serializable {
    String id;
    String restName;
    String name;
    String price;
    String quantity;
    String itemID;
    String addOnID;
    String resturantID;

    public AddItemsToCartModel(String menuId, String restName, String name, String price, String quantity, String itemID, String addOnID, String resturantID) {
        this.id = menuId;
        this.restName = restName;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.itemID = itemID;
        this.addOnID = addOnID;
        this.resturantID = resturantID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRestName() {
        return restName;
    }

    public void setRestName(String restName) {
        this.restName = restName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getAddOnID() {
        return addOnID;
    }

    public void setAddOnID(String addOnID) {
        this.addOnID = addOnID;
    }

    public String getResturantID() {
        return resturantID;
    }

    public void setResturantID(String resturantID) {
        this.resturantID = resturantID;
    }
}

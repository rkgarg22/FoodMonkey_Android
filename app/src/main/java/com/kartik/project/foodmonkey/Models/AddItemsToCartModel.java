package com.kartik.project.foodmonkey.Models;

/**
 * Created by kartikeya on 22/10/2018.
 */

public class AddItemsToCartModel {
    String id;
    String restName;
    String name;
    String price;
    String quantity;

    public AddItemsToCartModel(String id, String restName, String name, String price, String quantity) {
        this.id = id;
        this.restName = restName;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
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
}

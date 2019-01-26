package com.app.foodMonkey.Models;

/**
 * Created by kartikeya on 30/09/2018.
 */

public class EditCartModel {
    String itemTitle;
    String perItemPrice;
    String noOfItems;

    public EditCartModel(String itemTitle, String perItemPrice, String noOfItems) {
        this.itemTitle = itemTitle;
        this.perItemPrice = perItemPrice;
        this.noOfItems = noOfItems;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getPerItemPrice() {
        return perItemPrice;
    }

    public void setPerItemPrice(String perItemPrice) {
        this.perItemPrice = perItemPrice;
    }

    public String getNoOfItems() {
        return noOfItems;
    }

    public void setNoOfItems(String noOfItems) {
        this.noOfItems = noOfItems;
    }
}

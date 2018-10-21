package com.kartik.project.foodmonkey.Models;

/**
 * Created by kartikeya on 15/10/2018.
 */

public class ChildAndAddonModel {
    int itemID;
    int restID;
    int addOnSize;
    String itemName;
    String itemPrice;
    String itemDesc;
    int itemStatus;
    int isNonVeg;

    public ChildAndAddonModel(int itemID, int restID, int addOnSize, String itemName, String itemPrice,
                              String itemDesc, int itemStatus, int isNonVeg) {
        this.itemID = itemID;
        this.restID = restID;
        this.addOnSize = addOnSize;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemDesc = itemDesc;
        this.itemStatus = itemStatus;
        this.isNonVeg = isNonVeg;
    }


    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getRestID() {
        return restID;
    }

    public void setRestID(int restID) {
        this.restID = restID;
    }

    public int getAddOnSize() {
        return addOnSize;
    }

    public void setAddOnSize(int addOnSize) {
        this.addOnSize = addOnSize;
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

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public int getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(int itemStatus) {
        this.itemStatus = itemStatus;
    }

    public int getIsNonVeg() {
        return isNonVeg;
    }

    public void setIsNonVeg(int isNonVeg) {
        this.isNonVeg = isNonVeg;
    }
}

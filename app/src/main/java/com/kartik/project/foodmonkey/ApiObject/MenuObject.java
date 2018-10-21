package com.kartik.project.foodmonkey.ApiObject;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by kartikeya on 14/10/2018.
 */

public class MenuObject implements Serializable {
    @SerializedName("Item_id")
    private Integer itemId;
    @SerializedName("Rest_id")
    private Integer restId;
    @SerializedName("Menu_category_Id")
    private Integer menuCategoryId;
    @SerializedName("Item_Name")
    private String itemName;
    @SerializedName("Item_Price")
    private String itemPrice;
    @SerializedName("Item_Desc")
    private String itemDesc;
    @SerializedName("Item_Status")
    private Integer itemStatus;
    @SerializedName("IsItemNonVeg")
    private Integer isItemNonVeg;
    @SerializedName("AddOn")
    private ArrayList<MenuAddOnObject> addOn = new ArrayList<>();

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getRestId() {
        return restId;
    }

    public void setRestId(Integer restId) {
        this.restId = restId;
    }

    public Integer getMenuCategoryId() {
        return menuCategoryId;
    }

    public void setMenuCategoryId(Integer menuCategoryId) {
        this.menuCategoryId = menuCategoryId;
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

    public Integer getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(Integer itemStatus) {
        this.itemStatus = itemStatus;
    }

    public Integer getIsItemNonVeg() {
        return isItemNonVeg;
    }

    public void setIsItemNonVeg(Integer isItemNonVeg) {
        this.isItemNonVeg = isItemNonVeg;
    }

    public ArrayList<MenuAddOnObject> getAddOn() {
        return addOn;
    }

    public void setAddOn(ArrayList<MenuAddOnObject> addOn) {
        this.addOn = addOn;
    }

}

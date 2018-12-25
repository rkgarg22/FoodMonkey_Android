package com.kartik.project.foodmonkey.ApiObject;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by kartikeya on 14/10/2018.
 */

public class MenuDetailCategoryObject implements Serializable{
    @SerializedName("Menu_Category_Id")
    private Integer menuCategoryId;
    @SerializedName("Menu_Category_Name")
    private String menuCategoryName;
    @SerializedName("Menu_Cat_Info")
    private String menuCatInfo;
    @SerializedName("Menus")
    private ArrayList<MenuObject> menus = new ArrayList<>();

    boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public Integer getMenuCategoryId() {
        return menuCategoryId;
    }

    public void setMenuCategoryId(Integer menuCategoryId) {
        this.menuCategoryId = menuCategoryId;
    }

    public String getMenuCategoryName() {
        return menuCategoryName;
    }

    public void setMenuCategoryName(String menuCategoryName) {
        this.menuCategoryName = menuCategoryName;
    }

    public ArrayList<MenuObject> getMenus() {
        return menus;
    }

    public void setMenus(ArrayList<MenuObject> menus) {
        this.menus = menus;
    }
}

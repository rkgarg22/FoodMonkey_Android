package com.app.foodMonkey.ApiObject;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by kartikeya on 28/12/2018.
 */

public class MenuSubCategoryObject implements Serializable{

    @SerializedName("Menu_Sub_Cate_Id")
    private Integer menuSubCateId;
    @SerializedName("Menu_Cate_Id")
    private Integer menuCateId;
    @SerializedName("Menu_Sub_Cate_Name")
    private String menuSubCateName;
    @SerializedName("SubCate_Info")
    private String subCateInfo;
    @SerializedName("Menus")
    private ArrayList<MenuObject> menus = new ArrayList<>();

    boolean isSelected2;

    public boolean isSelected2() {
        return isSelected2;
    }

    public void setSelected2(boolean selected2) {
        isSelected2 = selected2;
    }

    public Integer getMenuSubCateId() {
        return menuSubCateId;
    }

    public void setMenuSubCateId(Integer menuSubCateId) {
        this.menuSubCateId = menuSubCateId;
    }

    public Integer getMenuCateId() {
        return menuCateId;
    }

    public void setMenuCateId(Integer menuCateId) {
        this.menuCateId = menuCateId;
    }

    public String getMenuSubCateName() {
        return menuSubCateName;
    }

    public void setMenuSubCateName(String menuSubCateName) {
        this.menuSubCateName = menuSubCateName;
    }

    public String getSubCateInfo() {
        return subCateInfo;
    }

    public void setSubCateInfo(String subCateInfo) {
        this.subCateInfo = subCateInfo;
    }

    public ArrayList<MenuObject> getMenus() {
        return menus;
    }

    public void setMenus(ArrayList<MenuObject> menus) {
        this.menus = menus;
    }

}

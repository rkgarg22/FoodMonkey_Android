package com.kartik.project.foodmonkey.ApiObject;

import com.google.gson.annotations.SerializedName;
import com.kartik.project.foodmonkey.ApiResponse.CloseResturantObject;
import com.kartik.project.foodmonkey.ApiResponse.OpenResturantObject;

import java.util.List;

/**
 * Created by kartikeya on 02/10/2018.
 */

public class RestutantListObject {

    @SerializedName("open_resturant")
    private List<OpenResturantObject> openResturant = null;
    @SerializedName("preorder_resturant")
    private List<PreOrderRestObject> preorderResturant = null;
    @SerializedName("close_resturant")
    private List<CloseResturantObject> closeResturant = null;

    public List<OpenResturantObject> getOpenResturant() {
        return openResturant;
    }

    public void setOpenResturant(List<OpenResturantObject> openResturant) {
        this.openResturant = openResturant;
    }

    public List<PreOrderRestObject> getPreorderResturant() {
        return preorderResturant;
    }

    public void setPreorderResturant(List<PreOrderRestObject> preorderResturant) {
        this.preorderResturant = preorderResturant;
    }

    public List<CloseResturantObject> getCloseResturant() {
        return closeResturant;
    }

    public void setCloseResturant(List<CloseResturantObject> closeResturant) {
        this.closeResturant = closeResturant;
    }

}

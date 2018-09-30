package com.kartik.project.foodmonkey.Models;

/**
 * Created by kartikeya on 01/10/2018.
 */

public class AddAddressModel {
    String addressTitle;
    String addressFull;

    boolean isSelected;

    public AddAddressModel(String addressTitle, String addressFull) {
        this.addressTitle = addressTitle;
        this.addressFull = addressFull;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getAddressTitle() {
        return addressTitle;
    }

    public void setAddressTitle(String addressTitle) {
        this.addressTitle = addressTitle;
    }

    public String getAddressFull() {
        return addressFull;
    }

    public void setAddressFull(String addressFull) {
        this.addressFull = addressFull;
    }
}

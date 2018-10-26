package com.kartik.project.foodmonkey.Models;

/**
 * Created by kartikeya on 29/09/2018.
 */

public class InfoModel {
    String itemId;
    String restId;
    String title;
    String description;
    String noOfItem;

    public InfoModel(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public InfoModel(String itemId, String restId, String title, String description, String noOfItem) {
        this.itemId = itemId;
        this.restId = restId;
        this.title = title;
        this.description = description;
        this.noOfItem = noOfItem;
    }

    public String getNoOfItem() {
        return noOfItem;
    }

    public void setNoOfItem(String noOfItem) {
        this.noOfItem = noOfItem;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getRestId() {
        return restId;
    }

    public void setRestId(String restId) {
        this.restId = restId;
    }
}

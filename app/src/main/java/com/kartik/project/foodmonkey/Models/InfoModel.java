package com.kartik.project.foodmonkey.Models;

/**
 * Created by kartikeya on 29/09/2018.
 */

public class InfoModel {
    String title;
    String description;

    public InfoModel(String title, String description) {
        this.title = title;
        this.description = description;
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
}

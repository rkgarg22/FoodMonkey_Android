package com.app.foodMonkey.Models;

/**
 * Created by kartikeya on 23/09/2018.
 */

public class NavigationModel {
    int icons;
    String title;

    public NavigationModel(int icons, String title) {
        this.icons = icons;
        this.title = title;
    }

    public int getIcons() {
        return icons;
    }

    public void setIcons(int icons) {
        this.icons = icons;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

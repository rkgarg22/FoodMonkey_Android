package com.kartik.project.foodmonkey.Models;

/**
 * Created by kartikeya on 15/10/2018.
 */

public class HeaderDataModel {
    int headerID;
    String headerName;

    public HeaderDataModel(int headerID, String headerName) {
        this.headerID = headerID;
        this.headerName = headerName;
    }

    public int getHeaderID() {
        return headerID;
    }

    public void setHeaderID(int headerID) {
        this.headerID = headerID;
    }

    public String getHeaderName() {
        return headerName;
    }

    public void setHeaderName(String headerName) {
        this.headerName = headerName;
    }
}

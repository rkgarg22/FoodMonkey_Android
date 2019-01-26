package com.app.foodMonkey.ApiObject;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kartikeya on 23/01/2019.
 */

public class FAQListObj {

    @SerializedName("FAQCategoryId")
    private Integer fAQCategoryId;
    @SerializedName("FAQCategoryName")
    private String fAQCategoryName;
    @SerializedName("FAQ_Question")
    private List<FAQQuestionObject> fAQQuestion = null;

    boolean isSelected;

    public Integer getfAQCategoryId() {
        return fAQCategoryId;
    }

    public void setfAQCategoryId(Integer fAQCategoryId) {
        this.fAQCategoryId = fAQCategoryId;
    }

    public String getfAQCategoryName() {
        return fAQCategoryName;
    }

    public void setfAQCategoryName(String fAQCategoryName) {
        this.fAQCategoryName = fAQCategoryName;
    }

    public List<FAQQuestionObject> getfAQQuestion() {
        return fAQQuestion;
    }

    public void setfAQQuestion(List<FAQQuestionObject> fAQQuestion) {
        this.fAQQuestion = fAQQuestion;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}

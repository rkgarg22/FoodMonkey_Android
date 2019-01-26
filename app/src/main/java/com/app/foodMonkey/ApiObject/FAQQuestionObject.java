package com.app.foodMonkey.ApiObject;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kartikeya on 23/01/2019.
 */

public class FAQQuestionObject {
    @SerializedName("RestOwnerFAQId")
    private Integer restOwnerFAQId;
    @SerializedName("FAQCategoryId")
    private Integer fAQCategoryId;
    @SerializedName("Question")
    private String question;
    @SerializedName("Answer")
    private String answer;

    public Integer getRestOwnerFAQId() {
        return restOwnerFAQId;
    }

    public void setRestOwnerFAQId(Integer restOwnerFAQId) {
        this.restOwnerFAQId = restOwnerFAQId;
    }

    public Integer getfAQCategoryId() {
        return fAQCategoryId;
    }

    public void setfAQCategoryId(Integer fAQCategoryId) {
        this.fAQCategoryId = fAQCategoryId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}

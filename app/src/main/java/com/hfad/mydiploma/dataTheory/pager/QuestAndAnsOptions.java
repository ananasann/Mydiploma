package com.hfad.mydiploma.dataTheory.pager;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuestAndAnsOptions {

    @SerializedName("textQuiz")
    String textQuiz;

    @SerializedName("ansOptions")
    List<String> ansOptions;

    @SerializedName("corrAnsNum")
    Integer corrAnsNum;

    public QuestAndAnsOptions(String textQuiz, List ansOptions, Integer corrAnsNum) {
        this.textQuiz = textQuiz;
        this.ansOptions = ansOptions;
        this.corrAnsNum = corrAnsNum;

    }

    public String getTextQuiz() {
        return textQuiz;
    }

    public List getAnsOptions() {
        return ansOptions;
    }

    public Integer getCorrAnsNum() {
        return corrAnsNum;
    }
}
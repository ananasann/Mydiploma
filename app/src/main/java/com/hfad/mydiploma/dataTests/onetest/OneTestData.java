package com.hfad.mydiploma.dataTests.onetest;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OneTestData {
    @SerializedName("questTest")
    String questTest;

    @SerializedName("ansOpTest")
    String ansOpTest;

    @SerializedName("corrAnsTest")
    Integer corrAnsTest;

    public OneTestData(String quesTest, String ansOpTest, Integer corrAnsTest) {
        this.questTest = quesTest;
        this.ansOpTest = ansOpTest;
        this.corrAnsTest = corrAnsTest;
    }

    public String getQuesTest() {
        return questTest;
    }
    public String getAnsOpTest() {
        return ansOpTest; }

    public Integer getCorrAnsTest() {
        return corrAnsTest; }
}


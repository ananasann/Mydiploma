package com.hfad.mydiploma.dataTests;

import com.google.gson.annotations.SerializedName;
import com.hfad.mydiploma.dataTheory.pager.CardData;

import java.util.List;

public class TestsCard {

    @SerializedName("titleTest")
    String titleTest;

    @SerializedName("itemNameTest")
    String itemNameTest;

    public TestsCard(String titleTest, String itemNameTest) {
        this.titleTest = titleTest;
        this.itemNameTest = itemNameTest;
    }

    public String getTitleTest() {
        return titleTest;
    }
    public String getItemNameTest() { return itemNameTest; }
}
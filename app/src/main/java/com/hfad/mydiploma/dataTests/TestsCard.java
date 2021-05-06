package com.hfad.mydiploma.dataTests;

import com.google.gson.annotations.SerializedName;
import com.hfad.mydiploma.dataTests.onetest.OneTestData;

import java.util.List;

public class TestsCard {

    @SerializedName("titleTest")
    String titleTest;

    @SerializedName("itemNameTest")
    String itemNameTest;

    @SerializedName("oneTestData")
    List<OneTestData> testDataList;

    public TestsCard(String titleTest, String itemNameTest,List<OneTestData> testDataList ) {
        this.titleTest = titleTest;
        this.itemNameTest = itemNameTest;
        this.testDataList = testDataList;
    }

    public String getTitleTest() {
        return titleTest;
    }
    public String getItemNameTest() { return itemNameTest; }

    public List<OneTestData> getTestDataListData() {
        return testDataList;
    }
}
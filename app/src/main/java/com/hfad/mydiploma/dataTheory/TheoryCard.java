package com.hfad.mydiploma.dataTheory;

import com.google.gson.annotations.SerializedName;
import com.hfad.mydiploma.dataTheory.pager.CardData;

import java.util.List;

public class TheoryCard {

    @SerializedName("title")
    String title;

    @SerializedName("itemName")
    String itemName;

    @SerializedName("cardData")
    List<CardData> cardDataList;

    public TheoryCard(String title, String itemName, List<CardData> listOfCardData) {
        this.title = title;
        this.itemName = itemName;
        this. cardDataList = cardDataList;
    }

    public String getTitle() {
        return title;
    }
    public String getItemName() { return itemName; }
    public List<CardData> getCardDataList() {
        return cardDataList;
    }
}

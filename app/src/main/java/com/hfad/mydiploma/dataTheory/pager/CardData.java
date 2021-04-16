package com.hfad.mydiploma.dataTheory.pager;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CardData {

    @SerializedName("imageAndDiscr")
    List<ImageAndDescription> imageAndDescriptionList;

    @SerializedName("qAndAnsOp")
    List<QuestAndAnsOptions> questAndAnsOptionsList;

    public CardData(List<ImageAndDescription> imageAndDescriptionList, List<QuestAndAnsOptions> questAndAnsOptionsList) {
        this.imageAndDescriptionList = imageAndDescriptionList;
        this.questAndAnsOptionsList = questAndAnsOptionsList;
    }

    public List<ImageAndDescription> getImageAndDescriptionList() {
        return imageAndDescriptionList;
    }

    public List<QuestAndAnsOptions> getQuestAndAnsOptionsList() {
        return questAndAnsOptionsList;
    }
}

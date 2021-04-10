package com.hfad.mydiploma.dataTheory;

import com.google.gson.annotations.SerializedName;

public class Theory {
    @SerializedName("title")
    String title;
    @SerializedName("itemName")
    String itemName;

    public Theory (String title, String itemName) {
        this.title = title;
        this.itemName = itemName;
    }

    public String getTitle() {
        return title;
    }
    public String getItemName() {
        return itemName;
    }

}

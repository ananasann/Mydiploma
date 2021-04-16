package com.hfad.mydiploma.dataTheory.pager;

import com.google.gson.annotations.SerializedName;

public class ImageAndDescription {

    @SerializedName("image")
    String imageUrl;

    @SerializedName("description")
    String textTheor;

    public ImageAndDescription(String textTheor, String imageUrl) {
        this.textTheor = textTheor;
        this.imageUrl = imageUrl;
    }

    public String getTextTheor() {
        return textTheor;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}

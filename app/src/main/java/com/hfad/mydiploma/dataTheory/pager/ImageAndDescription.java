package com.hfad.mydiploma.dataTheory.pager;

import com.google.gson.annotations.SerializedName;

public class ImageAndDescription {

    @SerializedName("image")
    String imageUrl;

    @SerializedName("description")
    String textTheor;

    public ImageAndDescription(String textTheor, String imageUrl) {
        this.imageUrl = imageUrl;
        this.textTheor = textTheor;

    }

    public String getImageUrl() {
        return imageUrl;
    }
    public String getTextTheor() {
        return textTheor;
    }

}

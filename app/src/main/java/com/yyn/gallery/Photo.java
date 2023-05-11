package com.yyn.gallery;

import com.google.gson.annotations.SerializedName;

public class Photo {

    @SerializedName("url_s")
    private String imageUrl;

    public Photo(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}

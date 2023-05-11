package com.yyn.gallery;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class RecentImagesResponse {

    @SerializedName("photos")
    private PhotosContainer photosContainer;

    public List<Photo> getPhotos() {
        return photosContainer != null ? photosContainer.getPhotos() : null;
    }

    private static class PhotosContainer {

        @SerializedName("photo")
        private List<Photo> photos;

        public List<Photo> getPhotos() {
            return photos;
        }
    }
}

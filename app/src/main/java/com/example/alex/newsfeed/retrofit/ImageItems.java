package com.example.alex.newsfeed.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageItems {

    @SerializedName("Photo")
    @Expose
    private String photo;
    @SerializedName("Thumb")
    @Expose
    private String thumb;
    @SerializedName("PhotoCaption")
    @Expose
    private String photoCaption;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getPhotoCaption() {
        return photoCaption;
    }

    public void setPhotoCaption(String photoCaption) {
        this.photoCaption = photoCaption;
    }
}

package com.appteamnith.hillffair.modals;

/**
 * Created by parvesh_dhull on 3/10/16.
 */

import com.google.gson.annotations.SerializedName;

public class newsfeed_model2 {
    @SerializedName("_id")
    private String _id;
    @SerializedName("title")
    private String title;
    @SerializedName("photo")
    private String photo;
    @SerializedName("description")
    private String description;
    @SerializedName("user")
    public user_model user;

    public newsfeed_model2(String _id, String title, String photo, String description, user_model user) {
        this._id = _id;
        this.title = title;
        this.photo = photo;
        this.description = description;
        this.user = user;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public user_model getUser() {
        return user;
    }

    public void setUser(user_model user) {
        this.user = user;
    }
}

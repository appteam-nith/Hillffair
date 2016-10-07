package com.appteamnith.hillffair.models;

/**
 * Created by parvesh_dhull on 3/10/16.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class newsfeed_model2 implements Parcelable {
    @SerializedName("_id")
    private String _id;
    @SerializedName("title")
    private String title;
    @SerializedName("photo")
    private String photo;
    @SerializedName("description")
    private String description;

    @SerializedName("uId")
    private String userid;

    @SerializedName("uName")
    private String username;


    public newsfeed_model2(String _id, String title, String photo, String description, String userid, String username) {
        this._id = _id;
        this.title = title;
        this.photo = photo;
        this.description = description;
        this.userid = userid;
        this.username = username;
    }

    protected newsfeed_model2(Parcel in) {
        _id = in.readString();
        title = in.readString();
        photo = in.readString();
        description = in.readString();
        userid = in.readString();
        username = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_id);
        dest.writeString(title);
        dest.writeString(photo);
        dest.writeString(description);
        dest.writeString(userid);
        dest.writeString(username);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<newsfeed_model2> CREATOR = new Creator<newsfeed_model2>() {
        @Override
        public newsfeed_model2 createFromParcel(Parcel in) {
            return new newsfeed_model2(in);
        }

        @Override
        public newsfeed_model2[] newArray(int size) {
            return new newsfeed_model2[size];
        }
    };

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

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

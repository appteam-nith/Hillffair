package com.appteamnith.hillffair.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by parvesh_dhull on 2/10/16.
 */

public class NewsfeedModel {
    @SerializedName("feed")
    private ArrayList<NewsfeedModel2> feed;
    @SerializedName("success")
    private boolean success;
    @SerializedName("error")
    private String error;

    public NewsfeedModel(ArrayList<NewsfeedModel2> feed, boolean success, String error) {
        this.feed = feed;
        this.success = success;
        this.error = error;
    }

    public ArrayList<NewsfeedModel2> getFeed() {
        return feed;
    }

    public void setFeed(ArrayList<NewsfeedModel2> feed) {
        this.feed = feed;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}

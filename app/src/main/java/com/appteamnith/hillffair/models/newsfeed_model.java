package com.appteamnith.hillffair.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by parvesh_dhull on 2/10/16.
 */

public class newsfeed_model {
    @SerializedName("feed")
    private ArrayList<newsfeed_model2> feed;
    @SerializedName("success")
    private boolean success;
    @SerializedName("error")
    private String error;

    public newsfeed_model(ArrayList<newsfeed_model2> feed, boolean success, String error) {
        this.feed = feed;
        this.success = success;
        this.error = error;
    }

    public ArrayList<newsfeed_model2> getFeed() {
        return feed;
    }

    public void setFeed(ArrayList<newsfeed_model2> feed) {
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

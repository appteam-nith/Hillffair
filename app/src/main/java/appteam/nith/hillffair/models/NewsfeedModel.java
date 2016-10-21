package appteam.nith.hillffair.models;

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
    @SerializedName("msg")
    private String msg;

    public NewsfeedModel(ArrayList<NewsfeedModel2> feed, boolean success, String msg) {
        this.feed = feed;
        this.success = success;
        this.msg = msg;
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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

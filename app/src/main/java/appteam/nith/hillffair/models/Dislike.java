package appteam.nith.hillffair.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jatin on 10/18/2016.
 */

public class Dislike {

    @SerializedName("likes")
    int likes;

    @SerializedName("success")
    boolean success;

    @SerializedName("msg")
    String msg;

    public Dislike(int likes,boolean success,String msg)
    {
        this.likes=likes;
        this.success=success;
        this.msg=msg;
    }

    public int getDisLikes(){
        return likes;
    }

    public void setLikes(int like){ this.likes=like; }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg(){

        return  msg;
    }

    public void setMsg(String msg) {
        this.msg=msg;
    }

}

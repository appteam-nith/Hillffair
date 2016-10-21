package appteam.nith.hillffair.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jatin on 9/11/2016.
 */
public class Login {

    @SerializedName("id")
    String id;

    @SerializedName("success")
    public boolean success;

    @SerializedName("msg")
    public  String msg;

    public Login(String id,boolean success, String msg) {
        this.success = success;
        this.id=id;
        this.msg = msg;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

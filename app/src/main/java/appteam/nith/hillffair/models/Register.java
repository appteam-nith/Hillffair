package appteam.nith.hillffair.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lenovo on 9/30/2016.
 */
public class Register {
    @SerializedName("id")
    private String id;
    @SerializedName("success")
    private boolean success;
    @SerializedName("msg")
    private String msg;

    public Register(String id, boolean success, String error) {
        this.id = id;
        this.success = success;
        this.msg = error;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

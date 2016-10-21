package appteam.nith.hillffair.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jatin on 10/15/2016.
 */

public class SendPassword {

    @SerializedName("id")
    public String id;

    @SerializedName("msg")
    public String msg;

    @SerializedName("success")
    public Boolean success;

    public void SendPassword(String id,String msg,Boolean Success)
    {
        this.id=id;
        this.msg=msg;
        this.success=Success;
    }

    public boolean isSuccess() {
        return success;
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

}

package appteam.nith.hillffair.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by sukhbir on 19/10/16.
 */

public class BattleDayModel {

    @SerializedName("profile")
    ArrayList<BattleDayItem> events;
    @SerializedName("success")
    String success;
    @SerializedName("msg")
    String msg;

    public ArrayList<BattleDayItem> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<BattleDayItem> events) {
        this.events = events;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

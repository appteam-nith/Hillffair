package appteam.nith.hillffair.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sukhbir on 20/10/16.
 */

public class BattleEventResponse {

    @SerializedName("name")
    private String eventname;

    @SerializedName("description")
    private String eventdescription;

    @SerializedName("rules")
    private String rules;

    @SerializedName("photo")
    private String photo;

    @SerializedName("date")
    private String date;

    public BattleEventResponse(String eventname, String eventdescription, String rules, String photo, String date) {
        this.eventname = eventname;
        this.eventdescription = eventdescription;
        this.rules = rules;
        this.photo = photo;
        this.date = date;
    }

    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public String getEventdescription() {
        return eventdescription;
    }

    public void setEventdescription(String eventdescription) {
        this.eventdescription = eventdescription;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

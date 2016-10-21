package appteam.nith.hillffair.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jaykay12 on 5/10/16.
 */
public class BattleDayItem {

    @SerializedName("name")
    public String name;
    @SerializedName("photo")
    public String photo;
    @SerializedName("id")
    public String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

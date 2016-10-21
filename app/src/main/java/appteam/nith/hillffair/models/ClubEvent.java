

package appteam.nith.hillffair.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Akatsuki on 3/23/2016.
 */
public class ClubEvent {
    @SerializedName("photo")
    private String image_id;
    @SerializedName("name")
    private String name;


    public ClubEvent(String image_id, String name) {
        this.image_id = image_id;
        this.name = name;
    }

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

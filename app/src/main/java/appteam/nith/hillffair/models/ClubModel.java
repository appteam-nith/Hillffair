package appteam.nith.hillffair.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by parvesh_dhull on 5/10/16.
 */


public class ClubModel {
    @SerializedName("_id")
    private String _id;
    @SerializedName("name")
    private String name;
    @SerializedName("photo")
    private String photo;
    @SerializedName("description")
    private String description;


    public ClubModel(String photo, String _id, String name, String description) {
        this.photo = photo;
        this._id = _id;
        this.name = name;
        this.description = description;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

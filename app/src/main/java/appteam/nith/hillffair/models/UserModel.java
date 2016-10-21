package appteam.nith.hillffair.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by parvesh_dhull on 3/10/16.
 */

public class UserModel {
    @SerializedName("_id")
    private String _id;
    @SerializedName("name")
    private String name;

    public UserModel(String _id, String name) {
        this._id = _id;
        this.name = name;
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
}

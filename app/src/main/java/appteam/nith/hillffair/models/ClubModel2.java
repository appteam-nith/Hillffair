package appteam.nith.hillffair.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by parvesh_dhull on 5/10/16.
 */


public class ClubModel2 {
        @SerializedName("profile")
        private ClubModel profile;
        @SerializedName("success")
        private boolean success;
        @SerializedName("error")
        private String error;


    public ClubModel2(ClubModel profile, boolean success, String error) {
        this.profile = profile;
        this.success = success;
        this.error = error;
    }


    public ClubModel getProfile() {
        return profile;
    }

    public void setProfile(ClubModel profile) {
        this.profile = profile;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}

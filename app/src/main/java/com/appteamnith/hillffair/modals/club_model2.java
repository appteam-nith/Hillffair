package com.appteamnith.hillffair.modals;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by parvesh_dhull on 5/10/16.
 */

public class club_model2 {
        @SerializedName("profile")
        private club_model profile;
        @SerializedName("success")
        private boolean success;
        @SerializedName("error")
        private String error;

    public club_model2(club_model profile, boolean success, String error) {
        this.profile = profile;
        this.success = success;
        this.error = error;
    }

    public club_model getProfile() {
        return profile;
    }

    public void setProfile(club_model profile) {
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

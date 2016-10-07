package com.appteamnith.hillffair.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by parvesh_dhull on 5/10/16.
 */

public class Club_model2 {
        @SerializedName("profile")
        private Club_model profile;
        @SerializedName("success")
        private boolean success;
        @SerializedName("error")
        private String error;

    public Club_model2(Club_model profile, boolean success, String error) {
        this.profile = profile;
        this.success = success;
        this.error = error;
    }

    public Club_model getProfile() {
        return profile;
    }

    public void setProfile(Club_model profile) {
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

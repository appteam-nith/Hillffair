package com.appteamnith.hillffair.models;

import com.appteamnith.hillffair.fragments.ProfileTab2;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Aditya on 10/5/2016.
 */
public class ProfileDataModel {

    @SerializedName("profile")
    private ProfileTab2.ProfileBasicDetailModel profileInfo;

    @SerializedName("success")
    private boolean success;

    @SerializedName("error")
    private String error;

    public ProfileDataModel(ProfileTab2.ProfileBasicDetailModel profileInfo, boolean success, String error) {
        this.profileInfo = profileInfo;
        this.success = success;
        this.error = error;
    }

    public ProfileTab2.ProfileBasicDetailModel getProfileInfo() {
        return profileInfo;
    }

    public void setProfileInfo(ProfileTab2.ProfileBasicDetailModel profileInfo) {
        this.profileInfo = profileInfo;
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

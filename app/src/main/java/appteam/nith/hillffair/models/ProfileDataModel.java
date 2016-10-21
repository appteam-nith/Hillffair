package appteam.nith.hillffair.models;

import appteam.nith.hillffair.fragments.ProfileTab2;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Aditya on 10/5/2016.
 */
public class ProfileDataModel {

    @SerializedName("profile")
    private ProfileTab2.ProfileBasicDetailModel profileInfo;

    @SerializedName("success")
    private boolean success;

    @SerializedName("msg")
    private String msg;

    public ProfileDataModel(ProfileTab2.ProfileBasicDetailModel profileInfo, boolean success, String msg) {
        this.profileInfo = profileInfo;
        this.success = success;
        this.msg = msg;
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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

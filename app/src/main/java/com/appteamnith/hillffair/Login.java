package com.appteamnith.hillffair;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jatin on 9/11/2016.
 */
public class Login {

    @SerializedName("success")
    public boolean success;

    @SerializedName("error")
    public  String error;

    public Login(boolean success, String error) {
        this.success = success;
        this.error = error;
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

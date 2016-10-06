package com.appteamnith.hillffair.models;

import com.appteamnith.hillffair.fragments.ProfileTab1;
import com.google.gson.annotations.SerializedName;

/**
 * Created by lenovo on 10/6/2016.
 */
public class UserScoreResponse {


    @SerializedName("quize")
    private ProfileTab1.QuizResponse quizResponse;

    @SerializedName("success")
    private boolean success;

    @SerializedName("error")
    private String error;

    public UserScoreResponse(ProfileTab1.QuizResponse quizResponse, boolean success, String error) {
        this.quizResponse = quizResponse;
        this.success = success;
        this.error = error;
    }

    public ProfileTab1.QuizResponse getQuizResponse() {
        return quizResponse;
    }

    public void setQuizResponse(ProfileTab1.QuizResponse quizResponse) {
        this.quizResponse = quizResponse;
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

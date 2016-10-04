package com.appteamnith.hillffair.utilities;

import com.appteamnith.hillffair.activities.ProfileActivity;
import com.appteamnith.hillffair.activities.UploadNewsFeedActivity;
import com.appteamnith.hillffair.modals.Login;
import com.appteamnith.hillffair.modals.Register;
import com.appteamnith.hillffair.modals.newsfeed_model;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import static com.appteamnith.hillffair.R.layout.newsfeed;

/**
 * Created by Jatin on 9/11/2016.
 */

public interface APIINTERFACE {

    @FormUrlEncoded
    @POST("login")
    Call<Login> login(@Field("email") String email, @Field("pwd") String pwd);

    @FormUrlEncoded
    @POST("register")
    Call<Register> register (@Field("name")String name, @Field("email")String email, @Field("pwd")String pwd, @Field("nitian")boolean nitian, @Field("rollno")String rollno, @Field("phone")String phone);


    @FormUrlEncoded
    @POST("newsfeed/post")
    Call<UploadNewsFeedActivity.UploadResponse> uploadNews(@Field("title") String title, @Field("description") String description, @Field("uId") String userId, @Field("uName") String userName);


    @GET("newsfeed/all")
    Call<newsfeed_model> getAllNews(@Query("from")String from);

    @FormUrlEncoded
    @POST("profile")
    Call<ProfileActivity.ProfileBasicDetailModel> profileBasicInfo(@Field("id") String id);

}



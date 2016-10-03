package com.appteamnith.hillffair;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Jatin on 9/11/2016.
 */

public interface APIINTERFACE {

    @FormUrlEncoded
    @POST("login")
    Call<Login> login(@Field("email") String email,@Field("pwd") String pwd);

    @FormUrlEncoded
    @POST("register")
    Call<Register> register (@Field("name")String name,@Field("email")String email,@Field("pwd")String pwd,@Field("nitian")boolean nitian,@Field("rollno")String rollno,@Field("phone")String phone);


    @FormUrlEncoded
    @POST("newsfeed/post")
    Call<UploadNewsFeed.UploadResponse> uploadNews(@Field("title") String title,@Field("description") String description,@Field("uId") String userId,@Field("uName") String userName);


}


